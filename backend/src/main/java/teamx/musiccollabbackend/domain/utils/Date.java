package teamx.musiccollabbackend.domain.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Date {

    public static LocalDateTime startOfWeek() {
        return LocalDateTime.now().with(DayOfWeek.MONDAY).truncatedTo(ChronoUnit.DAYS);
    }

    public static LocalDateTime endOfWeek() {
        return startOfWeek().plusDays(6).plusHours(23).plusMinutes(59).plusSeconds(59);
    }

    public static LocalDateTime startOfMonth() {
        return LocalDateTime.now().withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS);
    }

    public static LocalDateTime endOfMonth() {
        return startOfMonth().plusMonths(1).minusSeconds(1);
    }

}
