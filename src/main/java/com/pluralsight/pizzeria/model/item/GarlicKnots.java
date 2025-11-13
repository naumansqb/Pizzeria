package com.pluralsight.pizzeria.model.item;

public class GarlicKnots implements Item{
    private int qty;
    private static final double PRICE_PER_ORDER = 1.50;

    public GarlicKnots(int qty) {
        this.qty = qty;
    }

    @Override
    public double calculatePrice() {
        return qty * PRICE_PER_ORDER;
    }

    @Override
    public String getDescription() {
            String orderWord = (qty == 1) ? "order" : "orders";
            return qty + " " + orderWord + " of Garlic Knots";
    }
}
