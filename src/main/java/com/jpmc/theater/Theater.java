package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Theater
 */
public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;

    /**
     * Theater constructor - populates movies and showings
     * @param provider LocalDateProvider
     */
    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));
    }

    /**
     * Creates a Reservation
     * @param customer Customer making reservation
     * @param sequence Which sequence
     * @param howManyTickets How mant tickets
     * @return new Reservation
     */
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;

        if(howManyTickets < 1){
            throw new IllegalArgumentException("Invalid number of tickets " + howManyTickets);
        }

        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    /**
     * Prints a schedule in simple text format
     */
    public void printSchedule() {
        System.out.println("===================================================");
        System.out.println(
                "Movie Showings for " + DateTimeFormatter.ofPattern("MM/dd/YYYY").format(provider.currentDate()));
        System.out.println("===================================================");
        schedule.forEach(
                s -> System.out.println(s.getSequenceOfTheDay() + ": " + humanReadableStartTime(s.getStartTime()) + " "
                        + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $"
                        + String.format("%.2f", s.getMovieFee()) + " " + (s.getMovieFee() == s.calculateFee(1) ? ""
                                : "DISCOUNTED TO: $" + String.format("%.2f", s.calculateFee(1)))));
        System.out.println("===================================================");
    }

    /**
     * Turns startTime to be easier to read
     * @param time Time in LocalDateTime format
     * @return Readable time string (10:10 AM)
     */
    public String humanReadableStartTime(LocalDateTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();

        String meridien = hour >= 12 ? "PM" : "AM";
        hour = hour > 12 ? hour - 12 : hour;

        return String.format("%s:%s %s", hour, minute < 10 ? Integer.toString(minute) + "0" : minute, meridien);
    }

    /**
     * Transforms a time into a human readable string
     * @param duration time in Duration format
     * @return readable duration string (1 hour 30 minutes)
     */
    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin,
                handlePlural(remainingMin));
    }

    /**
     * Pluralizes string
     * @param value number
     * @return s for plural or empty string
     */
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "s";
        }
    }

    /**
     * Prints the movie schedule in JSON format
     */
    public void printScheduleJSON() {
        int lastShowing = schedule.get(schedule.size() - 1).getSequenceOfTheDay();

        System.out.println("{\n  \"date\": \"" + provider.currentDate() + "\",");
        System.out.println("  \"showings\": [");
        schedule.forEach(s -> {
            System.out.println("    {");
            System.out.println("      \"showing\": " + s.getSequenceOfTheDay() + ",");
            System.out.println("      \"title\": \"" + s.getMovie().getTitle() + "\",");
            System.out.println("      \"duration\": " + s.getMovie().getRunningTime().toMinutes() + ",");
            System.out.println("      \"fee\": " + s.getMovieFee() + ",");
            System.out.println("      \"startTime\": \"" + s.getStartTime() + "\",");
            System.out.println(s.isSequence(lastShowing) ? "    }" : "    },");
        });

        System.out.println("  ]\n}");
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        // Uncomment below to see JSON
        // theater.printScheduleJSON();
    }
}
