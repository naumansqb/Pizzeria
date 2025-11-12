package com.pluralsight.pizzeria.userinterface;

import com.pluralsight.pizzeria.model.Order;
import com.pluralsight.pizzeria.model.item.Drink;
import com.pluralsight.pizzeria.model.item.GarlicKnots;
import com.pluralsight.pizzeria.model.item.Pizza;
import com.pluralsight.pizzeria.utilities.Utilities;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

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

    /**
     * Displays Home Screen To Customer
     * Allows them to create a new order or exit the application
     **/
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

    /**
     * Upon Clicking New Order Customer Is Shown The Order Screen Below
     * Uses Switch Case To Move Customer To Different Displays
     */
    private void showOrderScreen() {
        boolean running = true;
        while (running) {
            System.out.println("=".repeat(80));
            System.out.println("PIZZA-licious Order Menu");
            System.out.println("=".repeat(80));
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.println("Choose an option from the menu: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addPizzaScreen();
                case "2" -> addDrinkScreen();
                case "3" -> addGarlicKnotsScreen();
                case "4" -> checkoutScreen();
                case "0" -> running = false;
                default -> System.out.println("\nInvalid option. Please enter a valid option from 0 to 4. \n");
            }
        }
    }

    /**
     * Allows customer to add a drink to their order.
     * Invokes methods to get flavor,size, and qty
     * Handles invalid inputs && loops till customer selects a correct option
     */
    private void addDrinkScreen() {
        System.out.println("=".repeat(80));
        System.out.println("Add Drink");
        String flavor = chooseDrinkFlavor();
        String size = chooseDrinkSize();
        int qty = getQty("Please enter your quantity");
        Drink drink = new Drink(size, flavor, qty);
        currentOrder.addItem(drink);
        System.out.println();
        System.out.println("Drink added successfully!");
        System.out.println(drink.getDescription());
        System.out.printf("Price: $%.2f\n", drink.calculatePrice());
        System.out.printf("Order Total: $%.2f\n", currentOrder.calculateTotal());
        System.out.println("\nPress the enter key to return to menu");
        scanner.nextLine();
    }

    /**
     * Prompts customer to select a drink flavor from available options.
     */
    private String chooseDrinkFlavor() {
        String flavor;
        do {
            IntStream.range(0, Utilities.DRINK_FLAVORS.size())
                    .forEach(t -> System.out.println((t + 1) + ") " + Utilities.DRINK_FLAVORS.get(t)));
            System.out.print("Please choose which flavor you would like: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice > Utilities.DRINK_FLAVORS.size() || choice <= 0) {
                    System.out.println(
                            "Invalid option, please enter a valid option from 1 to " + Utilities.DRINK_FLAVORS.size());
                } else {
                    flavor = Utilities.DRINK_FLAVORS.get(choice - 1);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid option, please enter a valid option from 1 to " + Utilities.DRINK_FLAVORS.size());
                scanner.nextLine();
            }
        } while (true);
        return flavor;
    }

    /**
     * Prompts customer to select a drink size and keeps looping till valid option
     */
    private String chooseDrinkSize() {
        String size;
        System.out.println();
        do {
            System.out.println("Displaying size and price:  ");

            Utilities.DRINK_SIZE_PRICES.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(t -> System.out.println(t.getKey() + " - $" + t.getValue()));
            System.out.print("Please choose which size you would like: ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (Utilities.DRINK_SIZE_PRICES.containsKey(choice)) {
                size = choice;
                break;
            } else {
                System.out.println("Invalid option. Please enter 'small', 'medium', or 'large'");
            }
        } while (true);
        return size;
    }

    /**
     * Allows customer to add garlic knots to their order.
     * Prompts for number of pieces per order and quantity of orders.
     */
    private void addGarlicKnotsScreen() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("Add Garlic Knots");
        System.out.println("=".repeat(80) + "\n");

        System.out.println("Available sizes:");
        System.out.println("-".repeat(35));

        Utilities.GARLICKNOTS_SIZE_PRICES.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(t -> System.out.printf("%2d pieces ......... $%.2f\n",
                        t.getKey(), t.getValue()));

        System.out.println("-".repeat(35));

        int numberOfPieces = garlicKnotsNumberOfPieces();
        int qty = getQty("Please enter the number of orders you would like");

        GarlicKnots gk = new GarlicKnots(numberOfPieces, qty);
        currentOrder.addItem(gk);

        System.out.println("\nGarlic knots added successfully!");
        System.out.println(gk.getDescription());
        System.out.printf("Price: $%.2f\n", gk.calculatePrice());
        System.out.printf("Order Total: $%.2f\n", currentOrder.calculateTotal());
        System.out.println("\nPress the enter key to return to menu");
        scanner.nextLine();
    }

    /**
     * Prompts customer to select number of pieces per order
     * Ensures a valid option is chosen
     */
    private int garlicKnotsNumberOfPieces() {
        int pieces;
        do {
            System.out.print("\nEnter number of pieces (3, 6, or 9): ");
            try {
                pieces = scanner.nextInt();
                scanner.nextLine();

                if (Utilities.GARLICKNOTS_SIZE_PRICES.containsKey(pieces)) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please choose 3, 6, or 9 pieces.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        } while (true);
        return pieces;
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

    /**
     * Prompts user for a positive integer quantity with validation
     * Ensures input is greater than 0
     */
    private int getQty(String prompt) {
        int qty;
        do {
            System.out.print(prompt + ": ");
            try {
                qty = scanner.nextInt();
                scanner.nextLine();

                if (qty <= 0) {
                    System.out.println("Must be greater than 0. Please try again.");
                } else {
                    return qty;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        } while (true);
    }

}