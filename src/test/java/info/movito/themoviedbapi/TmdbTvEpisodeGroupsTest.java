package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.tv.episodegroups.EpisodeGroupType;
import info.movito.themoviedbapi.model.tv.episodegroups.TvEpisodeGroups;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static info.movito.themoviedbapi.TmdbTvEpisodeGroups.TMDB_METHOD_TV_EPISODE_GROUPS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.validateAbstractJsonMappingFields;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbTvEpisodeGroups}.
 */
public class TmdbTvEpisodeGroupsTest extends AbstractTmdbApiTest {
    private TmdbTvEpisodeGroups tmdbTvEpisodeGroups;

    /**
     * Sets up TmdbTvEpisodeGroups class.
     */
    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        tmdbTvEpisodeGroups = getTmdbApi().getTvEpisodeGroups();
    }

    /**
     * Tests {@link TmdbTvEpisodeGroups#getDetails(String)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episode_groups/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV_EPISODE_GROUPS + "/5acfef37c3a36842e400333f");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvEpisodeGroups tvEpisodeGroups = tmdbTvEpisodeGroups.getDetails("5acfef37c3a36842e400333f");
        assertNotNull(tvEpisodeGroups);
        validateAbstractJsonMappingFields(tvEpisodeGroups);
    }

    /**
     * Tests the json value of type is mapped to the correct value of {@link EpisodeGroupType}.
     */
    @Test
    public void testEpisodeGroupType() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episode_groups/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_TV_EPISODE_GROUPS + "/5acfef37c3a36842e400333f");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvEpisodeGroups tvEpisodeGroups = tmdbTvEpisodeGroups.getDetails("5acfef37c3a36842e400333f");
        assertEquals(EpisodeGroupType.DIGITAL, tvEpisodeGroups.getType());
    }
}
