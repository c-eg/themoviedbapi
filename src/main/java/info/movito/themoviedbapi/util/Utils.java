package info.movito.themoviedbapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.movito.themoviedbapi.model.config.TmdbConfiguration;
import info.movito.themoviedbapi.tools.MovieDbException;
import info.movito.themoviedbapi.tools.TmdbException;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * Utility class.
 */
public final class Utils {
    private Utils() {
    }

    /**
     * Generate the full image URL from the size and image path.
     */
    public static URL createImageUrl(TmdbConfiguration configuration, String imagePath, String requiredSize) {
        if (StringUtils.isBlank(imagePath)) {
            return null;
        }

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
    public static String convertToJson(ObjectMapper jsonMapper, Map<String, ?> map) throws TmdbException {
        try {
            return jsonMapper.writeValueAsString(map);
        }
        catch (JsonProcessingException exception) {
            throw new TmdbException(exception.getMessage());
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

    /**
     * Calculate the difference in days between two date strings.
     *
     * @param startDateString the start date string, in format: YYYY-MM-DD.
     * @param endDateString   the end date string, in format: YYYY-MM-DD.
     * @return the difference in days.
     */
    public static long calculateDaysDifference(String startDateString, String endDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
