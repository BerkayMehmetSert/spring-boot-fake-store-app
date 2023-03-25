package com.bms.fakestoreapp.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequest(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        @Min(0)
        Double price,
        @NotNull
        @NotBlank
        String description,
        @NotNull
        @NotBlank
        String categoryId
) {
}
