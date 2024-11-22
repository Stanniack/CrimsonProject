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
     *
     * @param minutes average time per mining route cicle
     * @return false if it is possible continue mining or true if it is server
     * save time
     */
    public boolean isServerSave(int minutes) {
        int ssHour = 10, fullHour = 60;
        ZoneId zoneId = ZoneId.of("Atlantic/Reykjavik");
        ZonedDateTime nowInReykjavik = ZonedDateTime.now(zoneId);
        LocalTime localTime = nowInReykjavik.toLocalTime();

        return (localTime.getHour() == ssHour && (fullHour - localTime.getMinute()) < minutes);
    }

    public String getDate() {
        calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }
}
