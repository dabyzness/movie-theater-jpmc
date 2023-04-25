package com.jpmc.theater;

import java.time.LocalDate;

/**
 * Local Date Provider
 */
public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    /**
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
        return instance;
    }

    /**
     * 
     * @return Current date in yyyy-mm-dd format
     */
    public LocalDate currentDate() {
        return LocalDate.now();
    }
}
