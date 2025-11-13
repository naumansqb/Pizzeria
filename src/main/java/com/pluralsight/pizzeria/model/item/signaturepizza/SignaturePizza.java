package com.pluralsight.pizzeria.model.item.signaturepizza;

import com.pluralsight.pizzeria.model.item.Pizza;

public abstract class SignaturePizza extends Pizza {
    public SignaturePizza(String size, String crustType, boolean hasStuffedCrust) {
        super(size, crustType, hasStuffedCrust);
    }
    
}
