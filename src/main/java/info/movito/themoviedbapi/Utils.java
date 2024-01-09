package info.movito.themoviedbapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.TmdbConfiguration;
import info.movito.themoviedbapi.tools.MovieDbException;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Utility class.
 */
public final class Utils {
    private Utils() {
    }

    /**
     * Compare the MovieDB object with a title and year.
     *
     * @param moviedb The moviedb object to compare too
     * @param title   The title of the movie to compare
     * @param year    The year of the movie to compare exact match
     * @return True if there is a match, False otherwise.
     */
    public static boolean compareMovies(MovieDb moviedb, String title, String year) {
        return compareMovies(moviedb, title, year, 0);
    }

    /**
     * Compare the MovieDB object with a title and year.
     *
     * @param moviedb     The moviedb object to compare too
     * @param title       The title of the movie to compare
     * @param year        The year of the movie to compare
     * @param maxDistance The Levenshtein Distance between the two titles. 0 = exact match
     * @return True if there is a match, False otherwise.
     */
    public static boolean compareMovies(MovieDb moviedb, String title, String year, int maxDistance) {
        if ((moviedb == null) || (StringUtils.isBlank(title))) {
            return Boolean.FALSE;
        }

        if (isValidYear(year) && isValidYear(moviedb.getReleaseDate())) {
            // Compare with year
            String movieYear = moviedb.getReleaseDate().substring(0, 4);
            if (movieYear.equals(year)) {
                if (compareDistance(moviedb.getOriginalTitle(), title, maxDistance)) {
                    return Boolean.TRUE;
                }

                if (compareDistance(moviedb.getTitle(), title, maxDistance)) {
                    return Boolean.TRUE;
                }
            }
        }

        // Compare without year
        if (compareDistance(moviedb.getOriginalTitle(), title, maxDistance)) {
            return Boolean.TRUE;
        }

        if (compareDistance(moviedb.getTitle(), title, maxDistance)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * Compare the Levenshtein Distance between the two strings.
     */
    private static boolean compareDistance(String title1, String title2, int distance) {
        return (StringUtils.getLevenshteinDistance(title1, title2) <= distance);
    }

    /**
     * Check the year is not blank or UNKNOWN.
     */
    private static boolean isValidYear(String year) {
        return (StringUtils.isNotBlank(year) && !year.equals("UNKNOWN"));
    }

    static void sleep(final int timeMs) {
        try {
            Thread.sleep(timeMs);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate the full image URL from the size and image path.
     */
    public static URL createImageUrl(TmdbApi tmdb, String imagePath, String requiredSize) {
        if (StringUtils.isBlank(imagePath)) {
            return null;
        }

        TmdbConfiguration configuration = tmdb.getConfiguration();
        if (!configuration.isValidSize(requiredSize)) {
            throw new MovieDbException("Invalid size: " + requiredSize);
        }

        StringBuilder sb = new StringBuilder(configuration.getBaseUrl());
        sb.append(requiredSize);
        sb.append(imagePath);
        try {
            return (new URL(sb.toString()));
        }
        catch (MalformedURLException ex) {
            throw new MovieDbException(sb.toString(), ex);
        }
    }

    /**
     * Use Jackson to convert Map to json string.
     */
    public static String convertToJson(ObjectMapper objectMapper, Map<String, ?> map) {
        try {
            return objectMapper.writeValueAsString(map);
        }
        catch (JsonProcessingException jpe) {
            throw new RuntimeException("json conversion failed", jpe);
        }
    }

    /**
     * Convert an array of objects to an array of strings.
     */
    public static String[] asStringArray(Object[] appendToResponse) {
        if (appendToResponse == null || appendToResponse.length == 0) {
            return null;
        }

        // guava would be more convenient here (just use transformer)
        String[] asArray = new String[appendToResponse.length];
        for (int i = 0; i < appendToResponse.length; i++) {
            asArray[i] = appendToResponse[i].toString();
        }

        return asArray;
    }
}
