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
            "thin", "regular", "thick", "cauliflower"
    );

}
