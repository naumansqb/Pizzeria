package com.pluralsight.pizzeria.userinterface;

import com.pluralsight.pizzeria.model.Order;


public class UserInterface {
    private Order currentOrder;

    public UserInterface() {
        this.currentOrder = null;
    }

    public void start() {
        System.out.println("Welcome to PIZZA-licious!");
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

}