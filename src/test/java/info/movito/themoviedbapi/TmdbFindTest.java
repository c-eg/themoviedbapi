package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.find.FindResults;
import info.movito.themoviedbapi.testutil.AbstractJsonMappingValidator;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.model.time.ExternalSource;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbFind.TMDB_METHOD_FIND;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbFind tmdbFind = new TmdbFind(getTmdbApi());
        FindResults findResults = tmdbFind.findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(findResults);
        List<String> filteredModel = new ArrayList<>();
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.personResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvSeriesResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvSeasonResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvEpisodeResults");

        abstractJsonMappingValidator.validateNullFields();
        abstractJsonMappingValidator.validateEmptyCollections(filteredModel);
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with person results.
     */
    @Test
    public void testFindByIdPersonResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/person_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbFind tmdbFind = new TmdbFind(getTmdbApi());
        FindResults findResults = tmdbFind.findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(findResults);
        List<String> filteredModel = new ArrayList<>();
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.movieResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvSeriesResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvSeasonResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvEpisodeResults");

        abstractJsonMappingValidator.validateNullFields();
        abstractJsonMappingValidator.validateEmptyCollections(filteredModel);
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with TV results.
     */
    @Test
    public void testFindByIdTvResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/tv_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbFind tmdbFind = new TmdbFind(getTmdbApi());
        FindResults findResults = tmdbFind.findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(findResults);
        List<String> filteredModel = new ArrayList<>();
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.movieResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.personResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvSeasonResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvEpisodeResults");

        abstractJsonMappingValidator.validateNullFields();
        abstractJsonMappingValidator.validateEmptyCollections(filteredModel);
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with TV season results.
     */
    @Test
    public void testFindByIdTvSeasonResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/tv_season_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbFind tmdbFind = new TmdbFind(getTmdbApi());
        FindResults findResults = tmdbFind.findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(findResults);
        List<String> filteredModel = new ArrayList<>();
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.movieResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.personResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvSeriesResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvEpisodeResults");

        abstractJsonMappingValidator.validateNullFields();
        abstractJsonMappingValidator.validateEmptyCollections(filteredModel);
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Test for {@link TmdbFind#findById(String, ExternalSource, String)} with TV episode results.
     */
    @Test
    public void testFindByIdTvEpisodeResults() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/find/tv_episode_results.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_FIND + "/nm0000158?external_source=imdb_id";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbFind tmdbFind = new TmdbFind(getTmdbApi());
        FindResults findResults = tmdbFind.findById("nm0000158", ExternalSource.IMDB_ID, null);
        assertNotNull(findResults);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(findResults);
        List<String> filteredModel = new ArrayList<>();
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.movieResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.personResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvSeriesResults");
        filteredModel.add("info.movito.themoviedbapi.model.find.FindResults.tvSeasonResults");

        abstractJsonMappingValidator.validateNullFields();
        abstractJsonMappingValidator.validateEmptyCollections(filteredModel);
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }
}
