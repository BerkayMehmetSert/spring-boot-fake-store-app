package com.bms.fakestoreapp.constants;

public class BusinessMessages {
    private BusinessMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_ALREADY_EXISTS = "Category with name %s already exists";
    public static final String CATEGORY_NOT_FOUND = "Category with %s not found";
    public static final String CATEGORY_LIST_EMPTY = "Category list is empty";
    public static final String PRODUCT_ALREADY_EXISTS = "Product with name %s already exists";
    public static final String PRODUCT_NOT_FOUND = "Product with %s not found";
    public static final String PRODUCT_LIST_EMPTY = "Product list is empty";
}
