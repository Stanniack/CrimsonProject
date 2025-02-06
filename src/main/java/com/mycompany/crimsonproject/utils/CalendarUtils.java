package com.mycompany.crimsonproject.utils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

/**
 *
 * @author Devmachine
 */
public class CalendarUtils {

    private Calendar calendar;

    /**
     * Checks if the current time in the "Atlantic/Reykjavik" time zone is
     * within a specified number of minutes before the next full hour at 10:59
     * AM.
     *
     * @param minutes the number of minutes before the full hour to check.
     * @return {@code true} if the current time is within the specified range,
     * otherwise {@code false}.
     */
    public boolean isServerSave(int minutes) {
        int ssHour = 10, fullHour = 60;
        ZoneId zoneId = ZoneId.of("Atlantic/Reykjavik");
        ZonedDateTime nowInReykjavik = ZonedDateTime.now(zoneId);
        LocalTime localTime = nowInReykjavik.toLocalTime();

        return (localTime.getHour() == ssHour && (fullHour - localTime.getMinute()) < minutes);
    }

    /**
     * Gets the current date and time formatted as "yyyy-MM-dd HH:mm:ss".
     *
     * @return the formatted date and time string.
     */
    public String getDate() {
        calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }
}
