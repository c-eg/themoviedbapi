package info.movito.themoviedbapi.model.core;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Small utility to get all pages.
 */
public final class ResultPageHelper {
    private static final int MAX_PAGES_DEFAULT = 50;

    private ResultPageHelper() {
    }

    /**
     * Get all pages.
     *
     * @param rrp result page provider
     * @param <K> type of result
     * @return all results
     */
    public static <K> List<K> getAll(ResultPageProvider<K> rrp) {
        return getAll(rrp, MAX_PAGES_DEFAULT);
    }

    /**
     * Get all pages.
     *
     * @param rrp      result page provider
     * @param maxPages maximum number of pages to fetch
     * @param <K>      type of result
     * @return all results
     */
    public static <K> List<K> getAll(ResultPageProvider<K> rrp, int maxPages) {
        ResultsPage<K> firstPage = rrp.get(1);

        List<ResultsPage<K>> pages = new ArrayList<>();
        pages.add(firstPage);

        for (int pageNumber = 2; pageNumber < Math.min(firstPage.getTotalPages(), maxPages); pageNumber++) {
            pages.add(rrp.get(pageNumber));
        }

        return Lists.newArrayList(Iterables.concat(Lists.transform(pages, ResultsPage::getResults)));
    }

    /**
     * Result page provider.
     *
     * @param <T> type of result
     */
    public interface ResultPageProvider<T> {
        ResultsPage<T> get(int pageNumber);
    }
}
