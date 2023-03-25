package com.bms.fakestoreapp.constants;

public class OpenApiConstants {
    private OpenApiConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static class ControllerInfo {
        private ControllerInfo() {
            throw new IllegalStateException("Utility class");
        }

        public static final String PRODUCT_DESCRIPTION = "Product API";
        public static final String OPERATION_METHOD_GET = "GET";
        public static final String OPERATION_METHOD_POST = "POST";
        public static final String OPERATION_METHOD_PUT = "PUT";
        public static final String OPERATION_METHOD_DELETE = "DELETE";

        public static final String PRODUCT_NAME = "Product API";
        public static final String PRODUCT_OPERATION_SUMMARY_GET = "Get a product by id";
        public static final String PRODUCT_OPERATION_SUMMARY_GET_BY_NAME = "Get a product by name";
        public static final String PRODUCT_OPERATION_SUMMARY_GET_BY_CATEGORY = "Get a product by category";
        public static final String PRODUCT_OPERATION_SUMMARY_GET_ALL = "Get all products";
        public static final String PRODUCT_OPERATION_SUMMARY_POST = "Create a new product";
        public static final String PRODUCT_OPERATION_SUMMARY_PUT = "Update a product";
        public static final String PRODUCT_OPERATION_SUMMARY_DELETE = "Delete a product";
        public static final String PRODUCT_OPERATION_DESCRIPTION_GET = "Get a product by id";
        public static final String PRODUCT_OPERATION_DESCRIPTION_GET_BY_NAME = "Get a product by name";
        public static final String PRODUCT_OPERATION_DESCRIPTION_GET_BY_CATEGORY = "Get a product by category";
        public static final String PRODUCT_OPERATION_DESCRIPTION_GET_ALL = "Get all products";
        public static final String PRODUCT_OPERATION_DESCRIPTION_POST = "Create a new product";
        public static final String PRODUCT_OPERATION_DESCRIPTION_PUT = "Update a product by id";
        public static final String PRODUCT_OPERATION_DESCRIPTION_PUT_PRICE = "Update a product price by id";
        public static final String PRODUCT_OPERATION_DESCRIPTION_PUT_CATEGORY = "Update a product category by id";
        public static final String PRODUCT_OPERATION_DESCRIPTION_DELETE = "Delete a product";

        public static final String CATEGORY_NAME = "Category API";
        public static final String CATEGORY_DESCRIPTION = "Category API";
        public static final String CATEGORY_OPERATION_SUMMARY_GET = "Get a category by id";
        public static final String CATEGORY_OPERATION_SUMMARY_GET_BY_NAME = "Get a category by name";
        public static final String CATEGORY_OPERATION_SUMMARY_GET_ALL = "Get all categories";
        public static final String CATEGORY_OPERATION_SUMMARY_POST = "Create a new category";
        public static final String CATEGORY_OPERATION_SUMMARY_PUT = "Update a category";
        public static final String CATEGORY_OPERATION_SUMMARY_DELETE = "Delete a category";
        public static final String CATEGORY_OPERATION_DESCRIPTION_GET = "Get a category by id";
        public static final String CATEGORY_OPERATION_DESCRIPTION_GET_BY_NAME = "Get a category by name";
        public static final String CATEGORY_OPERATION_DESCRIPTION_GET_ALL = "Get all categories";
        public static final String CATEGORY_OPERATION_DESCRIPTION_POST = "Create a new category";
        public static final String CATEGORY_OPERATION_DESCRIPTION_PUT = "Update a category by id";
        public static final String CATEGORY_OPERATION_DESCRIPTION_DELETE = "Delete a category";

    }
}
