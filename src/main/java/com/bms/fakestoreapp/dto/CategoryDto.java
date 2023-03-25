package com.bms.fakestoreapp.dto;

import com.bms.fakestoreapp.model.Category;

public record CategoryDto(
        String id,
        String name
) {
    public static CategoryDto convert(Category from) {
        return new CategoryDto(from.getId(), from.getName());
    }
}
