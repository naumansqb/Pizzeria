package com.pluralsight.pizzeria.userinterface;

import com.pluralsight.pizzeria.model.Order;
import com.pluralsight.pizzeria.model.item.Pizza;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.currentOrder = null;
    }

    public void start() {
        System.out.println("Welcome to PIZZA-licious!");
        showHomeScreen();
    }

    private void showHomeScreen() {
        do {
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                currentOrder = new Order(LocalDateTime.now());
                showOrderScreen();
            } else if (choice.equals("0")) {
                System.out.println("Have an amazing day!");
                break;
            } else {
                System.out.println("\nInvalid option. Please enter 0 or 1. \n");
            }
        } while (true);
    }

    private void showOrderScreen() {
        System.out.println("=".repeat(80));
        System.out.println("PIZZA-licious Order Menu");
        System.out.println("=".repeat(80));
        boolean running =true;
        do {
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.println("Choose an option from the menu: ");
            String choice = scanner.nextLine().trim();
            switch (choice){
                case "1" -> addPizzaScreen();
                case "2" -> addDrinkScreen();
                case "3" -> addGarlicKnotsScreen();
                case "4" -> checkoutScreen();
                case "0" -> running = false;
                default ->  System.out.println("\nInvalid option. Please enter a valid option from 0 to 4. \n");

            }
        } while (running);
    }

    private void addDrinkScreen() {
        System.out.println("Added drink");
    }

    private void addGarlicKnotsScreen() {
        System.out.println("Added Garlic Knot");
    }


    private void addPizzaScreen() {
        System.out.println("Added Pizza");
    }

    private void addToppingsToPizza(Pizza pizza) {
    }

    private void addMeatTopping(Pizza pizza) {

    }

    private void addCheeseTopping(Pizza pizza) {
    }

    private void addRegularTopping(Pizza pizza) {
    }

    private void addSauce(Pizza pizza) {
    }

    private void addSideTopping(Pizza pizza) {
    }

    private void checkoutScreen() {
        System.out.println("Checked Out");
    }
}