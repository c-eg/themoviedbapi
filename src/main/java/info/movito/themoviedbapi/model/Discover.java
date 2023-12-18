package info.movito.themoviedbapi.model;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import info.movito.themoviedbapi.api.AbstractTmdbApi;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.keywords.Keyword;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generate a discover object for use in the MovieDbApi
 * This allows you to just add the search components you are concerned with.
 * TODO: Investigate if this class still needs to be like this
 *
 * @author stuart.boston
 */
public class Discover {
    private static final String PARAM_PRIMARY_RELEASE_YEAR = "primary_release_year";

    private static final String PARAM_VOTE_COUNT_GTE = "vote_count.gte";

    private static final String PARAM_VOTE_AVERAGE_GTE = "vote_average.gte";

    private static final String PARAM_WITH_GENRES = "with_genres";

    private static final String PARAM_WITH_KEYWORKDS = "with_keywords";

    private static final String PARAM_RELEASE_DATE_GTE = "release_date.gte";

    private static final String PARAM_RELEASE_DATE_LTE = "release_date.lte";

    private static final String PARAM_CERTIFICATION_COUNTRY = "certification_country";

    private static final String PARAM_CERTIFICATION_LTE = "certification.lte";

    private static final String PARAM_WITH_COMPANIES = "with_companies";

    private static final String PARAM_SORT_BY = "sort_by";

    private static final int YEAR_MIN = 1900;

    private static final int YEAR_MAX = 2100;

    private final Map<String, String> params = new HashMap<>();

    Function<IdElement, Integer> toID = IdElement::getId;

    /**
     * Get the parameters. This will be used to construct the URL in the API.
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * Minimum value is 1 if included.
     */
    public Discover page(Integer page) {
        if (page != null && page > 0) {
            params.put(AbstractTmdbApi.PARAM_PAGE, String.valueOf(page));
        }
        return this;
    }

    /**
     * ISO 639-1 code.
     */
    public Discover language(String language) {
        if (StringUtils.isNotBlank(language)) {
            params.put(AbstractTmdbApi.PARAM_LANGUAGE, language);
        }
        return this;
    }

    /**
     * Available options are <br> vote_average.desc<br> vote_average.asc<br> release_date.desc<br> release_date.asc<br>.
     */
    public Discover sortBy(String sortBy) {
        if (StringUtils.isNotBlank(sortBy)) {
            params.put(PARAM_SORT_BY, sortBy);
        }
        return this;
    }

    /**
     * Toggle the inclusion of adult titles.
     */
    public Discover includeAdult(boolean includeAdult) {
        params.put(AbstractTmdbApi.PARAM_ADULT, String.valueOf(includeAdult));
        return this;
    }

    /**
     * Filter the results release dates to matches that include this value.
     */
    public Discover year(int year) {
        if (checkYear(year)) {
            params.put(AbstractTmdbApi.PARAM_YEAR, String.valueOf(year));
        }
        return this;
    }

    /**
     * Filter the results so that only the primary release date year has this value.
     */
    public Discover primaryReleaseYear(int primaryReleaseYear) {
        if (checkYear(primaryReleaseYear)) {
            params.put(PARAM_PRIMARY_RELEASE_YEAR, String.valueOf(primaryReleaseYear));
        }
        return this;
    }

    /**
     * Only include movies that are equal to, or have a vote count higher than this value.
     */
    public Discover voteCountGte(int voteCountGte) {
        if (voteCountGte > 0) {
            params.put(PARAM_VOTE_COUNT_GTE, String.valueOf(voteCountGte));
        }
        return this;
    }

    /**
     * Only include movies that are equal to, or have a higher average rating than this value.
     */
    public Discover voteAverageGte(float voteAverageGte) {
        if (voteAverageGte > 0) {
            params.put(PARAM_VOTE_AVERAGE_GTE, String.valueOf(voteAverageGte));
        }
        return this;
    }

    /**
     * Only include movies with the specified genres.
     *
     * Expected value is an integer (the id of a genre).
     *
     * Multiple values can be specified.
     *
     * Comma separated indicates an 'AND' query, while a pipe (|) separated value indicates an 'OR'
     *
     * @deprecated use {@link #withKeywords(List, boolean)} instead.
     */
    @Deprecated
    public Discover withGenres(String withGenres) {
        if (StringUtils.isNotBlank(withGenres)) {
            params.put(PARAM_WITH_GENRES, withGenres);
        }
        return this;
    }

    /**
     * Only include movies with the specified keywords.
     *
     * Expected value is an integer (the id of a keyword). Multiple values can be specified.
     * Comma separated indicates an 'AND' query, while a pipe (|) separated value indicates an 'OR'.
     *
     * Multiple values can be specified.
     */
    public Discover withKeywords(List<Keyword> keywords, boolean orQuery) {
        String query = Joiner.on(orQuery ? "|" : ",").join(Iterables.transform(keywords, toID));
        assert StringUtils.isNotBlank(query);

        params.put(PARAM_WITH_KEYWORKDS, query);

        return this;
    }

    /**
     * The minimum release to include.
     *
     * Expected format is YYYY-MM-DD.
     */
    public Discover releaseDateGte(String releaseDateGte) {
        if (StringUtils.isNotBlank(releaseDateGte)) {
            params.put(PARAM_RELEASE_DATE_GTE, releaseDateGte);
        }
        return this;
    }

    /**
     * The maximum release to include.
     *
     * Expected format is YYYY-MM-DD.
     */
    public Discover releaseDateLte(String releaseDateLte) {
        if (StringUtils.isNotBlank(releaseDateLte)) {
            params.put(PARAM_RELEASE_DATE_LTE, releaseDateLte);
        }
        return this;
    }

    /**
     * Only include movies with certifications for a specific country.
     *
     * When this value is specified, 'certificationLte' is required.
     *
     * A ISO 3166-1 is expected
     */
    public Discover certificationCountry(String certificationCountry) {
        if (StringUtils.isNotBlank(certificationCountry)) {
            params.put(PARAM_CERTIFICATION_COUNTRY, certificationCountry);
        }
        return this;
    }

    /**
     * Only include movies with this certification and lower.
     *
     * Expected value is a valid certification for the specified 'certificationCountry'.
     */
    public Discover certificationLte(String certificationLte) {
        if (StringUtils.isNotBlank(certificationLte)) {
            params.put(PARAM_CERTIFICATION_LTE, certificationLte);
        }
        return this;
    }

    /**
     * Filter movies to include a specific company.
     *
     * Expected value is an integer (the id of a company).
     *
     * They can be comma separated to indicate an 'AND' query
     */
    public Discover withCompanies(String withCompanies) {
        if (StringUtils.isNotBlank(withCompanies)) {
            params.put(PARAM_WITH_COMPANIES, withCompanies);
        }
        return this;
    }

    /**
     * check the year is between the min and max.
     */
    private boolean checkYear(int year) {
        return (year >= YEAR_MIN && year <= YEAR_MAX);
    }
}
