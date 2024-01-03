package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.ContentRating;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.model.core.TvKeywords;
import info.movito.themoviedbapi.model.tv.TvSeries;
import info.movito.themoviedbapi.tools.ApiUrl;

import static info.movito.themoviedbapi.Utils.asStringArray;

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
    public TvSeries getSeries(int seriesId, String language, TvMethod... appendToResponse) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId);

        apiUrl.addLanguage(language);

        apiUrl.appendToResponse(asStringArray(appendToResponse));

        return mapJsonResult(apiUrl, TvSeries.class);
    }

    /**
     * Gets the series' credits.
     *
     * @param seriesId the series id
     * @param language the language
     * @return the show's credits
     */
    public Credits getCredits(int seriesId, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_CREDITS);

        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, Credits.class);
    }

    /**
     * Gets the current popular series.
     *
     * @param language the language
     * @param page     the page
     * @return the series
     */
    public TvResultsPage getPopular(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_POPULAR);

        apiUrl.addLanguage(language);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }

    /**
     * Gets the series airing today.
     *
     * @param language the language
     * @param page     the page
     * @param timezone the timezone
     * @return the series
     */
    public TvResultsPage getAiringToday(String language, Integer page, Timezone timezone) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_AIRINGTODAY);

        apiUrl.addLanguage(language);
        apiUrl.addPage(page);

        if (timezone != null) {
            apiUrl.addPathParam("timezone", timezone);
        }

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }

    /**
     * Gets the series airing today.
     *
     * @param language the language
     * @param page     the page
     * @return the series
     */
    public TvResultsPage getOnTheAir(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_ONTHEAIR);

        apiUrl.addLanguage(language);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }

    /**
     * Gets the top-rated series.
     *
     * @param language the language
     * @param page     the page
     * @return the series
     */
    public TvResultsPage getTopRated(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_TOPRATED);

        apiUrl.addLanguage(language);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }

    /**
     * Gets the series' images.
     *
     * @param seriesId the series id
     * @param language the language
     * @return the series
     */
    public MovieImages getImages(int seriesId, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TvMethod.images);

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, MovieImages.class);
    }

    /**
     * Gets the series keywords.
     *
     * @param seriesId the series id
     * @param language the language
     * @return the keywords
     */
    public TvKeywords getKeywords(int seriesId, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_KEYWORDS);

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, TvKeywords.class);
    }

    /**
     * Gets the series' content rating.
     *
     * @param seriesId the series id
     * @param language the language
     * @return the content rating
     */
    public ContentRating.Results getContentRating(int seriesId, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_CONTENT_RATING);

        apiUrl.addLanguage(language);

        return mapJsonResult(apiUrl, ContentRating.Results.class);
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
