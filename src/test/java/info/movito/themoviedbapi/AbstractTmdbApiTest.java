package info.movito.themoviedbapi;

import info.movito.themoviedbapi.tools.TmdbUrlReader;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

/**
 * Abstract test class for TMDB API tests.
 *
 * @param <T> the type of TMDB API to test.
 */
@Getter
public abstract class AbstractTmdbApiTest<T extends AbstractTmdbApi> {
    private TmdbApi tmdbApi;

    private TmdbUrlReader tmdbUrlReader;

    private T apiToTest;

    /**
     * Sets up TmdbApi class with a mocked TmdbUrlReader.
     */
    @BeforeEach
    public void setUp() {
        tmdbUrlReader = mock(TmdbUrlReader.class);
        tmdbApi = new TmdbApi(tmdbUrlReader);
        apiToTest = createApiToTest();
    }

    /**
     * Creates an instance of the API to test.
     *
     * @return an instance of the API to test
     */
    public abstract T createApiToTest();
}
