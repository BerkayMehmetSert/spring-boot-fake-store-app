package com.bms.fakestoreapp.dto;

import com.bms.fakestoreapp.model.Product;

import java.util.Objects;

public record ProductDto(
        String id,
        String name,
        Double price,
        String description,
        String categoryId
) {
    public static ProductDto convert(Product from) {
        return new ProductDto(
                from.getId(),
                from.getName(),
                from.getPrice(),
                from.getDescription(),
                Objects.requireNonNull(from.getCategory()).getId()
        );
    }
}
