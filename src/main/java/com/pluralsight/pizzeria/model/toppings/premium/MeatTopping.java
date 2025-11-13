package com.pluralsight.pizzeria.model.toppings.premium;

import com.pluralsight.pizzeria.utilities.Utilities;

public class MeatTopping extends PremiumTopping {

    public MeatTopping(String name, boolean isExtra) {
        super(name, isExtra);
    }

    @Override
    public double calculatePrice(String size) {
        if (isExtra()) {
            return Utilities.MEAT_TOPPING_PRICES.get(size) + Utilities.EXTRA_MEAT_PRICES.get(size);
        }
        return Utilities.MEAT_TOPPING_PRICES.get(size);
    }
}