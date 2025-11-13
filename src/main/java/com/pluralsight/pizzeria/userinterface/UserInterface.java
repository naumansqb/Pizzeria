package com.pluralsight.pizzeria.userinterface;

import com.pluralsight.pizzeria.model.Order;
import com.pluralsight.pizzeria.model.item.Drink;
import com.pluralsight.pizzeria.model.item.GarlicKnots;
import com.pluralsight.pizzeria.model.item.Item;
import com.pluralsight.pizzeria.model.item.Pizza;
import com.pluralsight.pizzeria.model.toppings.Topping;
import com.pluralsight.pizzeria.model.toppings.premium.CheeseTopping;
import com.pluralsight.pizzeria.model.toppings.premium.MeatTopping;
import com.pluralsight.pizzeria.model.toppings.regular.RegularTopping;
import com.pluralsight.pizzeria.model.toppings.regular.SauceTopping;
import com.pluralsight.pizzeria.model.toppings.regular.SideTopping;
import com.pluralsight.pizzeria.utilities.Utilities;

import java.time.LocalDateTime;
import java.util.*;
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
            displayCurrentOrder();
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose an option from the menu: ");
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
     * Displays the current order items with newest items first
     * Shows individual item prices and total
     */
    private void displayCurrentOrder() {
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            return;
        }
        System.out.println("\nCurrent Order:");
        System.out.println("_".repeat(80));

        List<Item> items = new ArrayList<>(currentOrder.getItems());
        Collections.reverse(items);
        int itemNumber = 0;
        for (Item item : items) {
            System.out.printf("%d. %s\n", ++itemNumber, item.getDescription());
            System.out.printf("   Price: $%.2f\n", item.calculatePrice());
        }

        System.out.println("_".repeat(80));
        System.out.printf("Order Total: $%.2f\n", currentOrder.calculateTotal());
        System.out.println();
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
        Item drink = new Drink(size, flavor, qty);
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

        Item gk = new GarlicKnots(numberOfPieces, qty);
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
        System.out.println("=".repeat(80));
        System.out.println("Add Pizza");
        System.out.println("=".repeat(80));

        String pizzaSize = choosePizzaSize();
        String crustChoice = chooseCrustType();
        boolean hasStuffedCrust = chooseStuffedCrust(pizzaSize);

        Item pizza = new Pizza(pizzaSize, crustChoice, hasStuffedCrust);
        addToppingsToPizza((Pizza) pizza);

        currentOrder.addItem(pizza);

        System.out.println("\nPizza added successfully!");
        System.out.println(pizza.getDescription());
        System.out.printf("Price: $%.2f\n", pizza.calculatePrice());
        System.out.printf("Order Total: $%.2f\n", currentOrder.calculateTotal());
        System.out.println("Press the enter key to return to menu");
        scanner.nextLine();
    }

    /**
     * Prompts customer to select a pizza size
     * Ensures a valid size is chosen
     */
    private String choosePizzaSize() {
        String pizzaSize = "";
        do {
            System.out.println("\nAvailable sizes:");
            Utilities.PIZZA_SIZE_PRICES.entrySet().stream().sorted(Map.Entry.comparingByValue())
                    .forEach(t -> System.out.printf("Size: %s inches, Price $%.2f\n", t.getKey(), t.getValue()));
            System.out.print("Please enter the size you would like: ");
            String choice = scanner.nextLine().trim();
            if (Utilities.PIZZA_SIZE_PRICES.containsKey(choice)) {
                pizzaSize = choice;
                break;
            } else {
                System.out.println("Please enter a valid size from the options above.");
            }
        } while (true);
        return pizzaSize;
    }

    /**
     * Prompts customer to select a crust type
     * Ensures a valid crust type is chosen
     */
    private String chooseCrustType() {
        String crustChoice = "";
        do {
            System.out.println("\nAvailable crust types:");
            IntStream.range(0, Utilities.CRUST_TYPES.size())
                    .forEach(i -> System.out.println((i + 1) + ") " + Utilities.CRUST_TYPES.get(i)));
            System.out.print("Please enter the number that correlates to the crust you'd like: ");
            try {
                int choiceNum = scanner.nextInt();
                scanner.nextLine();
                if (choiceNum >= 1 && choiceNum <= Utilities.CRUST_TYPES.size()) {
                    crustChoice = Utilities.CRUST_TYPES.get(choiceNum - 1);
                    System.out.println(crustChoice + " crust selected");
                    break;
                } else {
                    System.out.println("Please enter a number between 1 and " + Utilities.CRUST_TYPES.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        } while (crustChoice.isBlank());
        return crustChoice;
    }

    /**
     * Prompts customer to choose if they want stuffed crust
     */
    private boolean chooseStuffedCrust(String pizzaSize) {
        boolean hasStuffedCrust = false;
        String stuffedCrustOption = "";
        do {
            System.out.println("\nStuffed crust option:");
            System.out.printf("For a %s\" pizza, stuffed crust costs $%.2f\n",
                    pizzaSize, Utilities.STUFFED_CRUST_PRICES.get(pizzaSize));
            System.out.print("Would you like to make your crust stuffed? (Y/N): ");
            stuffedCrustOption = scanner.nextLine().trim();
            if (stuffedCrustOption.equalsIgnoreCase("Y")) {
                hasStuffedCrust = true;
                break;
            } else if (stuffedCrustOption.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Please enter Y for yes or N for no.");
            }
        } while (true);
        return hasStuffedCrust;
    }

    /**
     * Gives customer option to add toppings
     * Uses switch to transfer to add topping of choice
     */
    private void addToppingsToPizza(Pizza pizza) {
        String choice = "";
        do {
            System.out.print("Would you like to add toppings to your pizza? (Y/N): ");
            choice = scanner.nextLine().trim();
            if (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
                System.out.println("Invalid Response. Please choose Y or N.");
            }
        } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
        if (choice.equalsIgnoreCase("n")) {
            return;
        }
        while (true) {
            System.out.println("=".repeat(80));
            System.out.println("Add Toppings");
            System.out.println("=".repeat(80));
            System.out.println("1. Meat Topping");
            System.out.println("2. Cheese Topping");
            System.out.println("3. Regular Toppings");
            System.out.println("4. Sauce Toppings");
            System.out.println("5. Side Toppings");
            System.out.println("0. Done adding toppings");
            System.out.print("Which topping would you like to add? ");

            try {
                int toppingChoice = scanner.nextInt();
                scanner.nextLine();

                if (toppingChoice == 0) {
                    break;
                }
                switch (toppingChoice) {
                    case 1 -> addMeatTopping(pizza);
                    case 2 -> addCheeseTopping(pizza);
                    case 3 -> addRegularTopping(pizza);
                    case 4 -> addSauceTopping(pizza);
                    case 5 -> addSideTopping(pizza);
                    default -> System.out.println("Invalid Option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Displays Meat Topping Options
     * Prompts user to add topping of choice
     * Can't add same topping to pizza
     */
    private void addMeatTopping(Pizza pizza) {
        System.out.printf("\nFor a %s\" pizza, meat topping costs $%.2f\n",
                pizza.getSize(), Utilities.MEAT_TOPPING_PRICES.get(pizza.getSize()));
        IntStream.range(0, Utilities.MEAT_TOPPINGS.size())
                .forEach(t -> System.out.println((t + 1) + ". " + Utilities.MEAT_TOPPINGS.get(t)));
        System.out.print("Please enter the number that corresponds to the meat topping you'd like: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < 1 || choice > Utilities.MEAT_TOPPINGS.size()) {
                System.out.println("Invalid option. Please try again.");
                return;
            }

            String selectedMeat = Utilities.MEAT_TOPPINGS.get(choice - 1);

            boolean toppingExists = pizza.getToppings().stream()
                    .anyMatch(t -> t.getName().equalsIgnoreCase(selectedMeat));

            if (toppingExists) {
                System.out.printf("\n%s is already on your pizza, please select a different topping\n",
                        selectedMeat.toUpperCase());
            } else {
                System.out.printf("\nFor a %s\" pizza, extra meat topping costs $%.2f\n",
                        pizza.getSize(), Utilities.EXTRA_MEAT_PRICES.get(pizza.getSize()));
                System.out.print("Would you like to add extra meat? (Y/N): ");
                boolean extraMeat = scanner.nextLine().trim().equalsIgnoreCase("Y");
                Topping topping = new MeatTopping(selectedMeat, extraMeat);
                pizza.addTopping(topping);
                System.out.println(selectedMeat.toUpperCase() + (extraMeat ? " (extra)" : "") + " added to pizza!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }
    }

    /**
     * Method to add all the free toppings option
     * Uses passed in parameters to add topping to pizza
     */
    private void addFreeTopping(Pizza pizza, List<String> toppingList, String toppingTypeName, String toppingType) {
        System.out.printf("\n%s are included in the price.\n", toppingTypeName);
        IntStream.range(0, toppingList.size())
                .forEach(t -> System.out.println((t + 1) + ". " + toppingList.get(t)));
        System.out.print(
                "Please enter the number that corresponds to the " + toppingTypeName.toLowerCase() + " you'd like: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < 1 || choice > toppingList.size()) {
                System.out.println("Invalid option. Please try again.");
                return;
            }

            String selectedTopping = toppingList.get(choice - 1);

            boolean toppingExists = pizza.getToppings().stream()
                    .anyMatch(t -> t.getName().equalsIgnoreCase(selectedTopping));

            if (toppingExists) {
                System.out.printf("\n%s is already on your pizza, please select a different topping\n",
                        selectedTopping.toUpperCase());
            } else {
                Topping topping;
                if (toppingType.equalsIgnoreCase("regular")) {
                    topping = new RegularTopping(selectedTopping);
                } else if (toppingType.equalsIgnoreCase("sauce")) {
                    topping = new SauceTopping(selectedTopping);
                } else if (toppingType.equalsIgnoreCase("side")) {
                    topping = new SideTopping(selectedTopping);
                } else {
                    System.out.println("Invalid topping type.");
                    return;
                }
                pizza.addTopping(topping);
                System.out.println(selectedTopping.toUpperCase() + " added to pizza!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }
    }

    private void addCheeseTopping(Pizza pizza) {
        System.out.printf("\nFor a %s\" pizza, cheese topping costs $%.2f\n",
                pizza.getSize(), Utilities.CHEESE_TOPPING_PRICES.get(pizza.getSize()));
        IntStream.range(0, Utilities.CHEESE_TOPPINGS.size())
                .forEach(t -> System.out.println((t + 1) + ". " + Utilities.CHEESE_TOPPINGS.get(t)));
        System.out.print("Please enter the number that corresponds to the cheese topping you'd like: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < 1 || choice > Utilities.CHEESE_TOPPINGS.size()) {
                System.out.println("Invalid option. Please try again.");
                return;
            }

            String selectedCheese = Utilities.CHEESE_TOPPINGS.get(choice - 1);

            boolean toppingExists = pizza.getToppings().stream()
                    .anyMatch(t -> t.getName().equalsIgnoreCase(selectedCheese));

            if (toppingExists) {
                System.out.printf("\n%s is already on your pizza, please select a different topping\n",
                        selectedCheese.toUpperCase());
            } else {
                System.out.printf("\nFor a %s\" pizza, extra cheese topping costs $%.2f\n",
                        pizza.getSize(), Utilities.EXTRA_CHEESE_PRICES.get(pizza.getSize()));
                System.out.print("Would you like to add extra cheese? (Y/N): ");
                boolean extraCheese = scanner.nextLine().trim().equalsIgnoreCase("Y");
                Topping topping = new CheeseTopping(selectedCheese, extraCheese);
                pizza.addTopping(topping);
                System.out.println(selectedCheese.toUpperCase() + (extraCheese ? " (extra)" : "") + " added to pizza!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }
    }

    private void addRegularTopping(Pizza pizza) {
        addFreeTopping(pizza, Utilities.REGULAR_TOPPINGS, "Regular toppings", "regular");
    }

    private void addSauceTopping(Pizza pizza) {
        addFreeTopping(pizza, Utilities.SAUCE_TOPPINGS, "Sauces", "sauce");
    }

    private void addSideTopping(Pizza pizza) {
        addFreeTopping(pizza, Utilities.SIDE_TOPPINGS, "Sides", "side");
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