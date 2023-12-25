package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.configuration.Job;
import info.movito.themoviedbapi.model.configuration.Configuration;
import info.movito.themoviedbapi.model.configuration.Country;
import info.movito.themoviedbapi.model.configuration.Language;
import info.movito.themoviedbapi.model.configuration.Timezone;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link TmdbConfiguration}.
 */
public class TmdbConfigurationTest extends AbstractTmdbApiTest {
    /**
     * Test for {@link TmdbConfiguration#getConfig()} with an expected result.
     */
    @Test
    public void testGetConfig() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/details.json");
        mockResponse(body, 200);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        Configuration configuration = tmdbConfiguration.getConfig();
        assertNotNull(configuration);
        testForNullFieldsAndUnknownProperties(configuration);
    }

    /**
     * Test for {@link TmdbConfiguration#getCountries(String)} with an expected result.
     */
    @Test
    public void testGetCountries() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/countries.json");
        mockResponse(body, 200);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<Country> countries = tmdbConfiguration.getCountries(null);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Country country = countries.get(0);
        assertNotNull(country);
        testForNullFieldsAndUnknownProperties(country);
    }

    /**
     * Test for {@link TmdbConfiguration#getJobs()} with an expected result.
     */
    @Test
    public void testGetJobs() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/jobs.json");
        mockResponse(body, 200);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<Job> jobs = tmdbConfiguration.getJobs();
        assertNotNull(jobs);
        assertFalse(jobs.isEmpty());

        Job job = jobs.get(0);
        assertNotNull(job);
        testForNullFieldsAndUnknownProperties(job);
    }

    /**
     * Test for {@link TmdbConfiguration#getLanguages()} with an expected result.
     */
    @Test
    public void testGetLanguages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/languages.json");
        mockResponse(body, 200);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<Language> languages = tmdbConfiguration.getLanguages();
        assertNotNull(languages);
        assertFalse(languages.isEmpty());

        Language language = languages.get(0);
        assertNotNull(language);
        testForNullFieldsAndUnknownProperties(language);
    }

    /**
     * Test for {@link TmdbConfiguration#getPrimaryTranslations()} with an expected result.
     */
    @Test
    public void testGetPrimaryTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/configuration/primary_translations.json");
        mockResponse(body, 200);

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
        mockResponse(body, 200);

        TmdbConfiguration tmdbConfiguration = getTmdbApi().getConfiguration();
        List<Timezone> timezones = tmdbConfiguration.getTimezones();
        assertNotNull(timezones);
        assertFalse(timezones.isEmpty());

        Timezone timezone = timezones.get(0);
        assertNotNull(timezone);
        testForNullFieldsAndUnknownProperties(timezone);
    }
}
