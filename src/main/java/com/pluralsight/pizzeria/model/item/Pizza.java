package com.pluralsight.pizzeria.model.item;

import com.pluralsight.pizzeria.model.toppings.Topping;
import com.pluralsight.pizzeria.model.toppings.premium.PremiumTopping;
import com.pluralsight.pizzeria.model.toppings.regular.SauceTopping;
import com.pluralsight.pizzeria.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void addTopping(Topping topping){
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    @Override
    public double calculatePrice() {
        double total = Utilities.PIZZA_SIZE_PRICES.get(size) +
                toppings.stream().mapToDouble(t -> t.calculatePrice(size)).sum();
        if(hasStuffedCrust){
            total+=Utilities.STUFFED_CRUST_PRICES.get(size);
        }
        return total;
    }

    @Override
    public String getDescription() {
        String crust = crustType + " crust" + (hasStuffedCrust ? " (stuffed)" : "");
        String toppingsList = toppings.isEmpty() ? "" :
            " with " + toppings.stream()
                .map(t -> {
                    String name = t.getName().toUpperCase();
                    if (t instanceof PremiumTopping premium && premium.isExtra()) {
                        name += " (extra)";
                    }else if(t instanceof SauceTopping){
                        name+= " (sauce)";
                    }
                    return name;
                }).collect(Collectors.joining(", "));
        
        return size + "\" Pizza - " + crust + toppingsList;
    }


}
