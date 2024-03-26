package info.movito.themoviedbapi.tools.builders.discover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import info.movito.themoviedbapi.AbstractTmdbApi;
import info.movito.themoviedbapi.tools.builders.ParamBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import static info.movito.themoviedbapi.AbstractTmdbApi.PARAM_ADULT;

/**
 * <p>Abstract class for shared parameters to build a 'discover' parameter map.
 * This allows you to just add the search components you are concerned with.</p>
 * <p>For more information, see: <a href="https://developer.themoviedb.org/reference/discover-movie">Discover movie docs</a> and
 * <a href="https://developer.themoviedb.org/reference/discover-tv">Discover tv docs</a>.</p>
 *
 * @author stuart.boston & c-eg
 * @param <T> The type of the builder
 */
public abstract class DiscoverParamBuilder<T extends DiscoverParamBuilder<T>> implements ParamBuilder {
    protected static final String PARAM_SORT_BY = "sort_by";
    private static final String PARAM_VOTE_AVERAGE_GTE = "vote_average.gte";
    private static final String PARAM_VOTE_AVERAGE_LTE = "vote_average.lte";
    private static final String PARAM_VOTE_COUNT_GTE = "vote_count.gte";
    private static final String PARAM_VOTE_COUNT_LTE = "vote_count.lte";
    private static final String PARAM_WATCH_MONETIZATION_TYPES = "with_watch_monetization_types";
    private static final String PARAM_WATCH_REGION = "watch_region";
    private static final String PARAM_WITHOUT_COMPANIES = "without_companies";
    private static final String PARAM_WITHOUT_GENRES = "without_genres";
    private static final String PARAM_WITHOUT_KEYWORDS = "without_keywords";
    private static final String PARAM_WITHOUT_WATCH_PROVIDERS = "without_watch_providers";
    private static final String PARAM_WITH_COMPANIES = "with_companies";
    private static final String PARAM_WITH_GENRES = "with_genres";
    private static final String PARAM_WITH_KEYWORDS = "with_keywords";
    private static final String PARAM_WITH_ORIGIN_COUNTRY = "with_origin_country";
    private static final String PARAM_WITH_ORIGINAL_LANGUAGE = "with_original_language";
    private static final String PARAM_WITH_RUNTIME_GTE = "with_runtime.gte";
    private static final String PARAM_WITH_RUNTIME_LTE = "with_runtime.lte";
    private static final String PARAM_WITH_WATCH_PROVIDERS = "with_watch_providers";
    @Getter(AccessLevel.PROTECTED)
    private final Map<String, String> params = new HashMap<>();

    /**
     * Returns the subclass builder.
     *
     * @return the subclass builder.
     */
    protected abstract T me();

    @Override
    public Map<String, String> getParameterMap() {
        return params;
    }

    public T page(int page) {
        if (page <= 0) {
            throw new IllegalArgumentException("Page must be greater than 0");
        }

        params.put(AbstractTmdbApi.PARAM_PAGE, String.valueOf(page));
        return me();
    }

    public T language(String language) {
        if (StringUtils.isBlank(language)) {
            throw new IllegalArgumentException("Language must be set");
        }

        params.put(AbstractTmdbApi.PARAM_LANGUAGE, language);
        return me();
    }

    public T includeAdult(boolean includeAdult) {
        params.put(PARAM_ADULT, String.valueOf(includeAdult));
        return me();
    }

    public T withOriginalLanguage(String withOriginalLanguage) {
        if (StringUtils.isBlank(withOriginalLanguage)) {
            throw new IllegalArgumentException("Original language must be set");
        }

        params.put(PARAM_WITH_ORIGINAL_LANGUAGE, withOriginalLanguage);
        return me();
    }

    public T voteAverageGte(double voteAverageGte) {
        if (voteAverageGte < 0) {
            throw new IllegalArgumentException("Vote average must be >= 0");
        }

        params.put(PARAM_VOTE_AVERAGE_GTE, String.valueOf(voteAverageGte));
        return me();
    }

    public T voteAverageLte(double voteAverageLte) {
        if (voteAverageLte < 0) {
            throw new IllegalArgumentException("Vote average must be >= 0");
        }

        params.put(PARAM_VOTE_AVERAGE_LTE, String.valueOf(voteAverageLte));
        return me();
    }

    public T voteCountGte(double voteCountGte) {
        if (voteCountGte < 0) {
            throw new IllegalArgumentException("Vote count must be >= 0");
        }

        params.put(PARAM_VOTE_COUNT_GTE, String.valueOf(voteCountGte));
        return me();
    }

