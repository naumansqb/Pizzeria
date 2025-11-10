package com.pluralsight.pizzeria.model.item;

import java.util.Map;

public class GarlicKnots implements Item{
    private int qty;
    private int size;

    public GarlicKnots(int size, int qty) {
        this.size = size;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private static final Map<Integer, Double> SIZE_PRICES = Map.of(
            6, 1.50,
            12, 2.75,
            16, 3.50);

    @Override
    public double calculatePrice() {
        return qty * SIZE_PRICES.get(size);
    }

    @Override
    public String getDescription() {
        return qty + ": " + size +" pieces of garlic knots";
    }
}
