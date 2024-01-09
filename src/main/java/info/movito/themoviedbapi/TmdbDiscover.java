package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Discover;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.ApiUrl;

/**
 * The movie database api for discover. See the
 * <a href="https://developer.themoviedb.org/reference/discover-movie">documentation</a> for more info.
 */
public class TmdbDiscover extends AbstractTmdbApi {
    public static final String TMDB_METHOD_DISCOVER = "discover";

    /**
     * Create a new TmdbDiscover instance to call the discover related TMDb API methods.
     */
    TmdbDiscover(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Discover movies by different types of data like average rating, number of votes, genres and certifications.
     *
     * You can alternatively create a "discover" object and pass it to this method to cut out the requirement for all of
     * these parameters
     *
     * @param page                 Minimum value is 1
     * @param language             ISO 639-1 code.
     * @param sortBy               Available options are vote_average.desc, vote_average.asc, release_date.desc,
     *                             release_date.asc, popularity.desc, popularity.asc
     * @param includeAdult         Toggle the inclusion of adult titles
     * @param year                 Filter the results release dates to matches that include this value
     * @param primaryReleaseYear   Filter the results so that only the primary release date year has this value
     * @param voteCountGte         Only include movies that are equal to, or have a vote count higher than this value
     * @param voteAverageGte       Only include movies that are equal to, or have a higher average rating than this
     *                             value
     * @param withGenres           Only include movies with the specified genres. Expected value is an integer (the id
     *                             of a genre). Multiple values can be specified. Comma separated indicates an 'AND'
     *                             query, while a pipe (|) separated value indicates an 'OR'.
     * @param releaseDateGte       The minimum release to include. Expected format is YYYY-MM-DD
     * @param releaseDateLte       The maximum release to include. Expected format is YYYY-MM-DD
     * @param certificationCountry Only include movies with certifications for a specific country. When this value is
     *                             specified, 'certificationLte' is required. A ISO 3166-1 is expected.
     * @param certificationLte     Only include movies with this certification and lower. Expected value is a valid
     *                             certification for the specified 'certificationCountry'.
     * @param withCompanies        Filter movies to include a specific company. Expected value is an integer (the id of
     *                             a company). They can be comma separated to indicate an 'AND' query.
     * @return the movie results page.
     */
    public MovieResultsPage getDiscover(int page, String language, String sortBy, boolean includeAdult, int year, int primaryReleaseYear,
                                        int voteCountGte, float voteAverageGte, String withGenres, String releaseDateGte,
                                        String releaseDateLte, String certificationCountry, String certificationLte,
                                        String withCompanies) throws TmdbResponseException {

        Discover discover = new Discover();
        discover.page(page)
            .language(language)
            .sortBy(sortBy)
            .includeAdult(includeAdult)
            .year(year)
            .primaryReleaseYear(primaryReleaseYear)
            .voteCountGte(voteCountGte)
            .voteAverageGte(voteAverageGte)
            .withGenres(withGenres)
            .releaseDateGte(releaseDateGte)
            .releaseDateLte(releaseDateLte)
            .certificationCountry(certificationCountry)
            .certificationLte(certificationLte)
            .withCompanies(withCompanies);

        return getDiscover(discover);
    }

    /**
     * Discover movies by different types of data like average rating, number of votes, genres and certifications.
     *
     * @param discover A discover object containing the search criteria required
     * @return the movie results page.
     */
    public MovieResultsPage getDiscover(Discover discover) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_DISCOVER, "movie");

        for (String key : discover.getParams().keySet()) {
            apiUrl.addPathParam(key, discover.getParams().get(key));
        }

        return mapJsonResult(apiUrl, MovieResultsPage.class);
    }

    /**
     * Discover TVSeries by different types of data like average rating, number of votes, genres and certifications.
     *
     * You can alternatively create a "discover" object and pass it to this method to cut out the requirement for all of
     * these parameters
     *
     * @param page                 Minimum value is 1
     * @param language             ISO 639-1 code.
     * @param sortBy               Available options are vote_average.desc, vote_average.asc, release_date.desc,
     *                             release_date.asc, popularity.desc, popularity.asc
     * @param includeAdult         Toggle the inclusion of adult titles
     * @param year                 Filter the results release dates to matches that include this value
     * @param primaryReleaseYear   Filter the results so that only the primary release date year has this value
     * @param voteCountGte         Only include TVSeries that are equal to, or have a vote count higher than this value
     * @param voteAverageGte       Only include TVSeries that are equal to, or have a higher average rating than this
     *                             value
     * @param withGenres           Only include TVSeries with the specified genres. Expected value is an integer (the id
     *                             of a genre). Multiple values can be specified. Comma separated indicates an 'AND'
     *                             query, while a pipe (|) separated value indicates an 'OR'.
     * @param releaseDateGte       The minimum release to include. Expected format is YYYY-MM-DD
     * @param releaseDateLte       The maximum release to include. Expected format is YYYY-MM-DD
     * @param certificationCountry Only include TVSeries with certifications for a specific country. When this value is
     *                             specified, 'certificationLte' is required. A ISO 3166-1 is expected.
     * @param certificationLte     Only include TVSeries with this certification and lower. Expected value is a valid
     *                             certification for the specified 'certificationCountry'.
     * @param withCompanies        Filter TVSeries to include a specific company. Expected value is an integer (the id of
     *                             a company). They can be comma separated to indicate an 'AND' query.
     * @return the tv results page.
     */
    public TvResultsPage getDiscoverTV(int page, String language, String sortBy, boolean includeAdult, int year, int primaryReleaseYear,
                                       int voteCountGte, float voteAverageGte, String withGenres, String releaseDateGte,
                                       String releaseDateLte, String certificationCountry, String certificationLte, String withCompanies)
        throws TmdbResponseException {

        Discover discover = new Discover();
        discover.page(page)
                .language(language)
                .sortBy(sortBy)
                .includeAdult(includeAdult)
                .year(year)
                .primaryReleaseYear(primaryReleaseYear)
                .voteCountGte(voteCountGte)
                .voteAverageGte(voteAverageGte)
                .withGenres(withGenres)
                .releaseDateGte(releaseDateGte)
                .releaseDateLte(releaseDateLte)
                .certificationCountry(certificationCountry)
                .certificationLte(certificationLte)
                .withCompanies(withCompanies);

        return getDiscoverTV(discover);
    }

    /**
     * Discover TVSeries by different types of data like average rating, number of votes, genres and certifications.
     *
     * @param discover A discover object containing the search criteria required
     * @return the tv results page.
     */
    public TvResultsPage getDiscoverTV(Discover discover) throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_DISCOVER, "tv");

        for (String key : discover.getParams().keySet()) {
            apiUrl.addPathParam(key, discover.getParams().get(key));
        }

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }
}
