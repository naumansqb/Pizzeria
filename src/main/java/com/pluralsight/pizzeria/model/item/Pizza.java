package com.pluralsight.pizzeria.model.item;

import com.pluralsight.pizzeria.model.toppings.Topping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pizza implements Item {
    private String size;
    private String crustType;
    private List<Topping> toppings;
    private boolean hasStuffedCrust;

    public Pizza(String size, String crustType, boolean hasStuffedCrust) {
        this.size = size;
        this.crustType = crustType;
        this.toppings = new ArrayList<>();
        this.hasStuffedCrust = hasStuffedCrust;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrustType() {
        return crustType;
    }

    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isHasStuffedCrust() {
        return hasStuffedCrust;
    }

    public void setHasStuffedCrust(boolean hasStuffedCrust) {
        this.hasStuffedCrust = hasStuffedCrust;
    }

    public void addTopping(Topping topping){
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }
    private static final Map<String, Double> SIZE_PRICES = Map.of(
            "8", 8.50,
            "12", 12.00,
            "16", 16.50);
    private static final Map<String, Double> STUFFED_CRUST_PRICES = Map.of(
            "8", 1.00,
            "12", 1.50,
            "16", 2.00);
    @Override
    public double calculatePrice() {
        double total = SIZE_PRICES.get(size) +
                toppings.stream().mapToDouble(t -> t.calculatePrice(size)).sum();
        if(hasStuffedCrust){
            total+=STUFFED_CRUST_PRICES.get(size);
        }
        return total;
    }

    @Override
    public String getDescription() {
       return "Pizza{" +
                "size='" + size + '\'' +
                ", crustType='" + crustType + '\'' +
                ", toppings=" + toppings +
                ", hasStuffedCrust=" + hasStuffedCrust +
                '}';
    }

}
