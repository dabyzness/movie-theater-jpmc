package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public double totalFee() {
        // Prevents negative audienceCount
        if(audienceCount < 1){
            return 0;
        }
        return showing.calculateFee(audienceCount);
    }

    public void print() {
        System.out.println(customer.toString() + " --- " + customer.getId()+ "\nTicket Count: " + audienceCount + " --- Total Cost: $" + String.format("%.2f", totalFee()));
    }
}