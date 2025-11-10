package com.pluralsight.pizzeria.model.toppings.premium;

import java.util.Map;

public class CheeseTopping extends PremiumTopping {
    private static final Map<String, Double> SIZE_PRICE = Map.of(
            "8", 0.75,
            "12", 1.50,
            "16", 2.25
    );

    private static final Map<String, Double> EXTRA_PRICES = Map.of(
            "8", 0.30,
            "12", 0.60,
            "16", 0.90
    );

    public CheeseTopping(String name, boolean isExtra) {
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