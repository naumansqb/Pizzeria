package com.pluralsight.pizzeria.model.toppings.premium;

import java.util.Map;

public class MeatTopping extends PremiumTopping {
    private static final Map<String, Double> SIZE_PRICE = Map.of(
            "8", 1.00,
            "12", 2.00,
            "16", 3.00
    );

    private static final Map<String, Double> EXTRA_PRICES = Map.of(
            "8", 0.50,
            "12", 1.00,
            "16", 1.50
    );

    public MeatTopping(String name, boolean isExtra) {
        super(name, isExtra);
    }

    @Override
    public double calculatePrice(String size) {
        if (isExtra()) {
            return SIZE_PRICE.get(size) + EXTRA_PRICES.get(size);
        }
        return SIZE_PRICE.get(size);
    }
}