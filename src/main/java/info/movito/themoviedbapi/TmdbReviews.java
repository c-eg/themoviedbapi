package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Reviews;
import info.movito.themoviedbapi.model.core.ResultsPage;
import info.movito.themoviedbapi.tools.ApiUrl;

import static info.movito.themoviedbapi.TmdbMovies.TMDB_METHOD_MOVIE;

/**
 * The movie database api for reviews. See the
 * <a href="https://developer.themoviedb.org/reference/review-details">documentation</a> for more info.
 */
public class TmdbReviews extends AbstractTmdbApi {
    /**
     * Create a new TmdbReviews instance to call the reviews related TMDb API methods.
     */
    TmdbReviews(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Get the reviews for a movie.
     *
     * @param movieId the movies id
     * @param language the language
     * @param page the page
     * @return the reviews
     */
    public ReviewResultsPage getReviews(int movieId, String language, Integer page) {
        ApiUrl apiUrl = new ApiUrl(TMDB_METHOD_MOVIE, movieId, "reviews");

        apiUrl.addLanguage(language);
        apiUrl.addPage(page);

        return mapJsonResult(apiUrl, ReviewResultsPage.class);
    }

    @SuppressWarnings("checkstyle:MissingJavadocType")
    public static class ReviewResultsPage extends ResultsPage<Reviews> {

    }
}
