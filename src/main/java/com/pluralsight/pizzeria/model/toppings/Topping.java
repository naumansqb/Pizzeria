package com.pluralsight.pizzeria.model.toppings;

public abstract class Topping {
    private String name;

    public Topping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract double calculatePrice(String size);
}
