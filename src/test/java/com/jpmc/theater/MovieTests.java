package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(2023, 4, 25, 10, 30));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void specialMovieWith25PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(2023, 4, 25, 12, 30));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void specialMovieWith3DollarDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(2023, 4, 25, 10, 30));
        assertEquals(9.50, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void regularMovieWith25PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(2023, 4, 25, 12, 30));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void regularMovieWith3DollarDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(2023, 4, 25, 10, 30));
        assertEquals(9.50, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void regularMovieWith2DollarDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(2023, 4, 25, 10, 30));
        assertEquals(10.50, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void regularMovieWith1DollarDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(2023, 4, 25, 10, 30));
        assertEquals(11.50, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void regularMovieWithNoDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(2023, 4, 25, 10, 30));
        assertEquals(12.50, spiderMan.calculateTicketPrice(showing));
    }
}
