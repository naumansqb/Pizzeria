package com.pluralsight.pizzeria.utilities;
import java.util.List;
import java.util.Map;

public class Utilities {

    public static final List<String> DRINK_FLAVORS = List.of(
            "Coke", "Pepsi", "Sprite", "Raspberry", "Water", "Lemonade"
    );
    public static final Map<String, Double> DRINK_SIZE_PRICES = Map.of(
            "small", 2.00,
            "medium", 2.50,
            "large", 3.00);
}
