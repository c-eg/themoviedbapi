package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.model.people.ExternalIds;
import info.movito.themoviedbapi.model.people.PersonDb;
import info.movito.themoviedbapi.model.people.PersonImages;
import info.movito.themoviedbapi.model.people.Translations;
import info.movito.themoviedbapi.model.people.credits.Cast;
import info.movito.themoviedbapi.model.people.credits.CombinedPersonCredits;
import info.movito.themoviedbapi.model.people.credits.Crew;
import info.movito.themoviedbapi.model.people.credits.MediaType;
import info.movito.themoviedbapi.model.people.credits.MovieCast;
import info.movito.themoviedbapi.model.people.credits.MovieCredits;
import info.movito.themoviedbapi.model.people.credits.MovieCrew;
import info.movito.themoviedbapi.model.people.credits.TvCast;
import info.movito.themoviedbapi.model.people.credits.TvCredits;
import info.movito.themoviedbapi.model.people.credits.TvCrew;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.PersonAppendToResponse;
import info.movito.themoviedbapi.util.AbstractJsonMappingValidator;
import info.movito.themoviedbapi.util.FieldsToIgnore;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static info.movito.themoviedbapi.TmdbPeople.TMDB_METHOD_PERSON;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.checkForNullAndEmptyFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbPeople}.
 */
