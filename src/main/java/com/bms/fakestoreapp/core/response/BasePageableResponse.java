package com.bms.fakestoreapp.core.response;

public class BasePageableResponse {
    private Integer page;
    private Integer size;
    private Long totalItems;
    private Integer totalPages;
    private Boolean hasNext;
    private Boolean hasPrevious;

    public BasePageableResponse(Integer page, Integer size, Long totalItems, Integer totalPages,
                                Boolean hasNext, Boolean hasPrevious) {
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }
}
