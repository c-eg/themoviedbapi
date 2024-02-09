package info.movito.themoviedbapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.AlternativeTitle;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.ExternalIds;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.MovieListResultsPage;
import info.movito.themoviedbapi.model.Translations;
import info.movito.themoviedbapi.model.MoviesAlternativeTitles;
import info.movito.themoviedbapi.model.ReleaseInfo;
import info.movito.themoviedbapi.model.Translation;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.model.movies.changes.ChangesItems;
import info.movito.themoviedbapi.model.core.IdElement;
import info.movito.themoviedbapi.model.core.MovieDbResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.providers.ProviderResults;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.TmdbException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static info.movito.themoviedbapi.TmdbAccount.PARAM_SESSION;
import static info.movito.themoviedbapi.TmdbMovies.MovieMethod.videos;
import static info.movito.themoviedbapi.util.Utils.asStringArray;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The movie database api for movies. See the
 * <a href="https://developer.themoviedb.org/reference/movie-details">documentation</a> for more info.
 */
public class TmdbMovies extends AbstractTmdbApi {
    // API Methods
    public static final String TMDB_METHOD_MOVIE = "movie";

    private static final String PARAM_START_DATE = "start_date";

    private static final String PARAM_END_DATE = "end_date";

    private static final String PARAM_COUNTRY = "country";

    private static final String PARAM_REGION = "region";

    /**
     * Create a new TmdbMovies instance to call the movie related TMDb API methods.
     */
    public TmdbMovies(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * This method is used to retrieve all the basic movie information.
     *
     * It will return the single highest rated poster and backdrop.
     */
    public MovieDb getMovie(int movieId, String language, MovieMethod... appendToResponse) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId);

        apiUrl.addLanguage(language);

        apiUrl.appendToResponse(asStringArray(appendToResponse));

