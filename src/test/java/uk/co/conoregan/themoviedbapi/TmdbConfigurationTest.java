package uk.co.conoregan.themoviedbapi;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.configuration.Configuration;
import uk.co.conoregan.themoviedbapi.model.configuration.Country;
import uk.co.conoregan.themoviedbapi.model.configuration.Job;
import uk.co.conoregan.themoviedbapi.model.configuration.Timezone;
import uk.co.conoregan.themoviedbapi.model.core.Language;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbConfiguration.TMDB_METHOD_CONFIGURATION;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

/**
 * Tests for {@link TmdbConfiguration}.
 */
public class TmdbConfigurationTest extends AbstractTmdbApiTest<TmdbConfiguration> {
    @Override
    public TmdbConfiguration createApiToTest() {
        return getTmdbApi().getConfiguration();
    }

    /**
     * Test for {@link TmdbConfiguration#getDetails()} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION;
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Configuration configuration = getApiToTest().getDetails();
        assertNotNull(configuration);
        TestUtils.validateAbstractJsonMappingFields(configuration);
    }

    /**
     * Test for {@link TmdbConfiguration#getCountries(String)} with an expected result.
     */
    @Test
    public void testGetCountries() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/countries.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/countries";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        List<Country> countries = getApiToTest().getCountries(null);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country country = countries.get(0);
        assertNotNull(country);
        TestUtils.validateAbstractJsonMappingFields(country);
    }

    /**
     * Test for {@link TmdbConfiguration#getJobs()} with an expected result.
     */
    @Test
    public void testGetJobs() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/jobs.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/jobs";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        List<Job> jobs = getApiToTest().getJobs();
        assertNotNull(jobs);
        assertFalse(jobs.isEmpty());

        Job job = jobs.get(0);
        assertNotNull(job);
        TestUtils.validateAbstractJsonMappingFields(job);
    }

    /**
     * Test for {@link TmdbConfiguration#getLanguages()} with an expected result.
     */
    @Test
    public void testGetLanguages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/languages.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/languages";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        List<Language> languages = getApiToTest().getLanguages();
        assertNotNull(languages);
        assertFalse(languages.isEmpty());

        Language language = languages.get(0);
        assertNotNull(language);
        TestUtils.validateAbstractJsonMappingFields(language);
    }

    /**
     * Test for {@link TmdbConfiguration#getPrimaryTranslations()} with an expected result.
     */
    @Test
    public void testGetPrimaryTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/primary_translations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/primary_translations";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        List<String> primaryTranslations = getApiToTest().getPrimaryTranslations();
        assertNotNull(primaryTranslations);
        assertFalse(primaryTranslations.isEmpty());

        String primaryTranslation = primaryTranslations.get(0);
        assertNotNull(primaryTranslation);
    }

    /**
     * Test for {@link TmdbConfiguration#getTimezones()} with an expected result.
     */
    @Test
    public void testGetTimezones() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/timezones.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/timezones";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        List<Timezone> timezones = getApiToTest().getTimezones();
        assertNotNull(timezones);
        assertFalse(timezones.isEmpty());

        Timezone timezone = timezones.get(0);
        assertNotNull(timezone);
        TestUtils.validateAbstractJsonMappingFields(timezone);
    }
}
