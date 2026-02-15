package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.tv.episodegroups.EpisodeGroupType;
import info.movito.themoviedbapi.model.tv.episodegroups.TvEpisodeGroups;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbTvEpisodeGroups.TMDB_METHOD_TV_EPISODE_GROUPS;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbTvEpisodeGroups}.
 */
public class TmdbTvEpisodeGroupsTest extends AbstractTmdbApiTest<TmdbTvEpisodeGroups> {
    @Override
    public TmdbTvEpisodeGroups createApiToTest() {
        return getTmdbApi().getTvEpisodeGroups();
    }

    /**
     * Tests {@link TmdbTvEpisodeGroups#getDetails(String)} with an expected result.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episode_groups/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV_EPISODE_GROUPS + "/5acfef37c3a36842e400333f";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvEpisodeGroups tvEpisodeGroups = getApiToTest().getDetails("5acfef37c3a36842e400333f");
        assertNotNull(tvEpisodeGroups);
        TestUtils.validateAbstractJsonMappingFields(tvEpisodeGroups);
    }

    /**
     * Tests the json value of type is mapped to the correct value of {@link EpisodeGroupType}.
     */
    @Test
    public void testEpisodeGroupType() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/tv_episode_groups/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_TV_EPISODE_GROUPS + "/5acfef37c3a36842e400333f";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TvEpisodeGroups tvEpisodeGroups = getApiToTest().getDetails("5acfef37c3a36842e400333f");
        assertEquals(EpisodeGroupType.DIGITAL, tvEpisodeGroups.getType());
    }
}
