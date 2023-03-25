package com.bms.fakestoreapp.response;

import com.bms.fakestoreapp.core.response.BasePageableResponse;
import com.bms.fakestoreapp.dto.ProductDto;

import java.util.List;

public class ProductResponse extends BasePageableResponse {
    private List<ProductDto> items;

    public ProductResponse(Integer page, Integer size, Long totalItems, Integer totalPages,
                           Boolean hasNext, Boolean hasPrevious, List<ProductDto> items) {
        super(page, size, totalItems, totalPages, hasNext, hasPrevious);
        this.items = items;
    }

    public List<ProductDto> getItems() {
        return items;
    }
}
