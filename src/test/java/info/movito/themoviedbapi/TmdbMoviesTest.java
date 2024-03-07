package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.AlternativeTitle;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.ProductionCompany;
import info.movito.themoviedbapi.model.ProductionCountry;
import info.movito.themoviedbapi.model.ReleaseDate;
import info.movito.themoviedbapi.model.ReleaseInfo;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.model.core.Language;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.responses.ResponseStatus;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.movies.AccountStates;
import info.movito.themoviedbapi.model.movies.AlternativeTitles;
import info.movito.themoviedbapi.model.movies.Cast;
import info.movito.themoviedbapi.model.movies.Credits;
import info.movito.themoviedbapi.model.movies.Crew;
import info.movito.themoviedbapi.model.movies.Data;
import info.movito.themoviedbapi.model.movies.ExternalIds;
import info.movito.themoviedbapi.model.movies.Images;
import info.movito.themoviedbapi.model.movies.KeywordResults;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.model.movies.MovieList;
import info.movito.themoviedbapi.model.movies.MovieListResultsPage;
import info.movito.themoviedbapi.model.movies.Rated;
import info.movito.themoviedbapi.model.movies.ReleaseDateResults;
import info.movito.themoviedbapi.model.movies.Review;
import info.movito.themoviedbapi.model.movies.ReviewResultsPage;
import info.movito.themoviedbapi.model.movies.Translation;
import info.movito.themoviedbapi.model.movies.Translations;
import info.movito.themoviedbapi.model.movies.VideoResults;
import info.movito.themoviedbapi.model.movies.changes.Change;
import info.movito.themoviedbapi.model.movies.changes.ChangeItem;
import info.movito.themoviedbapi.model.movies.changes.ChangeResults;
import info.movito.themoviedbapi.model.providers.Provider;
import info.movito.themoviedbapi.model.providers.ProviderResults;
import info.movito.themoviedbapi.model.providers.WatchProviders;
import info.movito.themoviedbapi.model.reviews.AuthorDetails;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.MovieAppendToResponse;
import info.movito.themoviedbapi.util.TestUtils;
import info.movito.themoviedbapi.util.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static info.movito.themoviedbapi.AbstractTmdbApi.getObjectMapper;
import static info.movito.themoviedbapi.TmdbMovies.TMDB_METHOD_MOVIE;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.testForNewItems;
import static info.movito.themoviedbapi.util.TestUtils.testForNullFields;
import static info.movito.themoviedbapi.util.TestUtils.checkForNullAndEmptyFieldsAndNewItems;
import static info.movito.themoviedbapi.util.TestUtils.testForNestedEmptyCollectionsAndNullObjects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests for throws IOException, TmdbException {@link TmdbMovies}.
 */
