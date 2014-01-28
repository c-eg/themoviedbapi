package info.movito.themoviedbapi;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public class AbstractTmdbApiTest {

    // API Key
    private static final String API_KEY = "5a1a77e2eba8984804586122754f969f";
    protected static TmdbApi tmdb;

    // Test data
    protected static final int ID_MOVIE_BLADE_RUNNER = 78;
    protected static final int ID_MOVIE_THE_AVENGERS = 24428;
    protected static final int ID_COLLECTION_STAR_WARS = 10;
    protected static final int ID_PERSON_BRUCE_WILLIS = 62;
    protected static final int ID_COMPANY = 2;
    protected static final String COMPANY_NAME = "Marvel Studios";
    protected static final int ID_GENRE_ACTION = 28;
    protected static final String ID_KEYWORD = "1721";
    // Languages
    protected static final String LANGUAGE_DEFAULT = "";
    protected static final String LANGUAGE_ENGLISH = "en";
    protected static final String LANGUAGE_RUSSIAN = "ru";


    @BeforeClass
    public static void setUpClass() throws Exception {
        String apiKey = System.getenv("apikey");
        tmdb = new TmdbApi(apiKey);
    }


    @AfterClass
    public static void tearDownClass() throws Exception {
    }


    @Before
    public void setUp() {
    }


    @After
    public void tearDown() {
    }
}
