package com.pluralsight.pizzeria.model.toppings.premium;

import com.pluralsight.pizzeria.model.toppings.Topping;

public abstract class PremiumTopping extends Topping {
    private boolean isExtra;

    public PremiumTopping(String name, boolean isExtra) {
        super(name);
        this.isExtra = isExtra;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }
}