    public T voteCountLte(double voteCountLte) {
        if (voteCountLte > 0) {
            throw new IllegalArgumentException("Vote count must be >= 0");
        }

        params.put(PARAM_VOTE_COUNT_LTE, String.valueOf(voteCountLte));
        return me();
    }

    public T withWatchMonetizationTypes(List<String> withWatchMonetizationTypes, boolean orQuery) {
        if (withWatchMonetizationTypes == null || withWatchMonetizationTypes.isEmpty()) {
            throw new IllegalArgumentException("Watch monetization types must be set");
        }

        String query = Joiner.on(orQuery ? "|" : ",").join(withWatchMonetizationTypes);
        params.put(PARAM_WATCH_MONETIZATION_TYPES, query);
        return me();
    }

    public T watchRegion(String watchRegion) {
        if (StringUtils.isBlank(watchRegion)) {
            throw new IllegalArgumentException("Watch region must be set");
        }

        params.put(PARAM_WATCH_REGION, watchRegion);
        return me();
    }

    public T withoutCompanies(List<Integer> companies) {
        if (companies == null || companies.isEmpty()) {
            throw new IllegalArgumentException("Without companies must be set");
        }

        String query = Joiner.on(",").join(companies);
        params.put(PARAM_WITHOUT_COMPANIES, query);
        return me();
    }

    public T withoutGenres(List<Integer> genres) {
        if (genres == null || genres.isEmpty()) {
            throw new IllegalArgumentException("Without genres must be set");
        }

        String query = Joiner.on(",").join(genres);
        params.put(PARAM_WITHOUT_GENRES, query);
        return me();
    }

    public T withoutKeywords(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            throw new IllegalArgumentException("Without keywords must be set");
        }

        String query = Joiner.on(",").join(keywords);
        params.put(PARAM_WITHOUT_KEYWORDS, query);
        return me();
    }

    public T withoutWatchProviders(List<Integer> watchProviders) {
        if (watchProviders == null || watchProviders.isEmpty()) {
            throw new IllegalArgumentException("Without watch providers must be set");
        }

        String query = Joiner.on(",").join(watchProviders);
        params.put(PARAM_WITHOUT_WATCH_PROVIDERS, query);
        return me();
    }

    public T withCompanies(List<Integer> companyIds, boolean orQuery) {
        if (companyIds == null || companyIds.isEmpty()) {
            throw new IllegalArgumentException("With companies must be set");
        }

        String query = Joiner.on(orQuery ? "|" : ",").join(companyIds);
        params.put(PARAM_WITH_COMPANIES, query);
        return me();
    }

    public T withGenres(List<Integer> genreIds, boolean orQuery) {
        if (genreIds == null || genreIds.isEmpty()) {
            throw new IllegalArgumentException("With genres must be set");
        }

        String query = Joiner.on(orQuery ? "|" : ",").join(genreIds);
        params.put(PARAM_WITH_GENRES, query);
        return me();
    }

    public T withKeywords(List<Integer> keywordIds, boolean orQuery) {
        if (keywordIds == null || keywordIds.isEmpty()) {
            throw new IllegalArgumentException("With keywords must be set");
        }

        String query = Joiner.on(orQuery ? "|" : ",").join(keywordIds);
        params.put(PARAM_WITH_KEYWORDS, query);
        return me();
    }

    public T withOriginCountry(String originCountry) {
        if (StringUtils.isBlank(originCountry)) {
            throw new IllegalArgumentException("Origin country must be set");
        }

        params.put(PARAM_WITH_ORIGIN_COUNTRY, originCountry);
        return me();
    }

    public T withRuntimeGte(int runtimeGte) {
        if (runtimeGte < 0) {
            throw new IllegalArgumentException("Runtime must be >= 0");
        }

        params.put(PARAM_WITH_RUNTIME_GTE, String.valueOf(runtimeGte));
        return me();
    }

    public T withRuntimeLte(int runtimeLte) {
        if (runtimeLte < 0) {
            throw new IllegalArgumentException("Runtime must be >= 0");
        }

        params.put(PARAM_WITH_RUNTIME_LTE, String.valueOf(runtimeLte));
        return me();
    }

    public T withWatchProviders(List<Integer> watchProviderIds, boolean orQuery) {
        if (watchProviderIds == null || watchProviderIds.isEmpty()) {
            throw new IllegalArgumentException("With watch providers must be set");
        }

        String query = Joiner.on(orQuery ? "|" : ",").join(watchProviderIds);
        params.put(PARAM_WITH_WATCH_PROVIDERS, query);
        return me();
    }
}
