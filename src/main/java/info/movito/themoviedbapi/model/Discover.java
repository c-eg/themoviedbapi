package info.movito.themoviedbapi.model;

import info.movito.themoviedbapi.AbstractApiElement;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Generate a discover object for use in the MovieDbApi
 * <p/>
 * This allows you to just add the search components you are concerned with
 *
 * @author stuart.boston
 */
public class Discover {

    private final Map<String, String> params = new HashMap<String, String>();
    private static final String PARAM_PRIMARY_RELEASE_YEAR = "primary_release_year";
    private static final String PARAM_VOTE_COUNT_GTE = "vote_count.gte";
    private static final String PARAM_VOTE_AVERAGE_GTE = "vote_average.gte";
    private static final String PARAM_WITH_GENRES = "with_genres";
    private static final String PARAM_RELEASE_DATE_GTE = "release_date.gte";
    private static final String PARAM_RELEASE_DATE_LTE = "release_date.lte";
    private static final String PARAM_CERTIFICATION_COUNTRY = "certification_country";
    private static final String PARAM_CERTIFICATION_LTE = "certification.lte";
    private static final String PARAM_WITH_COMPANIES = "with_companies";
    private static final String PARAM_SORT_BY = "sort_by";
    private static final int YEAR_MIN = 1900;
    private static final int YEAR_MAX = 2100;


    /**
     * Get the parameters
     * <p/>
     * This will be used to construct the URL in the API
     *
     * @return
     */
    public Map<String, String> getParams() {
        return params;
    }


    /**
     * Minimum value is 1 if included.
     *
     * @param page
     * @return
     */
    public Discover page(Integer page) {
        if (page != null && page > 0) {
            params.put(AbstractApiElement.PARAM_PAGE, String.valueOf(page));
        }
        return this;
    }


    /**
     * ISO 639-1 code
     *
     * @param language
     * @return
     */
    public Discover language(String language) {
        if (StringUtils.isNotBlank(language)) {
            params.put(AbstractApiElement.PARAM_LANGUAGE, language);
        }
        return this;
    }


    /**
     * Available options are <br>
     * vote_average.desc<br>
     * vote_average.asc<br>
     * release_date.desc<br>
     * release_date.asc<br>
     * popularity.desc<br>
     * popularity.asc
     *
     * @param sortBy
     * @return
     */
    public Discover sortBy(String sortBy) {
        if (StringUtils.isNotBlank(sortBy)) {
            params.put(PARAM_SORT_BY, sortBy);
        }
        return this;
    }


    /**
     * Toggle the inclusion of adult titles
     *
     * @param includeAdult
     * @return
     */
    public Discover includeAdult(boolean includeAdult) {
        params.put(AbstractApiElement.PARAM_ADULT, String.valueOf(includeAdult));
        return this;
    }


    /**
     * Filter the results release dates to matches that include this value.
     *
     * @param year
     * @return
     */
    public Discover year(int year) {
        if (checkYear(year)) {
            params.put(AbstractApiElement.PARAM_YEAR, String.valueOf(year));
        }
        return this;
    }


    /**
     * Filter the results so that only the primary release date year has this value
     *
     * @param primaryReleaseYear
     * @return
     */
    public Discover primaryReleaseYear(int primaryReleaseYear) {
        if (checkYear(primaryReleaseYear)) {
            params.put(PARAM_PRIMARY_RELEASE_YEAR, String.valueOf(primaryReleaseYear));
        }
        return this;
    }


    /**
     * Only include movies that are equal to, or have a vote count higher than this value
     *
     * @param voteCountGte
     * @return
     */
    public Discover voteCountGte(int voteCountGte) {
        if (voteCountGte > 0) {
            params.put(PARAM_VOTE_COUNT_GTE, String.valueOf(voteCountGte));
        }
        return this;
    }


    /**
     * Only include movies that are equal to, or have a higher average rating than this value
     *
     * @param voteAverageGte
     * @return
     */
    public Discover voteAverageGte(float voteAverageGte) {
        if (voteAverageGte > 0) {
            params.put(PARAM_VOTE_AVERAGE_GTE, String.valueOf(voteAverageGte));
        }
        return this;
    }


    /**
     * Only include movies with the specified genres.
     * <p/>
     * Expected value is an integer (the id of a genre).
     * <p/>
     * Multiple values can be specified.
     * <p/>
     * Comma separated indicates an 'AND' query, while a pipe (|) separated value indicates an 'OR'
     *
     * @param withGenres
     * @return
     */
    public Discover withGenres(String withGenres) {
        if (StringUtils.isNotBlank(withGenres)) {
            params.put(PARAM_WITH_GENRES, withGenres);
        }
        return this;
    }


    /**
     * The minimum release to include.
     * <p/>
     * Expected format is YYYY-MM-DD.
     *
     * @param releaseDateGte
     * @return
     */
    public Discover releaseDateGte(String releaseDateGte) {
        if (StringUtils.isNotBlank(releaseDateGte)) {
            params.put(PARAM_RELEASE_DATE_GTE, releaseDateGte);
        }
        return this;
    }


    /**
     * The maximum release to include.
     * <p/>
     * Expected format is YYYY-MM-DD.
     *
     * @param releaseDateLte
     * @return
     */
    public Discover releaseDateLte(String releaseDateLte) {
        if (StringUtils.isNotBlank(releaseDateLte)) {
            params.put(PARAM_RELEASE_DATE_LTE, releaseDateLte);
        }
        return this;
    }


    /**
     * Only include movies with certifications for a specific country.
     * <p/>
     * When this value is specified, 'certificationLte' is required.
     * <p/>
     * A ISO 3166-1 is expected
     *
     * @param certificationCountry
     * @return
     */
    public Discover certificationCountry(String certificationCountry) {
        if (StringUtils.isNotBlank(certificationCountry)) {
            params.put(PARAM_CERTIFICATION_COUNTRY, certificationCountry);
        }
        return this;
    }


    /**
     * Only include movies with this certification and lower.
     * <p/>
     * Expected value is a valid certification for the specified 'certificationCountry'.
     *
     * @param certificationLte
     * @return
     */
    public Discover certificationLte(String certificationLte) {
        if (StringUtils.isNotBlank(certificationLte)) {
            params.put(PARAM_CERTIFICATION_LTE, certificationLte);
        }
        return this;
    }


    /**
     * Filter movies to include a specific company.
     * <p/>
     * Expected value is an integer (the id of a company).
     * <p/>
     * They can be comma separated to indicate an 'AND' query
     *
     * @param withCompanies
     * @return
     */
    public Discover withCompanies(String withCompanies) {
        if (StringUtils.isNotBlank(withCompanies)) {
            params.put(PARAM_WITH_COMPANIES, withCompanies);
        }
        return this;
    }


    /**
     * check the year is between the min and max
     *
     * @param year
     * @return
     */
    private boolean checkYear(int year) {
        return (year >= YEAR_MIN && year <= YEAR_MAX);
    }
}
