package info.movito.themoviedbapi;

import java.io.IOException;
import java.util.List;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.multi.Multi;
import info.movito.themoviedbapi.model.core.multi.MultiMovie;
import info.movito.themoviedbapi.model.core.multi.MultiPerson;
import info.movito.themoviedbapi.model.core.multi.MultiResultsPage;
import info.movito.themoviedbapi.model.core.multi.MultiTvSeries;
import info.movito.themoviedbapi.model.core.popularperson.PopularPersonResultsPage;
import info.movito.themoviedbapi.model.search.CollectionResultsPage;
import info.movito.themoviedbapi.model.search.CompanyResultsPage;
import info.movito.themoviedbapi.model.search.KeywordResultsPage;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.testutil.ValidatorConfig;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbSearch}.
 */
public class TmdbTvSearchTest extends AbstractTmdbApiTest<TmdbSearch> {
    @Override
    public TmdbSearch createApiToTest() {
        return getTmdbApi().getSearch();
    }

    /**
     * Tests {@link TmdbSearch#searchCollection(String, String, Boolean, Integer, String)} with an expected result.
     */
    @Test
    public void testSearchCollection() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/collection.json");
        String url = TMDB_API_BASE_URL + "search/collection?query=batman&language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        CollectionResultsPage collectionResultsPage = getApiToTest().searchCollection("batman", "en-US", null, null, null);
        assertNotNull(collectionResultsPage);
        TestUtils.validateAbstractJsonMappingFields(collectionResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchCompany(String, Integer)} with an expected result.
     */
    @Test
    public void testSearchCompany() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/company.json");
        String url = TMDB_API_BASE_URL + "search/company?query=amici+films";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        CompanyResultsPage companyResultsPage = getApiToTest().searchCompany("amici films", null);
        assertNotNull(companyResultsPage);
        TestUtils.validateAbstractJsonMappingFields(companyResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchKeyword(String, Integer)} with an expected result.
     */
    @Test
    public void testSearchKeyword() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/keyword.json");
        String url = TMDB_API_BASE_URL + "search/keyword?query=autograph";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        KeywordResultsPage keywordResultsPage = getApiToTest().searchKeyword("autograph", null);
        assertNotNull(keywordResultsPage);
        TestUtils.validateAbstractJsonMappingFields(keywordResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchMovie(String, Boolean, String, String, Integer, String, String)} with an expected result.
     */
    @Test
    public void testSearchMovie() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/movie.json");
        String url = TMDB_API_BASE_URL + "search/movie?query=batman&language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MovieResultsPage movieResultsPage = getApiToTest().searchMovie("batman", null, "en-US", null, null, null, null);
        assertNotNull(movieResultsPage);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of("info.movito.themoviedbapi.model.core.MovieResultsPage.results.originCountry"))
            .build();
        TestUtils.validateAbstractJsonMappingFields(movieResultsPage, validatorConfig);
    }

    /**
     * Tests {@link TmdbSearch#searchMulti(String, Boolean, String, Integer)} with an expected result.
     */
    @Test
    public void testSearchMulti() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/multi.json");
        String url = TMDB_API_BASE_URL + "search/multi?query=batman&language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        MultiResultsPage multiResultsPage = getApiToTest().searchMulti("batman", null, "en-US", null);
        assertNotNull(multiResultsPage);

        ValidatorConfig multiValidatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of("info.movito.themoviedbapi.model.core.multi.MultiResultsPage.results.originCountry"))
            .build();
        TestUtils.validateAbstractJsonMappingFields(multiResultsPage, multiValidatorConfig);

        List<Multi> results = multiResultsPage.getResults();
        Multi multiMovie = results.get(0);
        assertNotNull(multiMovie);
        assertEquals(Multi.MediaType.MOVIE, multiMovie.getMediaType());

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of("info.movito.themoviedbapi.model.core.multi.MultiMovie.originCountry"))
            .build();
        TestUtils.validateAbstractJsonMappingFields((MultiMovie) multiMovie, validatorConfig);

        Multi multiTv = results.get(1);
        assertNotNull(multiTv);
        assertEquals(Multi.MediaType.TV_SERIES, multiTv.getMediaType());
        TestUtils.validateAbstractJsonMappingFields((MultiTvSeries) multiTv);

        Multi multiPerson = results.get(2);
        assertNotNull(multiPerson);
        assertEquals(Multi.MediaType.PERSON, multiPerson.getMediaType());
        TestUtils.validateAbstractJsonMappingFields((MultiPerson) multiPerson);
    }

    /**
     * Tests {@link TmdbSearch#searchPerson(String, Boolean, String, Integer)} with an expected result.
     */
    @Test
    public void testSearchPerson() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/person.json");
        String url = TMDB_API_BASE_URL + "search/person?query=vin&language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        PopularPersonResultsPage personResultsPage = getApiToTest().searchPerson("vin", null, "en-US", null);
        assertNotNull(personResultsPage);
        TestUtils.validateAbstractJsonMappingFields(personResultsPage);
    }

    /**
     * Tests {@link TmdbSearch#searchTv(String, Integer, Boolean, String, Integer, Integer)} with an expected result.
     */
    @Test
    public void testSearchTv() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/search/tv.json");
        String url = TMDB_API_BASE_URL + "search/tv?query=batman&language=en-US";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvSeriesResultsPage tvSeriesResultsPage = getApiToTest().searchTv("batman", null, null, "en-US", null, null);
        assertNotNull(tvSeriesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(tvSeriesResultsPage);
    }
}
