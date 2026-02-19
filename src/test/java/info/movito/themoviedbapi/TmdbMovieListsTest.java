package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.List;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.movielists.MovieResultsPageWithDates;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.testutil.ValidatorConfig;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbMovieLists.TMDB_METHOD_MOVIE_LISTS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbMovieLists}.
 */
public class TmdbMovieListsTest extends AbstractTmdbApiTest<TmdbMovieLists> {
    @Override
    public TmdbMovieLists createApiToTest() {
        return getTmdbApi().getMovieLists();
    }

    /**
     * Test {@link TmdbMovieLists#getNowPlaying(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetNowPlaying() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movie_lists/now_playing.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_LISTS + "/now_playing?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPageWithDates movieResultsPageWithDates = getApiToTest().getNowPlaying("en-US", 1, null);
        assertNotNull(movieResultsPageWithDates);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.movielists.MovieResultsPageWithDates.results.originCountry"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movieResultsPageWithDates, validatorConfig);
    }

    /**
     * Test {@link TmdbMovieLists#getPopular(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetPopular() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movie_lists/popular.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_LISTS + "/popular?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResultsPage = getApiToTest().getPopular("en-US", 1, null);
        assertNotNull(movieResultsPage);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of("info.movito.themoviedbapi.model.core.MovieResultsPage.results.originCountry"))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movieResultsPage, validatorConfig);
    }

    /**
     * Test {@link TmdbMovieLists#getTopRated(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetTopRated() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movie_lists/top_rated.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_LISTS + "/top_rated?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResultsPage = getApiToTest().getTopRated("en-US", 1, null);
        assertNotNull(movieResultsPage);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of("info.movito.themoviedbapi.model.core.MovieResultsPage.results.originCountry"))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movieResultsPage, validatorConfig);
    }

    /**
     * Test {@link TmdbMovieLists#getUpcoming(String, Integer, String)} with an expected result.
     */
    @Test
    public void testGetUpcoming() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movie_lists/upcoming.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_MOVIE_LISTS + "/upcoming?language=en-US&page=1";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPageWithDates movieResultsPageWithDates = getApiToTest().getUpcoming("en-US", 1, null);
        assertNotNull(movieResultsPageWithDates);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "info.movito.themoviedbapi.model.movielists.MovieResultsPageWithDates.results.originCountry"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movieResultsPageWithDates, validatorConfig);
    }
}
