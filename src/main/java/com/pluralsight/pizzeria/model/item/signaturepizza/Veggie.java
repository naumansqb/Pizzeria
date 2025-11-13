package com.pluralsight.pizzeria.model.item.signaturepizza;

import com.pluralsight.pizzeria.model.toppings.premium.CheeseTopping;
import com.pluralsight.pizzeria.model.toppings.regular.RegularTopping;
import com.pluralsight.pizzeria.model.toppings.regular.SauceTopping;

public class Veggie extends SignaturePizza {
    public Veggie() {
        super("8", "regular", false);
        addTopping(new RegularTopping("bell peppers"));
        addTopping(new RegularTopping("spinach"));
        addTopping(new RegularTopping("olives"));
        addTopping(new RegularTopping("onions"));
        addTopping(new SauceTopping("marinara"));
        addTopping(new CheeseTopping("mozzarella", false));
    }
}
