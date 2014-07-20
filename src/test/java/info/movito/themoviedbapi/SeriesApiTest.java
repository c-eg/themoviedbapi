package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.model.tv.TvSeries;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class SeriesApiTest extends AbstractTmdbApiTest {

    public static final int BREAKING_BAD_SERIES_ID = 1396;


    @Test
    public void getSeries() {
        TvSeries result = tmdb.getTvSeries().getSeries(BREAKING_BAD_SERIES_ID, LANGUAGE_ENGLISH, TmdbTV.TvMethod.credits, TmdbTV.TvMethod.external_ids);

        assertNotNull("No results found", result);
        Assert.assertTrue("No results found", result.getNetworks().size() == 1);
    }


    @Test
    public void getSeason() {
        TvSeason result = tmdb.getTvSeasons().getSeason(BREAKING_BAD_SERIES_ID, 5, LANGUAGE_ENGLISH);

        assertNotNull("No results found", result);
        Assert.assertTrue("episode number does not match", result.getEpisodes().get(0).getEpisodeNumber() == 1);
    }


    @Test
    public void getSeasonWithAppendedMethods() {
        TvSeason result = tmdb.getTvSeasons().getSeason(BREAKING_BAD_SERIES_ID, 5, LANGUAGE_ENGLISH, TmdbTvSeasons.SeasonMethod.values());

        assertNotNull("No results found", result);
        Assert.assertTrue("episode number does not match", result.getEpisodes().get(0).getEpisodeNumber() == 1);

        // todo add more asserts here that test the methods
    }


    @Test
    public void getEpisode() {
        TvEpisode episode = tmdb.getTvEpisodes().getEpisode(BREAKING_BAD_SERIES_ID, 5, 3, LANGUAGE_ENGLISH);

        assertNotNull("No results found", episode);
        Assert.assertTrue("episode number does not match", episode.getEpisodeNumber() == 3);
        Assert.assertEquals("episode titles does not match", "Hazard Pay", episode.getName());
    }


    @Test
    public void getEpisodeWithAppendedMethods() {
        TvEpisode episode = tmdb.getTvEpisodes().getEpisode(BREAKING_BAD_SERIES_ID, 5, 3, LANGUAGE_ENGLISH, TmdbTvEpisodes.EpisodeMethod.values());

        assertNotNull("No results found", episode);
        Assert.assertTrue("episode number does not match", episode.getEpisodeNumber() == 3);
        Assert.assertEquals("episode titles does not match", "Hazard Pay", episode.getName());
        Assert.assertEquals("episode titles does not match", 7, episode.getCredits().getCast().size());
        Assert.assertEquals("episode titles does not match", "4339518", episode.getExternalIds().getTvdbId());
    }


    @Test
    public void testHomelandEpisodeStills() {
        TvEpisode episode = tmdb.getTvEpisodes().getEpisode(1407, 1, 1, LANGUAGE_ENGLISH, TmdbTvEpisodes.EpisodeMethod.values());
        Assert.assertFalse(episode.getImages().getStills().isEmpty());

    }
}