public class TmdbPeopleTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbPeople#getDetails(int, String, PersonAppendToResponse...)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        PersonDb details = tmdbPeople.getDetails(123, "en-US");
        assertNotNull(details);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(details);
        List<FieldsToIgnore> fieldsToIgnore = new ArrayList<>();
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "changes", "changes"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "combinedCredits", "combinedCredits"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "externalIds", "externalIds"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "images", "images"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "latest", "latest"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "movieCredits", "movieCredits"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "tvCredits", "tvCredits"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "translations", "translations"));

        abstractJsonMappingValidator.validateNullFields(fieldsToIgnore);
        abstractJsonMappingValidator.validateEmptyCollections();
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Tests {@link TmdbPeople#getDetails(int, String, PersonAppendToResponse...)} with appended responses, with an expected result.
     */
    @Test
    public void testGetDetailsWithAppendToResponse() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/details_with_append_to_response.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123?language=en-US&" +
            "append_to_response=changes%2Ccombined_credits%2Cexternal_ids%2Cimages%2Clatest%2Cmovie_credits%2Ctv_credits%2Ctranslations");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        PersonDb details = tmdbPeople.getDetails(123, "en-US", PersonAppendToResponse.values());
        assertNotNull(details);
        checkForNullAndEmptyFieldsAndNewItems(details);
    }

    /**
     * Tests {@link TmdbPeople#getChanges(int, String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetChanges() throws IOException, TmdbException {
        String startDate = "2023-01-13";
        String endDate = "2023-01-14";
        int page = 1;

        String body = TestUtils.readTestFile("api_responses/people/changes.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON +
            "/123/changes?start_date=" + startDate + "&end_date=" + endDate + "&page=" + page);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        ChangeResults changes = tmdbPeople.getChanges(123, startDate, endDate, page);
        assertNotNull(changes);
        checkForNullAndEmptyFieldsAndNewItems(changes);
    }

    /**
     * Tests {@link TmdbPeople#getCombinedCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetCombinedCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/combined_credits.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/combined_credits?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        CombinedPersonCredits personCredits = tmdbPeople.getCombinedCredits(123, "en-US");
        assertNotNull(personCredits);
        checkForNullAndEmptyFieldsAndNewItems(personCredits);

        // this is not done in generic util way to make sure that the cast and crew are of the correct type
        List<Cast> cast = personCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        MovieCast movieCast = (MovieCast) cast.get(0);
        assertNotNull(movieCast);
        checkForNullAndEmptyFieldsAndNewItems(movieCast);
        TvCast tvCast = (TvCast) cast.get(1);
        assertNotNull(tvCast);
        checkForNullAndEmptyFieldsAndNewItems(tvCast);

        List<Crew> crew = personCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        MovieCrew movieCrew = (MovieCrew) crew.get(0);
        assertNotNull(movieCrew);
        checkForNullAndEmptyFieldsAndNewItems(movieCrew);
        TvCrew tvCrew = (TvCrew) crew.get(1);
        assertNotNull(tvCrew);
        checkForNullAndEmptyFieldsAndNewItems(tvCrew);
    }

    /**
     * Tests {@link TmdbPeople#getExternalIds(int)} with an expected result.
     */
    @Test
    public void testExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/external_ids.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/external_ids");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        ExternalIds externalIds = tmdbPeople.getExternalIds(123);
        assertNotNull(externalIds);
        checkForNullAndEmptyFieldsAndNewItems(externalIds);
    }

    /**
     * Tests {@link TmdbPeople#getImages(int)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/images.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/images");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        PersonImages images = tmdbPeople.getImages(123);
        assertNotNull(images);
        checkForNullAndEmptyFieldsAndNewItems(images);
    }

    /**
     * Tests {@link TmdbPeople#getLatest()} with an expected result.
     */
    @Test
    public void testGetLatest() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/latest.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/latest");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        PersonDb latest = tmdbPeople.getLatest();
        assertNotNull(latest);

        AbstractJsonMappingValidator abstractJsonMappingValidator = new AbstractJsonMappingValidator(latest);
        List<FieldsToIgnore> fieldsToIgnore = new ArrayList<>();
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "changes", "changes"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "combinedCredits", "combinedCredits"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "externalIds", "externalIds"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "images", "images"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "latest", "latest"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "movieCredits", "movieCredits"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "tvCredits", "tvCredits"));
        fieldsToIgnore.add(new FieldsToIgnore(PersonDb.class, "translations", "translations"));

        abstractJsonMappingValidator.validateNullFields(fieldsToIgnore);
        abstractJsonMappingValidator.validateEmptyCollections();
        abstractJsonMappingValidator.validateNullContainingCollection();
        abstractJsonMappingValidator.validateEmptyMaps();
        abstractJsonMappingValidator.validateNullContainingMaps();
        abstractJsonMappingValidator.validateNewItems();
    }

    /**
     * Tests {@link TmdbPeople#getMovieCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetMovieCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/movie_credits.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/movie_credits?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        MovieCredits movieCredits = tmdbPeople.getMovieCredits(123, "en-US");
        assertNotNull(movieCredits);
        checkForNullAndEmptyFieldsAndNewItems(movieCredits);

        // this is not done in generic util way to make sure that the cast and crew are of the correct type
        List<MovieCast> cast = movieCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        cast.forEach(c -> assertEquals(c.getMediaType(), MediaType.MOVIE));

        MovieCast movieCast = cast.get(0);
        assertNotNull(movieCast);
        checkForNullAndEmptyFieldsAndNewItems(movieCast);

        List<MovieCrew> crew = movieCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        crew.forEach(c -> assertEquals(c.getMediaType(), MediaType.MOVIE));

        MovieCrew movieCrew = crew.get(0);
        assertNotNull(movieCrew);
        checkForNullAndEmptyFieldsAndNewItems(movieCrew);
    }

    /**
     * Tests {@link TmdbPeople#getTvCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetTvCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/tv_credits.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/tv_credits?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        TvCredits tvCredits = tmdbPeople.getTvCredits(123, "en-US");
        assertNotNull(tvCredits);
        checkForNullAndEmptyFieldsAndNewItems(tvCredits);

        // this is not done in generic util way to make sure that the cast and crew are of the correct type
        List<TvCast> cast = tvCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        cast.forEach(c -> assertEquals(c.getMediaType(), MediaType.TV));

        TvCast tvCast = cast.get(0);
        assertNotNull(tvCast);
        checkForNullAndEmptyFieldsAndNewItems(tvCast);

        List<TvCrew> crew = tvCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        crew.forEach(c -> assertEquals(c.getMediaType(), MediaType.TV));

        TvCrew tvCrew = crew.get(0);
        assertNotNull(tvCrew);
        checkForNullAndEmptyFieldsAndNewItems(tvCrew);
    }

    /**
     * Tests {@link TmdbPeople#getTranslations(int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/translations.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/translations");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbPeople tmdbPeople = getTmdbApi().getPeople();
        Translations translations = tmdbPeople.getTranslations(123);
        assertNotNull(translations);
        checkForNullAndEmptyFieldsAndNewItems(translations);
    }
}
