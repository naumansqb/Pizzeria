package com.pluralsight.pizzeria.utilities;

import java.util.List;
import java.util.Map;

public class Utilities {

        public static final List<String> DRINK_FLAVORS = List.of(
                        "Coke", "Pepsi", "Sprite", "Raspberry", "Water", "Lemonade");
        public static final Map<String, Double> DRINK_SIZE_PRICES = Map.of(
                        "small", 2.00,
                        "medium", 2.50,
                        "large", 3.00);
        public static final Map<Integer, Double> GARLICKNOTS_SIZE_PRICES = Map.of(
                        3, 1.50,
                        6, 2.75,
                        9, 3.50);
        public static final Map<String, Double> PIZZA_SIZE_PRICES = Map.of(
                        "8", 8.50,
                        "12", 12.00,
                        "16", 16.50);
        public static final Map<String, Double> STUFFED_CRUST_PRICES = Map.of(
                        "8", 1.00,
                        "12", 1.50,
                        "16", 2.00);

        public static final List<String> CRUST_TYPES = List.of(
                        "thin", "regular", "thick", "cauliflower");

        public static final List<String> MEAT_TOPPINGS = List.of(
                        "pepperoni", "sausage", "ham", "bacon", "chicken", "meatball");

        public static final List<String> CHEESE_TOPPINGS = List.of(
                        "mozzarella", "parmesan", "ricotta", "goat cheese", "buffalo");

        public static final List<String> REGULAR_TOPPINGS = List.of(
                        "onions", "mushrooms", "bell peppers", "olives", "tomatoes",
                        "spinach", "basil", "pineapple", "anchovies");

        public static final List<String> SAUCE_TOPPINGS = List.of(
                        "marinara", "alfredo", "pesto", "bbq", "buffalo", "olive oil");

        public static final List<String> SIDE_TOPPINGS = List.of(
                        "red pepper", "parmesan");

        public static final Map<String, Double> MEAT_TOPPING_PRICES = Map.of(
                        "8", 1.00,
                        "12", 2.00,
                        "16", 3.00);

        public static final Map<String, Double> EXTRA_MEAT_PRICES = Map.of(
                        "8", 0.50,
                        "12", 1.00,
                        "16", 1.50);

        public static final Map<String, Double> CHEESE_TOPPING_PRICES = Map.of(
                        "8", 0.75,
                        "12", 1.50,
                        "16", 2.25);

        public static final Map<String, Double> EXTRA_CHEESE_PRICES = Map.of(
                        "8", 0.30,
                        "12", 0.60,
                        "16", 0.90);

}
