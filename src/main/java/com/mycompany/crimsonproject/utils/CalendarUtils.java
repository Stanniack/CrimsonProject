package com.mycompany.crimsonproject.utils;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author Devmachine
 */
public class CalendarUtils {

    /**
     *
     * @param minutes average time per mining route cicle
     * @return false if it is possible continue mining or true if it is server save time
     */
    public boolean isServerSave(int minutes) {
        int ssHour = 7, fullHour = 60;
        ZoneId zoneId = ZoneId.of("Atlantic/Reykjavik");
        ZonedDateTime nowInReykjavik = ZonedDateTime.now(zoneId);
        LocalTime localTime = nowInReykjavik.toLocalTime();

        return (localTime.getHour() == ssHour && (fullHour - localTime.getMinute()) < minutes);
    }
}
