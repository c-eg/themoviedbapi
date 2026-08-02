package uk.co.conoregan.themoviedbapi;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.movies.changes.ChangeResults;
import uk.co.conoregan.themoviedbapi.model.people.ExternalIds;
import uk.co.conoregan.themoviedbapi.model.people.PersonDb;
import uk.co.conoregan.themoviedbapi.model.people.PersonImages;
import uk.co.conoregan.themoviedbapi.model.people.Translations;
import uk.co.conoregan.themoviedbapi.model.people.credits.Cast;
import uk.co.conoregan.themoviedbapi.model.people.credits.CombinedPersonCredits;
import uk.co.conoregan.themoviedbapi.model.people.credits.Crew;
import uk.co.conoregan.themoviedbapi.model.people.credits.MediaType;
import uk.co.conoregan.themoviedbapi.model.people.credits.MovieCast;
import uk.co.conoregan.themoviedbapi.model.people.credits.MovieCredits;
import uk.co.conoregan.themoviedbapi.model.people.credits.MovieCrew;
import uk.co.conoregan.themoviedbapi.model.people.credits.TvCast;
import uk.co.conoregan.themoviedbapi.model.people.credits.TvCredits;
import uk.co.conoregan.themoviedbapi.model.people.credits.TvCrew;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.testutil.ValidatorConfig;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;
import uk.co.conoregan.themoviedbapi.tools.appendtoresponse.PersonAppendToResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbPeople.TMDB_METHOD_PERSON;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

/**
 * Tests for {@link TmdbPeople}.
 */
public class TmdbPeopleTest extends AbstractTmdbApiTest<TmdbPeople> {
    @Override
    public TmdbPeople createApiToTest() {
        return getTmdbApi().getPeople();
    }

