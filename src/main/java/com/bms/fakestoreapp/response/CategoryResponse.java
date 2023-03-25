package com.bms.fakestoreapp.response;

import com.bms.fakestoreapp.core.response.BasePageableResponse;
import com.bms.fakestoreapp.dto.CategoryDto;

import java.util.List;

public class CategoryResponse extends BasePageableResponse {
    private List<CategoryDto> items;

    public CategoryResponse(Integer page, Integer size, Long totalItems, Integer totalPages,
                            Boolean hasNext, Boolean hasPrevious, List<CategoryDto> items) {
        super(page, size, totalItems, totalPages, hasNext, hasPrevious);
        this.items = items;
    }

    public List<CategoryDto> getItems() {
        return items;
    }
}
