package info.movito.themoviedbapi;

import info.movito.themoviedbapi.tools.TmdbUrlReader;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

/**
 * Abstract test class for TMDB API tests.
 */
@Getter
public abstract class AbstractTmdbApiTest {
    private TmdbApi tmdbApi;

    private TmdbUrlReader tmdbUrlReader;

    /**
     * Sets up TmdbApi class with a mocked TmdbUrlReader.
     */
    @BeforeEach
    public void setUp() {
        tmdbUrlReader = mock(TmdbUrlReader.class);
        tmdbApi = new TmdbApi(tmdbUrlReader);
    }
}
