package uk.co.conoregan.themoviedbapi.api;

import uk.co.conoregan.themoviedbapi.model.TvSeriesDbResultsPage;
import uk.co.conoregan.themoviedbapi.model.ContentRating;
import uk.co.conoregan.themoviedbapi.model.Credits;
import uk.co.conoregan.themoviedbapi.model.MovieImages;
import uk.co.conoregan.themoviedbapi.model.config.Timezone;
import uk.co.conoregan.themoviedbapi.model.core.TvKeywords;
import uk.co.conoregan.themoviedbapi.model.tv.TvSeriesDb;
import uk.co.conoregan.themoviedbapi.tools.ApiEndpoint;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;

import static uk.co.conoregan.themoviedbapi.util.Utils.asStringArray;

/**
 * The movie database api for tv series. See the
 * <a href="https://developer.themoviedb.org/reference/tv-series-details">documentation</a> for more info.
 */
public class TmdbTV extends AbstractTmdbApi {
    public static final String TMDB_METHOD_TV = "tv";

    public static final String TMDB_METHOD_POPULAR = "popular";

    public static final String TMDB_METHOD_CREDITS = "credits";

    public static final String TMDB_METHOD_CONTENT_RATING = "content_rating";

    public static final String TMDB_METHOD_ONTHEAIR = "on_the_air";

    public static final String TMDB_METHOD_AIRINGTODAY = "airing_today";

    public static final String TMDB_METHOD_TOPRATED = "top_rated";

    public static final String TMDB_METHOD_RECOMMENDATIONS = "recommendations";

    public static final String TMDB_METHOD_KEYWORDS = "keywords";

    /**
     * Create a new TmdbTV instance to call the tv series TMDb API methods.
     */
    TmdbTV(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * This method is used to retrieve all the basic series information.
     */
    public TvSeriesDb getSeries(int seriesId, String language, TvMethod... appendToResponse) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, seriesId);

        apiEndpoint.addLanguage(language);

        apiEndpoint.appendToResponse(asStringArray(appendToResponse));

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvSeriesDb.class);
    }

    /**
     * Gets the series' credits.
     *
     * @param seriesId the series id
     * @param language the language
     * @return the show's credits
     */
    public Credits getCredits(int seriesId, String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, seriesId, TMDB_METHOD_CREDITS);
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Credits.class);
    }

    /**
     * Gets the current popular series.
     *
     * @param language the language
     * @param page     the page
     * @return the series
     */
    public TvSeriesDbResultsPage getPopular(String language, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, TMDB_METHOD_POPULAR);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvSeriesDbResultsPage.class);
    }

    /**
     * Gets the series airing today.
     *
     * @param language the language
     * @param page     the page
     * @param timezone the timezone
     * @return the series
     */
    public TvSeriesDbResultsPage getAiringToday(String language, Integer page, Timezone timezone) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, TMDB_METHOD_AIRINGTODAY);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);

        if (timezone != null) {
            apiEndpoint.addPathParam("timezone", timezone);
        }

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvSeriesDbResultsPage.class);
    }

    /**
     * Gets the series airing today.
     *
     * @param language the language
     * @param page     the page
     * @return the series
     */
    public TvSeriesDbResultsPage getOnTheAir(String language, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, TMDB_METHOD_ONTHEAIR);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvSeriesDbResultsPage.class);
    }

    /**
     * Gets the top-rated series.
     *
     * @param language the language
     * @param page     the page
     * @return the series
     */
    public TvSeriesDbResultsPage getTopRated(String language, Integer page) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, TMDB_METHOD_TOPRATED);
        apiEndpoint.addLanguage(language);
        apiEndpoint.addPage(page);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvSeriesDbResultsPage.class);
    }

    /**
     * Gets the series' images.
     *
     * @param seriesId the series id
     * @param language the language
     * @return the series
     */
    public MovieImages getImages(int seriesId, String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, seriesId, TvMethod.images);
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, MovieImages.class);
    }

    /**
     * Gets the series keywords.
     *
     * @param seriesId the series id
     * @param language the language
     * @return the keywords
     */
    public TvKeywords getKeywords(int seriesId, String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, seriesId, TMDB_METHOD_KEYWORDS);
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, TvKeywords.class);
    }

    /**
     * Gets the series' content rating.
     *
     * @param seriesId the series id
     * @param language the language
     * @return the content rating
     */
    public ContentRating.Results getContentRating(int seriesId, String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_TV, seriesId, TMDB_METHOD_CONTENT_RATING);
        apiEndpoint.addLanguage(language);

        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, ContentRating.Results.class);
    }

    /**
     * The type of tv method.
     */
    public enum TvMethod {
        credits,
        external_ids,
        images,
        videos,
        recommendations,
        keywords,
        content_ratings
    }
}