    /**
     * Tests {@link TmdbPeople#getDetails(int, String, PersonAppendToResponse...)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        PersonDb details = getApiToTest().getDetails(123, "en-US");
        assertNotNull(details);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.changes",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.combinedCredits",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.externalIds",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.images",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.latest",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.movieCredits",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.tvCredits",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.translations"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(details, validatorConfig);
    }

    /**
     * Tests {@link TmdbPeople#getDetails(int, String, PersonAppendToResponse...)} with appended responses, with an expected result.
     */
    @Test
    public void testGetDetailsWithAppendToResponse() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/details_with_append_to_response.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123?language=en-US&" +
            "append_to_response=changes%2Ccombined_credits%2Cexternal_ids%2Cimages%2Clatest%2Cmovie_credits%2Ctv_credits%2Ctranslations";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        PersonDb details = getApiToTest().getDetails(123, "en-US", PersonAppendToResponse.values());
        assertNotNull(details);
        TestUtils.validateAbstractJsonMappingFields(details);
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
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON +
            "/123/changes?start_date=" + startDate + "&end_date=" + endDate + "&page=" + page;
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ChangeResults changes = getApiToTest().getChanges(123, startDate, endDate, page);
        assertNotNull(changes);
        TestUtils.validateAbstractJsonMappingFields(changes);
    }

    /**
     * Tests {@link TmdbPeople#getCombinedCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetCombinedCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/combined_credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/combined_credits?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        CombinedPersonCredits personCredits = getApiToTest().getCombinedCredits(123, "en-US");
        assertNotNull(personCredits);
        TestUtils.validateAbstractJsonMappingFields(personCredits);

        // this is not done in generic util way to make sure that the cast and crew are of the correct type
        List<Cast> cast = personCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        MovieCast movieCast = (MovieCast) cast.get(0);
        assertNotNull(movieCast);
        TestUtils.validateAbstractJsonMappingFields(movieCast);
        TvCast tvCast = (TvCast) cast.get(1);
        assertNotNull(tvCast);
        TestUtils.validateAbstractJsonMappingFields(tvCast);

        List<Crew> crew = personCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        MovieCrew movieCrew = (MovieCrew) crew.get(0);
        assertNotNull(movieCrew);
        TestUtils.validateAbstractJsonMappingFields(movieCrew);
        TvCrew tvCrew = (TvCrew) crew.get(1);
        assertNotNull(tvCrew);
        TestUtils.validateAbstractJsonMappingFields(tvCrew);
    }

    /**
     * Tests {@link TmdbPeople#getExternalIds(int)} with an expected result.
     */
    @Test
    public void testExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/external_ids.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/external_ids";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ExternalIds externalIds = getApiToTest().getExternalIds(123);
        assertNotNull(externalIds);
        TestUtils.validateAbstractJsonMappingFields(externalIds);
    }

    /**
     * Tests {@link TmdbPeople#getImages(int)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/images";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        PersonImages images = getApiToTest().getImages(123);
        assertNotNull(images);
        TestUtils.validateAbstractJsonMappingFields(images);
    }

    /**
     * Tests {@link TmdbPeople#getLatest()} with an expected result.
     */
    @Test
    public void testGetLatest() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/latest.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/latest";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        PersonDb latest = getApiToTest().getLatest();
        assertNotNull(latest);

        ValidatorConfig validatorConfig = ValidatorConfig.builder()
            .nullFieldsToIgnore(List.of(
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.changes",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.combinedCredits",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.externalIds",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.images",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.latest",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.movieCredits",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.tvCredits",
                "uk.co.conoregan.themoviedbapi.model.people.PersonDb.translations"
            ))
            .build();
        TestUtils.validateAbstractJsonMappingFields(latest, validatorConfig);
    }

    /**
     * Tests {@link TmdbPeople#getMovieCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetMovieCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/movie_credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/movie_credits?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        MovieCredits movieCredits = getApiToTest().getMovieCredits(123, "en-US");
        assertNotNull(movieCredits);
        TestUtils.validateAbstractJsonMappingFields(movieCredits);

        // this is not done in generic util way to make sure that the cast and crew are of the correct type
        List<MovieCast> cast = movieCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        cast.forEach(c -> assertEquals(MediaType.MOVIE, c.getMediaType()));

        MovieCast movieCast = cast.get(0);
        assertNotNull(movieCast);
        TestUtils.validateAbstractJsonMappingFields(movieCast);

        List<MovieCrew> crew = movieCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        crew.forEach(c -> assertEquals(MediaType.MOVIE, c.getMediaType()));

        MovieCrew movieCrew = crew.get(0);
        assertNotNull(movieCrew);
        TestUtils.validateAbstractJsonMappingFields(movieCrew);
    }

    /**
     * Tests {@link TmdbPeople#getTvCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetTvCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/tv_credits.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/tv_credits?language=en-US";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvCredits tvCredits = getApiToTest().getTvCredits(123, "en-US");
        assertNotNull(tvCredits);
        TestUtils.validateAbstractJsonMappingFields(tvCredits);

        // this is not done in generic util way to make sure that the cast and crew are of the correct type
        List<TvCast> cast = tvCredits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        cast.forEach(c -> assertEquals(MediaType.TV, c.getMediaType()));

        TvCast tvCast = cast.get(0);
        assertNotNull(tvCast);
        TestUtils.validateAbstractJsonMappingFields(tvCast);

        List<TvCrew> crew = tvCredits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        crew.forEach(c -> assertEquals(MediaType.TV, c.getMediaType()));

        TvCrew tvCrew = crew.get(0);
        assertNotNull(tvCrew);
        TestUtils.validateAbstractJsonMappingFields(tvCrew);
    }

    /**
     * Tests {@link TmdbPeople#getTranslations(int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/people/translations.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_PERSON + "/123/translations";
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        Translations translations = getApiToTest().getTranslations(123);
        assertNotNull(translations);
        TestUtils.validateAbstractJsonMappingFields(translations);
    }
}
