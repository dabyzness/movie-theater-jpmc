package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
    }

    @Test
    void makeSureIsSingleton() {
        Theater x = new Theater(LocalDateProvider.singleton());
        Theater y = new Theater(LocalDateProvider.singleton());

        assertEquals(x.provider, y.provider);
    }
}
