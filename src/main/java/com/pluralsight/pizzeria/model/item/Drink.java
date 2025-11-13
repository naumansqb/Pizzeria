package com.pluralsight.pizzeria.model.item;

import com.pluralsight.pizzeria.utilities.Utilities;

public class Drink implements Item {
    private String size;
    private String flavor;
    private int qty;

    public Drink(String size, String flavor, int qty) {
        this.size = size;
        this.flavor = flavor;
        this.qty = qty;
    }
    @Override
    public double calculatePrice() {
        return qty * Utilities.DRINK_SIZE_PRICES.get(size.toLowerCase());
    }

    @Override
    public String getDescription() {
        return qty + "x " + (size.toUpperCase()) + " " + (flavor);
    }
}
