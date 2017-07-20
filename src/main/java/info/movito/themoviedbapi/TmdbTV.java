package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.model.tv.TvSeries;
import info.movito.themoviedbapi.tools.ApiUrl;

import static info.movito.themoviedbapi.Utils.asStringArray;


public class TmdbTV extends AbstractTmdbApi {

    public static final String TMDB_METHOD_TV = "tv";
    public static final String TMDB_METHOD_POPULAR = "popular";
    public static final String TMDB_METHOD_CREDITS = "credits";
    public static final String TMDB_METHOD_ONTHEAIR = "on_the_air";
    public static final String TMDB_METHOD_AIRINGTODAY = "airing_today";
    public static final String TMDB_METHOD_TOPRATED = "top_rated";
    public static final String TMDB_METHOD_RECOMMENDATIONS = "recommendations";


    public static enum TvMethod {credits, external_ids, images, videos, recommendations}


    TmdbTV(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    /**
     * This method is used to retrieve all of the basic series information.
     *
     * @param seriesId
     * @param language
     */
    public TvSeries getSeries(int seriesId, String language, TvMethod... appendToResponse) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId);

        apiUrl.addLanguage(language);

        apiUrl.appendToResponse(asStringArray(appendToResponse));

        return mapJsonResult(apiUrl, TvSeries.class);
    }


    public Credits getCredits(int seriesId, String language) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, seriesId, TMDB_METHOD_CREDITS);

        apiUrl.addLanguage(language);
        return mapJsonResult(apiUrl, Credits.class);
    }


    public TvResultsPage getPopular(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_POPULAR);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    public TvResultsPage getAiringToday(String language, Integer page, Timezone timezone) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_AIRINGTODAY);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        if (timezone != null) {
            apiUrl.addParam("timezone", timezone);
        }
        
        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    public TvResultsPage getOnTheAir(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_ONTHEAIR);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    public TvResultsPage getTopRated(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_TV, TMDB_METHOD_TOPRATED);

        apiUrl.addLanguage(language);

        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }
}
