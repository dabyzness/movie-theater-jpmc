package com.jpmc.theater;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void totalFeeWith3Customers() {
        var customer = new Customer("John Doe", 24);
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(2023, 4, 25, 10, 30)
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.5);
    }

    @Test
    void totalFeeWith0Customers() {
        var customer = new Customer("John Doe", 24);
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(2023, 4, 25, 10, 30)
        );
        // Technically this should not be allowed. A reservation should not be able to made with 0 customers
        assertTrue(new Reservation(customer, showing, 0).totalFee() == 0);
    }

    @Test
    void totalFeeWithNegativeCustomers() {
        var customer = new Customer("John Doe", 24);
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(2023, 4, 25, 10, 30)
        );
        // Technically this should not be allowed. A reservation should not be able to made with negative customers
        assertTrue(new Reservation(customer, showing, -5).totalFee() == 0);
    }

    @Test
    void checkPrint() {
        var customer = new Customer("John Doe", 24);
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(2023, 4, 25, 10, 30)
        );

        new Reservation(customer, showing, 3).print();
        assertEquals("Name: John Doe --- ID: 24\nTicket Count: 3 --- Total Cost: $28.50", outputStreamCaptor.toString().trim());
    }
}
