package info.movito.themoviedbapi.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class.
 */
public final class Utils {
    private Utils() {
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
