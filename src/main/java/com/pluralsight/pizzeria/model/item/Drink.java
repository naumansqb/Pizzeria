package com.pluralsight.pizzeria.model.item;

public class Drink implements Item{

    @Override
    public double calculatePrice() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "";
    }
}
