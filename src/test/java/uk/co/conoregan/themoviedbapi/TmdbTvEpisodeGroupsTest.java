package uk.co.conoregan.themoviedbapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.tv.episodegroups.EpisodeGroupType;
import uk.co.conoregan.themoviedbapi.model.tv.episodegroups.TvEpisodeGroups;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbTvEpisodeGroups.TMDB_METHOD_TV_EPISODE_GROUPS;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        TvEpisodeGroups tvEpisodeGroups = getApiToTest().getDetails("5acfef37c3a36842e400333f");
        assertEquals(EpisodeGroupType.DIGITAL, tvEpisodeGroups.getType());
    }
}
