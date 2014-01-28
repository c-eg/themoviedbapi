package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Reviews;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class TmdbReviews extends AbstractApiElement {

    TmdbReviews(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    public List<Reviews> getReviews(int movieId, String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TmdbMovies.TMDB_METHOD_MOVIE, movieId, "reviews");

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        if (page != null && page > 0) {
            apiUrl.addParam(PARAM_PAGE, page);
        }

        return mapJsonResult(apiUrl, ReviewResults.class).getResults();
    }


    private static class ReviewResults extends ResultsPage<Reviews> {

    }

}