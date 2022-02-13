package io.github.metalcupcake5.JustEnoughUpdates.utils;

import java.time.Instant;

public class SkyblockTime {

    public static final int HOUR_S = 50;
    public static final int DAY_S = 24 * HOUR_S;
    public static final int MONTH_LENGTH = 31;
    public static final int YEAR_LENGTH = 12;
    public static final int MONTH_S = MONTH_LENGTH * DAY_S;
    public static final int YEAR_S = YEAR_LENGTH * MONTH_S;
    public static final int YEAR_0 = 1560275700; // in seconds

    public static final String[] MONTHS = new String[]{
        "Early Spring", "Spring", "Late Spring",
        "Early Summer", "Summer", "Late Summer",
        "Early Autumn", "Autumn", "Late Autumn",
        "Early Winter", "Winter", "Late Winter"
    };

    // in 24 hour time
    public static String getCurrentTime(){
        double currentOffset = (Instant.now().getEpochSecond() - YEAR_0) % YEAR_S;
        double currentMonth = Math.floor(currentOffset / MONTH_S);
        double currentMonthOffset = (currentOffset - currentMonth * MONTH_S) % MONTH_S;
        double currentDay = Math.floor(currentMonthOffset / DAY_S);
        double currentDayOffset = (currentMonthOffset - currentDay * DAY_S) % DAY_S;
        int currentHour = (int) Math.floor(currentDayOffset / HOUR_S);
        int currentMinute = (int) Math.floor((currentDayOffset - currentHour * HOUR_S) / HOUR_S * 60);
        return currentHour + ":" + currentMinute/10*10;
    }

    public static int getCurrentDay() {
        double currentOffset = (Instant.now().getEpochSecond() - YEAR_0) % YEAR_S;
        double currentMonth = Math.floor(currentOffset / MONTH_S);
        double currentMonthOffset = (currentOffset - currentMonth * MONTH_S) % MONTH_S;
        return (int) Math.floor(currentMonthOffset / DAY_S) + 1;
    }

    public static String getCurrentMonth() {
        double currentOffset = (Instant.now().getEpochSecond() - YEAR_0) % YEAR_S;
        return MONTHS[(int) Math.floor(currentOffset / MONTH_S)];
    }

}
