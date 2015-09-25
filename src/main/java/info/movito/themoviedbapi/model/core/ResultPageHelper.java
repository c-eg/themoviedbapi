package info.movito.themoviedbapi.model.core;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;


/**
 * Small utility to get all pages.
 */
public class ResultPageHelper {


    // small utility to get all pages
    public static <K> List<K> getAll(ResultPageProvider<K> rrp) {
        ResultsPage<K> firstPage = rrp.get(1);

        List<ResultsPage<K>> pages = new ArrayList<ResultsPage<K>>();
        pages.add(firstPage);

        for (int pageNumber = 2; pageNumber < firstPage.getTotalPages(); pageNumber++) {
            pages.add(rrp.get(pageNumber));
        }

        return Lists.newArrayList(Iterables.concat(Lists.transform(pages, new Function<ResultsPage<K>, List<K>>() {
            @Override
            public List<K> apply(ResultsPage<K> kResultsPage) {
                return kResultsPage.getResults();
            }

        })));
    }


    public interface ResultPageProvider<T> {

        ResultsPage<T> get(int pageNumber);
    }

}
