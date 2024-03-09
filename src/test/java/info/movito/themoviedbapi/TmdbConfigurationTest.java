package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.configuration.Configuration;
import info.movito.themoviedbapi.model.configuration.Country;
import info.movito.themoviedbapi.model.configuration.Job;
import info.movito.themoviedbapi.model.core.Language;
import info.movito.themoviedbapi.model.configuration.Timezone;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static info.movito.themoviedbapi.TmdbConfiguration.TMDB_METHOD_CONFIGURATION;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbConfiguration}.
 */
public class TmdbConfigurationTest extends AbstractTmdbApiTest {
    /**
     * Test for {@link TmdbConfiguration#getDetails()} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        Configuration configuration = tmdbConfiguration.getDetails();
        assertNotNull(configuration);
        validateAbstractJsonMappingFields(configuration);
    }

    /**
     * Test for {@link TmdbConfiguration#getCountries(String)} with an expected result.
     */
    @Test
    public void testGetCountries() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/countries.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/countries");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<Country> countries = tmdbConfiguration.getCountries(null);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country country = countries.get(0);
        assertNotNull(country);
        validateAbstractJsonMappingFields(country);
    }

    /**
     * Test for {@link TmdbConfiguration#getJobs()} with an expected result.
     */
    @Test
    public void testGetJobs() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/jobs.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/jobs");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<Job> jobs = tmdbConfiguration.getJobs();
        assertNotNull(jobs);
        assertFalse(jobs.isEmpty());

        Job job = jobs.get(0);
        assertNotNull(job);
        validateAbstractJsonMappingFields(job);
    }

    /**
     * Test for {@link TmdbConfiguration#getLanguages()} with an expected result.
     */
    @Test
    public void testGetLanguages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/languages.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/languages");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<Language> languages = tmdbConfiguration.getLanguages();
        assertNotNull(languages);
        assertFalse(languages.isEmpty());

        Language language = languages.get(0);
        assertNotNull(language);
        validateAbstractJsonMappingFields(language);
    }

    /**
     * Test for {@link TmdbConfiguration#getPrimaryTranslations()} with an expected result.
     */
    @Test
    public void testGetPrimaryTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/primary_translations.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/primary_translations");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<String> primaryTranslations = tmdbConfiguration.getPrimaryTranslations();
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
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_CONFIGURATION + "/timezones");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<Timezone> timezones = tmdbConfiguration.getTimezones();
        assertNotNull(timezones);
        assertFalse(timezones.isEmpty());

        Timezone timezone = timezones.get(0);
        assertNotNull(timezone);
        validateAbstractJsonMappingFields(timezone);
    }
}
