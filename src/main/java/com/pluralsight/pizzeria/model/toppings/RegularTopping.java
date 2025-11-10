package com.pluralsight.pizzeria.model.toppings;

public class RegularTopping extends Topping{
    public RegularTopping(String name) {
        super(name);
    }
    @Override
    public double calculatePrice(String size) {
        return 0;
    }
}
