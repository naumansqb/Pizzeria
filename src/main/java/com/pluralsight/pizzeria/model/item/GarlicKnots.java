package com.pluralsight.pizzeria.model.item;

import com.pluralsight.pizzeria.utilities.Utilities;

public class GarlicKnots implements Item{
    private int qty;
    private int numberOfPieces;

    public GarlicKnots(int numberOfPieces, int qty) {
        this.numberOfPieces = numberOfPieces;
        this.qty = qty;
    }

    @Override
    public double calculatePrice() {
        return qty * Utilities.GARLICKNOTS_SIZE_PRICES.get(numberOfPieces);
    }

    @Override
    public String getDescription() {
            String orderWord = (qty == 1) ? "order" : "orders";
            return qty + " " + orderWord + " of " + numberOfPieces + "-piece Garlic Knots";
    }
}
