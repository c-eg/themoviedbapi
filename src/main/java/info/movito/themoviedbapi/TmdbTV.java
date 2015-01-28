package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.tv.TvSeries;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;


public class TmdbTV extends AbstractTmdbApi {

    public static final String TMDB_METHOD_TV = "tv";
    public static final String TMDB_METHOD_POPULAR = "popular";
    public static final String TMDB_METHOD_CREDITS = "credits";
    public static final String TMDB_METHOD_ONTHEAIR = "on_the_air";
    public static final String TMDB_METHOD_AIRINGTODAY = "airing_today";
    public static final String TMDB_METHOD_TOPRATED = "top_rated";


    public static enum TvMethod {credits, external_ids, images, videos}


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

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        apiUrl.appendToResponse(Utils.asStringArray(appendToResponse));

        return mapJsonResult(apiUrl, TvSeries.class);
    }


    public Credits getCredits(int seriesId, String language) {
        ApiUrl apiUrl = new ApiUrl(TmdbTV.TMDB_METHOD_TV, seriesId, TmdbTV.TMDB_METHOD_CREDITS);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }
        return mapJsonResult(apiUrl, Credits.class);
    }


    public TvResultsPage getPopular(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TmdbTV.TMDB_METHOD_TV, TmdbTV.TMDB_METHOD_POPULAR);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    public TvResultsPage getAiringToday(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TmdbTV.TMDB_METHOD_TV, TmdbTV.TMDB_METHOD_AIRINGTODAY);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    public TvResultsPage getOnTheAir(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TmdbTV.TMDB_METHOD_TV, TmdbTV.TMDB_METHOD_ONTHEAIR);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }


    public TvResultsPage getTopRated(String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TmdbTV.TMDB_METHOD_TV, TmdbTV.TMDB_METHOD_TOPRATED);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, Integer.toString(page));
        }

        return mapJsonResult(apiUrl, TvResultsPage.class);
    }
}
