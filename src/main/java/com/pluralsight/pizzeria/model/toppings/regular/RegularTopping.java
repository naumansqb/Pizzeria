package com.pluralsight.pizzeria.model.toppings.regular;

import com.pluralsight.pizzeria.model.toppings.Topping;

public class RegularTopping extends Topping {
    public RegularTopping(String name) {
        super(name);
    }
    @Override
    public double calculatePrice(String size) {
        return 0;
    }
}
