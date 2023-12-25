package info.movito.themoviedbapi;

import com.fasterxml.jackson.core.type.TypeReference;
import info.movito.themoviedbapi.model.configuration.Job;
import info.movito.themoviedbapi.model.configuration.Configuration;
import info.movito.themoviedbapi.model.configuration.Country;
import info.movito.themoviedbapi.model.configuration.Language;
import info.movito.themoviedbapi.model.configuration.Timezone;
import info.movito.themoviedbapi.tools.ApiEndpoint;
import info.movito.themoviedbapi.tools.TmdbException;

import java.util.List;

/**
 * The movie database api for configuration. See the
 * <a href="https://developer.themoviedb.org/reference/configuration-details">documentation</a> for more info.
 */
public class TmdbConfiguration extends AbstractTmdbApi {
    private static final String TMDB_METHOD_CONFIGURATION = "configuration";

    /**
     * Create a new TmdbConfig instance to call the config related TMDb API methods.
     */
    TmdbConfiguration(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * <p>Query the API configuration details.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/configuration-details">documentation</a> for more info.</p>
     *
     * @return The configuration details
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public Configuration getConfig() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CONFIGURATION);
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, Configuration.class);
    }

    /**
     * <p>Get the list of countries (ISO 3166-1 tags) used throughout TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/configuration-countries">documentation</a> for more info.</p>
     *
     * @param language optional - The language to return the results in.
     * @return The configuration details
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Country> getCountries(String language) throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CONFIGURATION, "countries");
        apiEndpoint.addLanguage(language);
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, new TypeReference<>(){});
    }

    /**
     * <p>Get the list of jobs and departments we use on TMDb.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/configuration-jobs">documentation</a> for more info.</p>
     *
     * @return The list of jobs and departments
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Job> getJobs() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CONFIGURATION, "jobs");
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, new TypeReference<>(){});
    }

    /**
     * <p>Get the list of languages (ISO 639-1 tags) used throughout TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/configuration-languages">documentation</a> for more info.</p>
     *
     * @return The list of languages
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Language> getLanguages() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CONFIGURATION, "languages");
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, new TypeReference<>(){});
    }

    /**
     * <p>Get the list of languages (ISO 639-1 tags) used throughout TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/configuration-primary-translations">documentation</a>
     * for more info.</p>
     *
     * @return The list of languages
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<String> getPrimaryTranslations() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CONFIGURATION, "primary_translations");
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, new TypeReference<>(){});
    }

    /**
     * <p>Get the list of timezones used throughout TMDB.</p>
     * <p>See the <a href="https://developer.themoviedb.org/reference/configuration-timezones">documentation</a> for more info.</p>
     *
     * @return The list of timezones
     * @throws TmdbException If there was an error making the request or mapping the response.
     */
    public List<Timezone> getTimezones() throws TmdbException {
        ApiEndpoint apiEndpoint = new ApiEndpoint(TMDB_METHOD_CONFIGURATION, "timezones");
        String responseBody = makeGetRequest(apiEndpoint);
        return mapJsonResult(responseBody, new TypeReference<>(){});
    }
}
