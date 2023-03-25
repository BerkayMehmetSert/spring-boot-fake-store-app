package com.bms.fakestoreapp.service;

import com.bms.fakestoreapp.dto.CategoryDto;
import com.bms.fakestoreapp.model.Category;
import com.bms.fakestoreapp.repository.CategoryRepository;
import com.bms.fakestoreapp.response.CategoryResponse;
import com.bms.fakestoreapp.service.rules.CategoryBusinessRules;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "categories")
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryBusinessRules rules;

    public CategoryService(CategoryRepository categoryRepository, CategoryBusinessRules rules) {
        this.categoryRepository = categoryRepository;
        this.rules = rules;
    }

    @CachePut(key = "#name")
    public void createCategory(final String name) {
        rules.checkIfCategoryExistsByName(name);
        categoryRepository.save(new Category(name));
    }

    @CacheEvict(allEntries = true)
    public void updateCategory(final String id, final String name) {
        var category = getCategory(id);

        if (!category.getName().equals(name)) {
            rules.checkIfCategoryExistsByName(name);
        }

        var updatedCategory = new Category(category.getId(), name, category.getProducts());
        categoryRepository.save(updatedCategory);
    }

    @CacheEvict(allEntries = true)
    public void deleteCategory(final String id) {
        categoryRepository.delete(getCategory(id));
    }

    @Cacheable(key = "#id")
    public CategoryDto getCategoryById(final String id) {
        var category = getCategory(id);
        return CategoryDto.convert(category);
    }

    @Cacheable(key = "#name")
    public CategoryDto getCategoryByName(final String name) {
        var category = rules.checkIfCategoryExistsByNameReturnCategory(name);
        return CategoryDto.convert(category);
    }

    @Cacheable(key = "#root.methodName")
    public CategoryResponse getAllCategories(final int page, final int size) {
        var pageable = PageRequest.of(page, size);
        var categories = categoryRepository.findAll(pageable);

        rules.checkIfCategoryListIsEmpty(categories.getContent());

        return new CategoryResponse(
                categories.getNumber(),
                categories.getSize(),
                categories.getTotalElements(),
                categories.getTotalPages(),
                categories.hasNext(),
                categories.hasPrevious(),
                categories.getContent().stream()
                        .map(CategoryDto::convert)
                        .toList()
        );
    }

    protected Category getCategory(final String id) {
        return rules.checkIfCategoryExists(id);
    }
}
