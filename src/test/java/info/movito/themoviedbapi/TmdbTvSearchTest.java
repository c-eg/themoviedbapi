package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.model.search.CollectionResultsPage;
import info.movito.themoviedbapi.model.search.CompanyResultsPage;
import info.movito.themoviedbapi.model.search.KeywordResultsPage;
import info.movito.themoviedbapi.model.search.Multi;
import info.movito.themoviedbapi.model.search.MultiResultsPage;
import info.movito.themoviedbapi.model.search.SearchedMovie;
import info.movito.themoviedbapi.model.search.SearchedPerson;
import info.movito.themoviedbapi.model.search.SearchedTvSeries;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbSearch}.
 */
public class TmdbTvSearchTest extends AbstractTmdbApiTest {
    private TmdbSearch tmdbSearch;

    /**
     * Sets up TmdbTvSearchTest class.
     */
    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        tmdbSearch = getTmdbApi().getSearch();
    }

    /**
     * Tests {@link TmdbSearch#searchCollection(String, String, Boolean, Integer, String)} with an expected result.
     */
    @Test
    public void testSearchCollection() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/collection.json");
        URL url = new URL(TMDB_API_BASE_URL + "search/collection?query=batman&language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        CollectionResultsPage collectionResultsPage = tmdbSearch.searchCollection("batman", "en-US", null, null, null);
        assertNotNull(collectionResultsPage);
        validateAbstractJsonMappingFields(collectionResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchCompany(String, Integer)} with an expected result.
     */
    @Test
    public void testSearchCompany() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/company.json");
        URL url = new URL(TMDB_API_BASE_URL + "search/company?query=amici+films");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        CompanyResultsPage companyResultsPage = tmdbSearch.searchCompany("amici films", null);
        assertNotNull(companyResultsPage);
        validateAbstractJsonMappingFields(companyResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchKeyword(String, Integer)} with an expected result.
     */
    @Test
    public void testSearchKeyword() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/keyword.json");
        URL url = new URL(TMDB_API_BASE_URL + "search/keyword?query=autograph");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        KeywordResultsPage keywordResultsPage = tmdbSearch.searchKeyword("autograph", null);
        assertNotNull(keywordResultsPage);
        validateAbstractJsonMappingFields(keywordResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchMovie(String, Boolean, String, String, Integer, String, String)} with an expected result.
     */
    @Test
    public void testSearchMovie() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/movie.json");
        URL url = new URL(TMDB_API_BASE_URL + "search/movie?query=batman&language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResultsPage = tmdbSearch.searchMovie("batman", null, "en-US", null, null, null, null);
        assertNotNull(movieResultsPage);
        validateAbstractJsonMappingFields(movieResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchMulti(String, Boolean, String, Integer)} with an expected result.
     */
    @Test
    public void testSearchMulti() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/multi.json");
        URL url = new URL(TMDB_API_BASE_URL + "search/multi?query=batman&language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MultiResultsPage multiResultsPage = tmdbSearch.searchMulti("batman", null, "en-US", null);
        assertNotNull(multiResultsPage);
        validateAbstractJsonMappingFields(multiResultsPage);

        List<Multi> results = multiResultsPage.getResults();
        Multi multiMovie = results.get(0);
        assertNotNull(multiMovie);
        assertEquals(Multi.MediaType.MOVIE, multiMovie.getMediaType());
        validateAbstractJsonMappingFields((SearchedMovie) multiMovie);

        Multi multiTv = results.get(1);
        assertNotNull(multiTv);
        assertEquals(Multi.MediaType.TV_SERIES, multiTv.getMediaType());
        validateAbstractJsonMappingFields((SearchedTvSeries) multiTv);

        Multi multiPerson = results.get(2);
        assertNotNull(multiPerson);
        assertEquals(Multi.MediaType.PERSON, multiPerson.getMediaType());
        validateAbstractJsonMappingFields((SearchedPerson) multiPerson);
    }

    /**
     * Tests {@link TmdbSearch#searchPerson(String, Boolean, String, Integer)} with an expected result.
     */
    @Test
    public void testSearchPerson() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/person.json");
        URL url = new URL(TMDB_API_BASE_URL + "search/person?query=vin&language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        PopularPersonResultsPage personResultsPage = tmdbSearch.searchPerson("vin", null, "en-US", null);
        assertNotNull(personResultsPage);
        validateAbstractJsonMappingFields(personResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchTv(String, Integer, Boolean, String, Integer, Integer)} with an expected result.
     */
    @Test
    public void testSearchTv() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/tv.json");
        URL url = new URL(TMDB_API_BASE_URL + "search/tv?query=batman&language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = tmdbSearch.searchTv("batman", null, null, "en-US", null, null);
        assertNotNull(tvSeriesResultsPage);
        validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }
}
