package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Artwork;
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
import info.movito.themoviedbapi.model.movies.changes.Change;
import info.movito.themoviedbapi.model.movies.changes.ChangeItem;
import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.model.people.Data;
import info.movito.themoviedbapi.model.people.ExternalIds;
import info.movito.themoviedbapi.model.people.PersonDb;
import info.movito.themoviedbapi.model.people.PersonImages;
import info.movito.themoviedbapi.model.people.Translation;
import info.movito.themoviedbapi.model.people.Translations;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.PersonAppendToResponse;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static info.movito.themoviedbapi.TmdbPeople.TMDB_METHOD_PERSON;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.testForNewItems;
import static info.movito.themoviedbapi.util.TestUtils.testForNullFields;
import static info.movito.themoviedbapi.util.TestUtils.testForNullFieldsAndNewItems;
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
        testForNullFields(details, "changes", "combinedCredits", "externalIds", "images", "latest", "movieCredits", "tvCredits",
            "translations");
        testForNewItems(details);

        List<String> alsoKnownAs = details.getAlsoKnownAs();
        assertNotNull(alsoKnownAs);
        assertFalse(alsoKnownAs.isEmpty());
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
        testForNullFieldsAndNewItems(details);

        List<String> alsoKnownAs = details.getAlsoKnownAs();
        assertNotNull(alsoKnownAs);
        assertFalse(alsoKnownAs.isEmpty());
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
        testForNullFieldsAndNewItems(changes);

        List<Change> changedItems = changes.getChangedItems();
        assertNotNull(changedItems);
        assertFalse(changedItems.isEmpty());

        Change change = changedItems.get(0);
        assertNotNull(change);
        testForNullFieldsAndNewItems(change);

        List<ChangeItem> changeItems = change.getChangeItems();
        assertNotNull(changeItems);
        assertFalse(changeItems.isEmpty());

        ChangeItem changeItem = changeItems.get(0);
        assertNotNull(changeItem);
        testForNullFieldsAndNewItems(changeItem);
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
        testForNullFieldsAndNewItems(personCredits);

        List<Cast> cast = personCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        MovieCast movieCast = (MovieCast) cast.get(0);
        assertNotNull(movieCast);
        testForNullFieldsAndNewItems(movieCast);
        TvCast tvCast = (TvCast) cast.get(1);
        assertNotNull(tvCast);
        testForNullFieldsAndNewItems(tvCast);

        List<Crew> crew = personCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        MovieCrew movieCrew = (MovieCrew) crew.get(0);
        assertNotNull(movieCrew);
        testForNullFieldsAndNewItems(movieCrew);
        TvCrew tvCrew = (TvCrew) crew.get(1);
        assertNotNull(tvCrew);
        testForNullFieldsAndNewItems(tvCrew);
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
        testForNullFieldsAndNewItems(externalIds);
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
        testForNullFieldsAndNewItems(images);

        List<Artwork> profiles = images.getProfiles();
        assertNotNull(profiles);
        assertFalse(profiles.isEmpty());

        Artwork profile = profiles.get(0);
        assertNotNull(profile);
        testForNullFieldsAndNewItems(profile);
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
        testForNullFields(latest, "changes", "combinedCredits", "externalIds", "images", "latest", "movieCredits", "tvCredits",
            "translations");
        testForNewItems(latest);

        List<String> alsoKnownAs = latest.getAlsoKnownAs();
        assertNotNull(alsoKnownAs);
        assertFalse(alsoKnownAs.isEmpty());

        String alsoKnownAsValue = alsoKnownAs.get(0);
        assertNotNull(alsoKnownAsValue);
        assertFalse(alsoKnownAsValue.isEmpty());
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
        testForNullFieldsAndNewItems(movieCredits);

        List<MovieCast> cast = movieCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        cast.forEach(c -> assertEquals(c.getMediaType(), MediaType.MOVIE));

        MovieCast movieCast = cast.get(0);
        assertNotNull(movieCast);
        testForNullFieldsAndNewItems(movieCast);

        List<MovieCrew> crew = movieCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        crew.forEach(c -> assertEquals(c.getMediaType(), MediaType.MOVIE));

        MovieCrew movieCrew = crew.get(0);
        assertNotNull(movieCrew);
        testForNullFieldsAndNewItems(movieCrew);
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
        testForNullFieldsAndNewItems(tvCredits);

        List<TvCast> cast = tvCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        cast.forEach(c -> assertEquals(c.getMediaType(), MediaType.TV));

        TvCast tvCast = cast.get(0);
        assertNotNull(tvCast);
        testForNullFieldsAndNewItems(tvCast);

        List<TvCrew> crew = tvCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        crew.forEach(c -> assertEquals(c.getMediaType(), MediaType.TV));

        TvCrew tvCrew = crew.get(0);
        assertNotNull(tvCrew);
        testForNullFieldsAndNewItems(tvCrew);
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
        testForNullFieldsAndNewItems(translations);

        List<Translation> translationList = translations.getTranslations();
        assertNotNull(translationList);
        assertFalse(translationList.isEmpty());

        Translation translation = translationList.get(0);
        assertNotNull(translation);
        testForNullFieldsAndNewItems(translation);

        Data data = translation.getData();
        assertNotNull(data);
        testForNullFieldsAndNewItems(data);
    }
}
