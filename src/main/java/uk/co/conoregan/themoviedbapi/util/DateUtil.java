package uk.co.conoregan.themoviedbapi.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

/**
 * Date Utility.
 */
public final class DateUtil {
    private DateUtil() {
    }

    /**
     * Calculate the difference in days between two date strings.
     *
     * @param startDate the start date, in format: YYYY-MM-DD.
     * @param endDate   the end date, in format: YYYY-MM-DD.
     * @return the difference in days, negative if the end date is before the start date.
     * @throws java.time.format.DateTimeParseException if either date is not in the format YYYY-MM-DD, or is not a real calendar date
     *                                                 (e.g. 2023-02-30).
     */
    public static long calculateDaysDifference(String startDate, String endDate) {
        var startLocalDate = LocalDate.parse(startDate, ISO_LOCAL_DATE);
        var endLocalDate = LocalDate.parse(endDate, ISO_LOCAL_DATE);
        return ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
    }
}
