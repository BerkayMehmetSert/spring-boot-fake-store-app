package com.bms.fakestoreapp.core.utilities;

public class CalculateProductPrice {
    private CalculateProductPrice() {
        throw new IllegalStateException("Utility class");
    }

    private static final double TAX = 0.2;
    private static final double SHIPPING = 0.1;

    public static double calculatePrice(double price) {
        return price + (price * TAX) + (price * SHIPPING);
    }
}
