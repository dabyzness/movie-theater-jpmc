package com.jpmc.theater;

import java.time.LocalDateTime;

/**
 * Showing information for a movie
 */
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    /**
     * Showing Constructor
     * @param movie movie being shown
     * @param sequenceOfTheDay the sequence number it is for the day
     * @param showStartTime time showing starts
     */
    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    /**
     * Getter - movie
     * @return movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Getter - showStartTime
     * @return showStartTime
     */
    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    /**
     * Checks if the sequence passed in is correct
     * @param sequence sequence to test
     * @return true if sequences are equal
     */
    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    /**
     * Getter - gets ticket price
     * @return original ticket price before discounts
     */
    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    /**
     * Getter - sequenceOfTheDay
     * @return sequenceOfTheDay
     */
    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    // Changed to public
    /**
     * Calculates the total cost
     * @param audienceCount
     * @return ticket cost * number of tickets
     */
    public double calculateFee(int audienceCount) {
        return movie.calculateTicketPrice(this) * audienceCount;
    }
}
