package com.pluralsight.pizzeria.model.item.signaturepizza;

import com.pluralsight.pizzeria.model.toppings.premium.CheeseTopping;
import com.pluralsight.pizzeria.model.toppings.regular.RegularTopping;
import com.pluralsight.pizzeria.model.toppings.regular.SauceTopping;

public class Margherita extends SignaturePizza {
    public Margherita() {
        super("12", "regular", false);
        addTopping(new CheeseTopping("mozzarella", false));
        addTopping(new RegularTopping("tomatoes"));
        addTopping(new RegularTopping("basil"));
        addTopping(new SauceTopping("marinara"));
        addTopping(new SauceTopping("olive oil"));
    }
}