public class TmdbMoviesTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbMovies#getDetails(int, String, MovieAppendToResponse...)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieDb movie = tmdbMovies.getDetails(123, "en-US");
        assertNotNull(movie);
        testForNullFields(movie, "accountStates", "alternativeTitles", "credits", "changes", "externalIds", "images", "keywords",
            "recommendations", "releaseDates", "lists", "reviews", "similar", "translations", "videos", "watchProviders");
        testForNewItems(movie);
        testForNestedEmptyCollectionsAndNullObjects(movie);

        List<Genre> genres = movie.getGenres();
        assertNotNull(genres);
        assertFalse(genres.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(genres.get(0));

        List<ProductionCompany> productionCompanies = movie.getProductionCompanies();
        assertNotNull(productionCompanies);
        assertFalse(productionCompanies.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(productionCompanies.get(0));

        List<ProductionCountry> productionCountries = movie.getProductionCountries();
        assertNotNull(productionCountries);
        assertFalse(productionCountries.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(productionCountries.get(0));

        List<Language> spokenLanguages = movie.getSpokenLanguages();
        assertNotNull(spokenLanguages);
        assertFalse(spokenLanguages.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(spokenLanguages.get(0));
    }

    /**
     * Tests {@link TmdbMovies#getDetails(int, String, MovieAppendToResponse...)} with appended responses, with an expected result.
     */
    @Test
    public void testGetDetailsWithAppendToResponse() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/details_with_append_to_response.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123?language=en-US&append_to_response=account_states%2C" +
            "alternative_titles%2Ccredits%2Cchanges%2Cexternal_ids%2Cimages%2Ckeywords%2Clists%2Crecommendations%2Crelease_dates%2C" +
            "reviews%2Csimilar%2Ctranslations%2Cvideos%2Cwatch%2Fproviders");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieDb movie = tmdbMovies.getDetails(123, "en-US", MovieAppendToResponse.values());
        assertNotNull(movie);
        checkForNullAndEmptyFieldsAndNewItems(movie);
    }

    /**
     * Tests {@link TmdbMovies#getAccountStates(int, String, String)} with an expected result.
     */
    @Test
    public void testGetAccountStates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/account_states.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/account_states?session_id=123");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        AccountStates accountStates = tmdbMovies.getAccountStates(123, "123", null);
        assertNotNull(accountStates);
        checkForNullAndEmptyFieldsAndNewItems(accountStates);

        Optional<Rated> rated = accountStates.getRated();
        assertNotNull(rated);
        assertTrue(rated.isPresent());
        checkForNullAndEmptyFieldsAndNewItems(rated.get());
    }

    /**
     * Tests {@link TmdbMovies#getAccountStates(int, String, String)} with an expected result.
     */
    @Test
    public void testGetAlternativeTitles() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/alternative_titles.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/alternative_titles?country=US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        AlternativeTitles alternativeTitles = tmdbMovies.getAlternativeTitles(123, "US");
        assertNotNull(alternativeTitles);
        checkForNullAndEmptyFieldsAndNewItems(alternativeTitles);

        List<AlternativeTitle> titles = alternativeTitles.getTitles();
        assertNotNull(titles);
        assertFalse(titles.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(titles.get(0));
    }

    /**
     * Tests {@link TmdbMovies#getChanges(int, String, String, Integer)} with an expected result.
     */
    @Test
    public void testGetChanges() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/changes.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/changes");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ChangeResults changeResults = tmdbMovies.getChanges(123, null, null, null);
        assertNotNull(changeResults);
        checkForNullAndEmptyFieldsAndNewItems(changeResults);

        List<Change> changedItems = changeResults.getChangedItems();
        assertNotNull(changedItems);
        assertFalse(changedItems.isEmpty());

        Change change = changedItems.get(0);
        assertNotNull(change);
        checkForNullAndEmptyFieldsAndNewItems(change);

        List<ChangeItem> changeItems = change.getChangeItems();
        assertNotNull(changeItems);
        assertFalse(changeItems.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(changeItems.get(0));
    }

    /**
     * Tests {@link TmdbMovies#getCredits(int, String)} with an expected result.
     */
    @Test
    public void testGetCredits() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/credits.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/credits?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        Credits credits = tmdbMovies.getCredits(123, "en-US");
        assertNotNull(credits);
        checkForNullAndEmptyFieldsAndNewItems(credits);

        List<Cast> cast = credits.getCast();
        assertNotNull(cast);
        assertFalse(cast.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(cast.get(0));

        List<Crew> crew = credits.getCrew();
        assertNotNull(crew);
        assertFalse(crew.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(crew.get(0));
    }

    /**
     * Tests {@link TmdbMovies#getExternalIds(int)} with an expected result.
     */
    @Test
    public void testGetExternalIds() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/external_ids.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/external_ids");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ExternalIds externalIds = tmdbMovies.getExternalIds(123);
        assertNotNull(externalIds);
        checkForNullAndEmptyFieldsAndNewItems(externalIds);
    }

    /**
     * Tests {@link TmdbMovies#getImages(int, String, String...)} with an expected result.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/images.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/images?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        Images images = tmdbMovies.getImages(123, "en-US");
        assertNotNull(images);
        checkForNullAndEmptyFieldsAndNewItems(images);

        List<Artwork> posters = images.getPosters();
        assertFalse(posters.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(posters.get(0));

        List<Artwork> backdrops = images.getBackdrops();
        assertFalse(backdrops.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(backdrops.get(0));

        List<Artwork> logos = images.getLogos();
        assertFalse(logos.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(logos.get(0));
    }

    /**
     * Tests {@link TmdbMovies#getKeywords(int)} with an expected result.
     */
    @Test
    public void testGetKeywords() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/keywords.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/keywords");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        KeywordResults keywords = tmdbMovies.getKeywords(123);
        assertNotNull(keywords);
        checkForNullAndEmptyFieldsAndNewItems(keywords);

        List<Keyword> keywordsList = keywords.getKeywords();
        assertFalse(keywordsList.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(keywordsList.get(0));
    }

    /**
     * Tests {@link TmdbMovies#getLatest()} with an expected result.
     */
    @Test
    public void testGetLatest() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/latest.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/latest");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieDb movie = tmdbMovies.getLatest();
        assertNotNull(movie);
        testForNullFields(movie, "accountStates", "alternativeTitles", "credits", "changes", "externalIds", "images", "keywords",
            "recommendations", "releaseDates", "lists", "reviews", "similar", "translations", "videos", "watchProviders");
        testForNewItems(movie);
    }

    /**
     * Tests {@link TmdbMovies#getLists(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetLists() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/lists.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/lists?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieListResultsPage lists = tmdbMovies.getLists(123, "en-US", null);
        assertNotNull(lists);
        checkForNullAndEmptyFieldsAndNewItems(lists);

        MovieList list = lists.getResults().get(0);
        assertNotNull(list);
        checkForNullAndEmptyFieldsAndNewItems(list);
    }

    /**
     * Tests {@link TmdbMovies#getRecommendations(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetRecommendations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/recommendations.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/recommendations?language=en-US&page=1");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieResultsPage recommendations = tmdbMovies.getRecommendations(123, "en-US", 1);
        assertNotNull(recommendations);
        checkForNullAndEmptyFieldsAndNewItems(recommendations);

        Movie movie = recommendations.getResults().get(0);
        assertNotNull(movie);
        checkForNullAndEmptyFieldsAndNewItems(movie);
    }

    /**
     * Tests {@link TmdbMovies#getReleaseDates(int)} with an expected result.
     */
    @Test
    public void testGetReleaseDates() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/release_dates.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/release_dates");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ReleaseDateResults releaseDates = tmdbMovies.getReleaseDates(123);
        assertNotNull(releaseDates);
        checkForNullAndEmptyFieldsAndNewItems(releaseDates);

        ReleaseInfo releaseInfo = releaseDates.getResults().get(0);
        assertNotNull(releaseInfo);
        checkForNullAndEmptyFieldsAndNewItems(releaseInfo);

        List<ReleaseDate> releaseDatesList = releaseInfo.getReleaseDates();
        assertNotNull(releaseDatesList);
        assertFalse(releaseDatesList.isEmpty());

        ReleaseDate releaseDate = releaseDatesList.get(0);
        assertNotNull(releaseDate);
        checkForNullAndEmptyFieldsAndNewItems(releaseDate);

        List<Object> descriptors = releaseDate.getDescriptors();
        assertNotNull(descriptors);
        assertFalse(descriptors.isEmpty());
        assertNotNull(descriptors.get(0));
    }

    /**
     * Tests {@link TmdbMovies#getReviews(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetReviews() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/reviews.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/reviews?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ReviewResultsPage reviews = tmdbMovies.getReviews(123, "en-US", null);
        assertNotNull(reviews);
        checkForNullAndEmptyFieldsAndNewItems(reviews);

        Review review = reviews.getResults().get(0);
        assertNotNull(review);
        checkForNullAndEmptyFieldsAndNewItems(review);

        AuthorDetails authorDetails = review.getAuthorDetails();
        assertNotNull(authorDetails);
        checkForNullAndEmptyFieldsAndNewItems(authorDetails);
    }

    /**
     * Tests {@link TmdbMovies#getSimilar(int, String, Integer)} with an expected result.
     */
    @Test
    public void testGetSimilar() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/similar.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/similar?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        MovieResultsPage similar = tmdbMovies.getSimilar(123, "en-US", null);
        assertNotNull(similar);
        checkForNullAndEmptyFieldsAndNewItems(similar);

        Movie movie = similar.getResults().get(0);
        assertNotNull(movie);
        checkForNullAndEmptyFieldsAndNewItems(movie);
    }

    /**
     * Tests {@link TmdbMovies#getTranslations(int)} with an expected result.
     */
    @Test
    public void testGetTranslations() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/translations.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/translations");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        Translations translations = tmdbMovies.getTranslations(123);
        assertNotNull(translations);
        checkForNullAndEmptyFieldsAndNewItems(translations);

        List<Translation> translationsList = translations.getTranslations();
        assertNotNull(translationsList);
        assertFalse(translationsList.isEmpty());

        Translation translation = translationsList.get(0);
        assertNotNull(translation);
        checkForNullAndEmptyFieldsAndNewItems(translation);

        Data data = translation.getData();
        assertNotNull(data);
        checkForNullAndEmptyFieldsAndNewItems(data);
    }

    /**
     * Tests {@link TmdbMovies#getVideos(int, String)} with an expected result.
     */
    @Test
    public void testGetVideos() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/videos.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/videos?language=en-US");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        VideoResults videos = tmdbMovies.getVideos(123, "en-US");
        assertNotNull(videos);
        checkForNullAndEmptyFieldsAndNewItems(videos);

        List<Video> videosList = videos.getResults();
        assertNotNull(videosList);
        assertFalse(videosList.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(videosList.get(0));
    }

    /**
     * Tests {@link TmdbMovies#getWatchProviders(int)} with an expected result.
     */
    @Test
    public void testGetWatchProviders() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/movies/watch_providers.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/watch/providers");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ProviderResults watchProviders = tmdbMovies.getWatchProviders(123);
        assertNotNull(watchProviders);
        checkForNullAndEmptyFieldsAndNewItems(watchProviders);

        Map<String, WatchProviders> results = watchProviders.getResults();
        assertNotNull(results);
        assertFalse(results.isEmpty());

        WatchProviders watchProvider = results.get("AE");
        assertNotNull(watchProvider);
        checkForNullAndEmptyFieldsAndNewItems(watchProvider);

        List<Provider> rentProviders = watchProvider.getRentProviders();
        assertNotNull(rentProviders);
        assertFalse(rentProviders.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(rentProviders.get(0));

        List<Provider> buyProviders = watchProvider.getBuyProviders();
        assertNotNull(buyProviders);
        assertFalse(buyProviders.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(buyProviders.get(0));

        List<Provider> flatrateProviders = watchProvider.getFlatrateProviders();
        assertNotNull(flatrateProviders);
        assertFalse(flatrateProviders.isEmpty());
        checkForNullAndEmptyFieldsAndNewItems(flatrateProviders.get(0));
    }

    /**
     * Tests {@link TmdbMovies#addRating(int, String, String, double)} with an expected result.
     */
    @Test
    public void testAddRating() throws IOException, TmdbException {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("value", 2.1);
        String jsonBody = Utils.convertToJson(getObjectMapper(), requestBody);

        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/rating");
        String body = TestUtils.readTestFile("api_responses/movies/add_rating.json");
        when(getTmdbUrlReader().readUrl(url, jsonBody, RequestType.POST)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ResponseStatus responseStatus = tmdbMovies.addRating(123, null, null, 2.1);
        assertNotNull(responseStatus);
        checkForNullAndEmptyFieldsAndNewItems(responseStatus);
        assertEquals(1, responseStatus.getStatusCode());
    }

    /**
     * Tests {@link TmdbMovies#deleteRating(int, String, String)} with an expected result.
     */
    @Test
    public void testDeleteRating() throws IOException, TmdbException {
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_MOVIE + "/123/rating");
        String body = TestUtils.readTestFile("api_responses/movies/delete_rating.json");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.DELETE)).thenReturn(body);

        TmdbMovies tmdbMovies = getTmdbApi().getMovies();
        ResponseStatus responseStatus = tmdbMovies.deleteRating(123, null, null);
        assertNotNull(responseStatus);
        checkForNullAndEmptyFieldsAndNewItems(responseStatus);
        assertEquals(13, responseStatus.getStatusCode());
    }
}
