package com.jpmc.theater;

import java.util.Objects;

/**
 * Customer - a user who has made reservations for a showing
 */
public class Customer {

    /** Customer's name */
    private String name;
    /** Customer's id */
    private int id;

    /**
     * Constructor
     * 
     * @param name customer name
     * @param id   customer id
     */
    public Customer(String name, int id) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter - id
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Customer))
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}