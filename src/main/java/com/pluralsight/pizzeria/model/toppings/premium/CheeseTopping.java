package com.pluralsight.pizzeria.model.toppings.premium;

import com.pluralsight.pizzeria.utilities.Utilities;

public class CheeseTopping extends PremiumTopping {

    public CheeseTopping(String name, boolean isExtra) {
        super(name, isExtra);
    }

    @Override
    public double calculatePrice(String size) {
        if (isExtra()) {
            return Utilities.CHEESE_TOPPING_PRICES.get(size) + Utilities.EXTRA_CHEESE_PRICES.get(size);
        }
        return Utilities.CHEESE_TOPPING_PRICES.get(size);
    }
}