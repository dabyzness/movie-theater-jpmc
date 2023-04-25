package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;
import java.time.LocalDateTime;

/**
 * Describes a movie
 */
public class Movie {
    /** Valid discount code for movie */
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description; // Not used anywhere -- keep for future features
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    /**
     * Movie Constructor
     * @param title movie title
     * @param runningTime length of movie
     * @param ticketPrice price of ticket
     * @param specialCode special discount code
     */
    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    /**
     * Getter for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for runningTime
     * @return runningTime
     */
    public Duration getRunningTime() {
        return runningTime;
    }

    /**
     * Getter for ticketPrice
     * @return ticketPrice
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Calculate the true ticket price
     * @param showing showing info for the movie
     * @return real ticket price
     */
    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(), showing.getStartTime());
    }

    /**
     * Calculate the discount for a movie
     * @param showSequence sequence of showing in theater
     * @param showStartTime showing start time
     * @return Discount for showing
     */
    private double getDiscount(int showSequence, LocalDateTime showStartTime) {
        // Discount if it has special code
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2; // 20% discount for special movie
        }

        // Discount depending on the sequence
        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {

            sequenceDiscount = 2; // $2 discount for 2nd show
        } else if (showSequence >= 7) {
            sequenceDiscount = 1; // $1 discount for 7th+ show
        }

        // Discount depending on time
        int startHour = showStartTime.getHour();
        double startTimeDiscount = 0;
        if (startHour >= 11 && startHour < 16) {
            startTimeDiscount = ticketPrice * .25; // 25% discount for movies between 11AM-4PM
        }

        // Choose bigger difference between the two discounts on percent
        double percentDiscount = specialDiscount > startTimeDiscount ? specialDiscount : startTimeDiscount;

        // biggest discount gets applied
        return percentDiscount > sequenceDiscount ? percentDiscount : sequenceDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}