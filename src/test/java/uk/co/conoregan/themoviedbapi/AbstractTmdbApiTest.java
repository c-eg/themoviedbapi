package uk.co.conoregan.themoviedbapi;

import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequestExecutor;

/**
 * Abstract test class for TMDB API tests.
 *
 * @param <T> the type of TMDB API to test.
 */
@Getter
@ExtendWith(MockitoExtension.class)
public abstract class AbstractTmdbApiTest<T> {
    private TmdbApi tmdbApi;

    @Mock
    private TmdbRequestExecutor requestExecutor;

    private T apiToTest;

    /**
     * Sets up TmdbApi class with a mocked TmdbRequestExecutor.
     */
    @BeforeEach
    public void setUp() {
        tmdbApi = new TmdbApi(requestExecutor);
        apiToTest = createApiToTest();
    }

    /**
     * Creates an instance of the API to test.
     *
     * @return an instance of the API to test
     */
    public abstract T createApiToTest();
}
