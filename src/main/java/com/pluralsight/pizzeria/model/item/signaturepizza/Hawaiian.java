package com.pluralsight.pizzeria.model.item.signaturepizza;

import com.pluralsight.pizzeria.model.toppings.premium.CheeseTopping;
import com.pluralsight.pizzeria.model.toppings.premium.MeatTopping;
import com.pluralsight.pizzeria.model.toppings.regular.RegularTopping;
import com.pluralsight.pizzeria.model.toppings.regular.SauceTopping;

public class Hawaiian extends SignaturePizza {
    public Hawaiian() {
        super("12", "regular", false);
        addTopping(new MeatTopping("ham", false));
        addTopping(new RegularTopping("pineapple"));
        addTopping(new CheeseTopping("mozzarella", false));
        addTopping(new SauceTopping("marinara"));
    }
}



