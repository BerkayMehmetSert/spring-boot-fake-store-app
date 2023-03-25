package com.bms.fakestoreapp.service.rules;

import com.bms.fakestoreapp.core.exceptions.BusinessException;
import com.bms.fakestoreapp.core.exceptions.NotFoundException;
import com.bms.fakestoreapp.model.Product;
import com.bms.fakestoreapp.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bms.fakestoreapp.constants.BusinessMessages.*;

@Component
public class ProductBusinessRules {
    private final ProductRepository productRepository;

    public ProductBusinessRules(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void checkIfProductExistsByName(final String name) {
        if (productRepository.existsByNameIgnoreCase(name))
            throw new BusinessException(String.format(PRODUCT_ALREADY_EXISTS, name));
    }

    public void checkIfProductListIsEmpty(final List<Product> products) {
        if (products.isEmpty())
            throw new NotFoundException(PRODUCT_LIST_EMPTY);
    }

    public Product checkIfProductExists(final String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
    }

    public Product checkIfProductExistsByNameReturnProduct(final String name) {
        return productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND, name)));
    }
}
