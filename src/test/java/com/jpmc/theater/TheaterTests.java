package com.jpmc.theater;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reservation.totalFee(), 37.50);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }

    @Test
    void printMovieScheduleInJSON() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printScheduleJSON();
    }

    @Test
    void makeReservation() {
        Theater theater = new Theater(LocalDateProvider.singleton());

        Reservation reservation = theater.reserve(new Customer("John Doe", "12"), 1, 3);

        assertEquals(24, reservation.totalFee());
    }

    @Test
    void noReservationIfInvalidSequence() {
        Theater theater = new Theater(LocalDateProvider.singleton());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            Reservation reservation = theater.reserve(new Customer("John Doe", "12"), 15, 3);
        });

        assertEquals("not able to find any showing for given sequence 15", exception.getMessage());
    }

    @Test
    void noReservationIfInvalidAudienceCount() {
        Theater theater = new Theater(LocalDateProvider.singleton());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Reservation reservation = theater.reserve(new Customer("John Doe", "12"), 15, -1);
        });

        assertEquals("Invalid number of tickets -1", exception.getMessage());
    }
}
