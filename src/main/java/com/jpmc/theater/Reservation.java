package com.jpmc.theater;

/**
 * Customer's reservation for a showing
 */
public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    /**
     * Reservation Constructor
     * @param customer Customer making reservation
     * @param showing The showing being reserved
     * @param audienceCount How many tickets is customer purchasing
     */
    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    /**
     * Calculates the total price of the reservation
     * @return reservation cost
     */
    public double totalFee() {
        // Prevents negative audienceCount
        if (audienceCount < 1) {
            return 0;
        }
        return showing.calculateFee(audienceCount);
    }

    /**
     * Prints out the reservation
     */
    public void print() {
        System.out.println(customer.toString() + " --- " + customer.getId() + "\nTicket Count: " + audienceCount
                + " --- Total Cost: $" + String.format("%.2f", totalFee()));
    }
}