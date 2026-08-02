package uk.co.conoregan.themoviedbapi;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.find.FindResults;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.testutil.ValidatorConfig;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;
import uk.co.conoregan.themoviedbapi.tools.model.time.ExternalSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbFind.TMDB_METHOD_FIND;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

/**
 * Tests for {@link TmdbFind}.
 */
public class TmdbFindTest extends AbstractTmdbApiTest<TmdbFind> {
    @Override
    public TmdbFind createApiToTest() {
        return getTmdbApi().getFind();
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with movie results.
     */
    @Test
    public void testFindByIdMovieResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/movie_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        FindResults findResults = getApiToTest().findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.personResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvSeriesResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvSeasonResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvEpisodeResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.movieResults.originCountry"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(findResults, validatorConfig);
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with person results.
     */
    @Test
    public void testFindByIdPersonResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/person_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        FindResults findResults = getApiToTest().findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.movieResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvSeriesResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvSeasonResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvEpisodeResults"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(findResults, validatorConfig);
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with TV results.
     */
    @Test
    public void testFindByIdTvResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/tv_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        FindResults findResults = getApiToTest().findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.movieResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.personResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvSeasonResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvEpisodeResults"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(findResults, validatorConfig);
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with TV season results.
     */
    @Test
    public void testFindByIdTvSeasonResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/tv_season_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        FindResults findResults = getApiToTest().findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.movieResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.personResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvSeriesResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvEpisodeResults"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(findResults, validatorConfig);
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with TV episode results.
     */
    @Test
    public void testFindByIdTvEpisodeResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/tv_episode_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        FindResults findResults = getApiToTest().findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .emptyCollectionFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.movieResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.personResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvSeriesResults",
                "uk.co.conoregan.themoviedbapi.model.find.FindResults.tvSeasonResults"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(findResults, validatorConfig);
    }
}