        return mapJsonResult(apiUrl, MovieDb.class);
    }

    /**
     * This method is used to retrieve all the alternative titles we have for a particular movie.
     */
    public List<AlternativeTitle> getAlternativeTitles(int movieId, String country) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.alternative_titles);

        if (StringUtils.isNotBlank(country)) {
            apiUrl.addPathParam(PARAM_COUNTRY, country);
        }

        return mapJsonResult(apiUrl, MoviesAlternativeTitles.class).getTitles();
    }

    /**
     * Gets the movie credits.
     *
     * @param movieId the movies id
     * @return the movie credits
     */
    public Credits getCredits(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.credits);

        return mapJsonResult(apiUrl, Credits.class);
    }

    /**
     * This method should be used when you’re wanting to retrieve all of the images for a particular movie.
     */
    public MovieImages getImages(int movieId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.images);

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, MovieImages.class);
    }

    /**
     * This method is used to retrieve all the keywords that have been added to a particular movie.
     *
     * Currently, only English keywords exist.
     */
    public List<Keyword> getKeywords(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.keywords);

        return mapJsonResult(apiUrl, KeywordResults.class).results;
    }

    /**
     * This method is used to retrieve all of the release and certification data we have for a specific movie.
     */
    public List<ReleaseInfo> getReleaseInfo(int movieId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.release_dates);

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, ReleaseInfoResults.class).results;
    }

    /**
     * This method is used to retrieve all of the videos for a particular movie.
     *
     * Supported sites are YouTube and QuickTime.
     */
    public List<Video> getVideos(int movieId, String language) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, videos);

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, Video.Results.class).getVideos();
    }

    /**
     * This method is used to retrieve a list of the available translations for a specific movie.
     */
    public List<Translation> getTranslations(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.translations);

        return mapJsonResult(apiUrl, Translations.class).getTranslations();
    }

    /**
     * The similar movies method will let you retrieve the similar movies for a particular movie.
     *
     * This data is created dynamically but with the help of users votes on TMDb.
     *
     * The data is much better with movies that have more keywords
     */
    public MovieDbResultsPage getSimilarMovies(int movieId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.similar);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieDbResultsPage.class);
    }

    /**
     * The recomendations movies method will let you retrieve the reccomended movies for a particular movie.
     *
     * This data is created dynamically but with the help of TMDb internal algorithm.
     *
     * The data is much better with movies that are more popular
     */
    public MovieDbResultsPage getRecommendedMovies(int movieId, String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.recommendations);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieDbResultsPage.class);
    }

    /**
     * Get the lists that the movie belongs to.
     */
    public MovieListResultsPage getListsContaining(int movieId, SessionToken sessionToken, String language,
                                                   Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.lists);

        apiUrl.addPathParam(PARAM_SESSION, sessionToken);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieListResultsPage.class);
    }

    /**
     * Get the changes for a specific movie id.
     *
     * Changes are grouped by key, and ordered by date in descending order.
     *
     * By default, only the last 24 hours of changes are returned.
     *
     * The maximum number of days that can be returned in a single request is 14.
     *
     * The language is present on fields that are translatable.
     *
     * TODO: DOES NOT WORK AT THE MOMENT. This is due to the "value" item changing type in the ChangeItem
     *
     * @param startDate the start date of the changes, optional
     * @param endDate   the end date of the changes, optional
     */
    public ChangesItems getChanges(int movieId, String startDate, String endDate) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.changes);

        if (StringUtils.isNotBlank(startDate)) {
            apiUrl.addPathParam(PARAM_START_DATE, startDate);
        }

        if (StringUtils.isNotBlank(endDate)) {
            apiUrl.addPathParam(PARAM_END_DATE, endDate);
        }

        return mapJsonResult(apiUrl, ChangesItems.class);
    }

    /**
     * Powered by our partnership with JustWatch, you can query this method to get a list of the availabilities per country by provider.
     * <p>
     * This is not going to return full deep links, but rather, it's just enough information to display what's available where.
     * <p>
     * You can link to the provided TMDB URL to help support TMDB and provide the actual deep links to the content.
     *
     * See: <a href="https://developers.themoviedb.org/3/movies/get-movie-watch-providers">API Docs</a>
     *
     * @param movieId The MovieId to retrieve watch providers for
     * @return Complete set of watch providers by country
     */
    public ProviderResults getWatchProviders(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.watch_providers);

        return mapJsonResult(apiUrl, ProviderResults.class);
    }

    /**
     * This method is used to retrieve the external ids for a movie.
     */
    public ExternalIds getExternalIds(int movieId) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, MovieMethod.external_ids);

        return mapJsonResult(apiUrl, ExternalIds.class);
    }

    /**
     * This method is used to retrieve the newest movie that was added to TMDb.
     */
    public MovieDb getLatestMovie() throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, MovieMethod.latest);

        return mapJsonResult(apiUrl, MovieDb.class);
    }

    /**
     * Get the list of upcoming movies.
     *
     * This list refreshes every day.
     *
     * The maximum number of items this list will include is 100.
     * <p>
     * See https://developers.themoviedb.org/3/movies/get-upcoming
     */
    public MovieDbResultsPage getUpcoming(String language, Integer page, String region) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, MovieMethod.upcoming);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        if (isNotBlank(region)) {
            apiUrl.addPathParam(PARAM_REGION, region);
        }

        return mapJsonResult(apiUrl, MovieDbResultsPage.class);
    }

    /**
     * This method is used to retrieve the movies currently in theatres.
     *
     * This is a curated list that will normally contain 100 movies. The default response will return 20 movies.
     */
    public MovieDbResultsPage getNowPlayingMovies(String language, Integer page, String region) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, MovieMethod.now_playing);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        if (isNotBlank(region)) {
            apiUrl.addPathParam(PARAM_REGION, region);
        }

        return mapJsonResult(apiUrl, MovieDbResultsPage.class);
    }

    /**
     * This method is used to retrieve the daily movie popularity list.
     *
     * This list is updated daily. The default response will return 20 movies.
     */
    public MovieDbResultsPage getPopularMovies(String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, MovieMethod.popular);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieDbResultsPage.class);
    }

    /**
     * This method is used to retrieve the top rated movies that have over 10 votes on TMDb.
     *
     * The default response will return 20 movies.
     */
    public MovieDbResultsPage getTopRatedMovies(String language, Integer page) throws TmdbException {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, MovieMethod.top_rated);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, MovieDbResultsPage.class);
    }

    /**
     * TODO: account_states and rating are not included as it wouldn't work anyway because of missing session id
     * --> inject session id into tmdb-instance?
     * TODO: remove "trailers" if deprecated or no longer available.
     */
    public enum MovieMethod {
        // CHECKSTYLE OFF: AnnotationLocation
        alternative_titles, credits, images, keywords, releases, release_dates,
        @Deprecated trailers,
        videos, // replacement for trailers
        translations, similar, recommendations,
        reviews, lists, changes, latest, upcoming, now_playing, popular, top_rated,
        watch_providers("watch/providers"), external_ids;
        // CHECKSTYLE ON: AnnotationLocation

        private String name;

        MovieMethod() {
        }

        MovieMethod(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            if (name != null) {
                return name;
            }

            return super.toString();
        }
    }

    private static class KeywordResults extends IdElement {
        @JsonProperty("keywords")
        List<Keyword> results;
    }

    @SuppressWarnings("checkstyle:MissingJavadocType")
    public static class ReleaseInfoResults extends IdElement {
        @JsonProperty("results")
        private List<ReleaseInfo> results;

        public List<ReleaseInfo> getResults() {
            return results;
        }
    }
}
