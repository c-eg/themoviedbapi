package uk.co.conoregan.themoviedbapi.util;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateUtilTest {

    @ParameterizedTest
    @CsvSource({
        "2023-01-13, 2023-01-14, 1",
        "2023-01-14, 2023-01-13, -1"
    })
    public void testCalculateDaysDifference(String startDate, String endDate, long expectedDays) {
        assertEquals(expectedDays, DateUtil.calculateDaysDifference(startDate, endDate));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", " 2023-01-13 ", "13-01-2023", "2023-02-30"})
    public void testCalculateDaysDifferenceInvalidDate(String invalidDate) {
        assertThrows(DateTimeParseException.class, () -> DateUtil.calculateDaysDifference(invalidDate, "2023-01-14"));
    }
}
