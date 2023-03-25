package com.bms.fakestoreapp.service.rules;

import com.bms.fakestoreapp.core.exceptions.BusinessException;
import com.bms.fakestoreapp.core.exceptions.NotFoundException;
import com.bms.fakestoreapp.model.Category;
import com.bms.fakestoreapp.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bms.fakestoreapp.constants.BusinessMessages.*;

@Component
public class CategoryBusinessRules {
    private final CategoryRepository categoryRepository;

    public CategoryBusinessRules(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void checkIfCategoryExistsByName(final String name) {
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(CATEGORY_ALREADY_EXISTS.formatted(name));
        }
    }

    public void checkIfCategoryListIsEmpty(final List<Category> categories) {
        if (categories.isEmpty()) {
            throw new NotFoundException(CATEGORY_LIST_EMPTY);
        }
    }

    public Category checkIfCategoryExists(final String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.formatted(id)));
    }

    public Category checkIfCategoryExistsByNameReturnCategory(final String name) {
        return categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.formatted(name)));
    }
}
