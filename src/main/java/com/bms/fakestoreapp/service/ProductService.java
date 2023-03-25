package com.bms.fakestoreapp.service;

import com.bms.fakestoreapp.dto.ProductDto;
import com.bms.fakestoreapp.model.Product;
import com.bms.fakestoreapp.repository.ProductRepository;
import com.bms.fakestoreapp.request.CreateProductRequest;
import com.bms.fakestoreapp.request.UpdateProductRequest;
import com.bms.fakestoreapp.response.ProductResponse;
import com.bms.fakestoreapp.service.rules.ProductBusinessRules;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import static com.bms.fakestoreapp.core.utilities.CalculateProductPrice.calculatePrice;

@Service
@CacheConfig(cacheNames = "products")
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductBusinessRules rules;

    public ProductService(ProductRepository productRepository, CategoryService categoryService,
                          ProductBusinessRules rules) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.rules = rules;
    }

    @CachePut(key = "#request.name()")
    public void createProduct(final CreateProductRequest request) {
        rules.checkIfProductExistsByName(request.name());

        var product = new Product(
                request.name(),
                calculatePrice(request.price()),
                request.description(),
                categoryService.getCategory(request.categoryId())
        );

        productRepository.save(product);
    }

    @CacheEvict(allEntries = true)
    public void updateProduct(final String id, final UpdateProductRequest request) {
        var product = getProduct(id);

        if (!product.getName().equals(request.name())) {
            rules.checkIfProductExistsByName(request.name());
        }

        var updatedProduct = new Product(
                product.getId(),
                request.name(),
                calculatePrice(request.price()),
                request.description(),
                categoryService.getCategory(request.categoryId())
        );

        productRepository.save(updatedProduct);
    }

    @CacheEvict(allEntries = true)
    public void updateProductPrice(final String id, final double price) {
        var product = getProduct(id);
        var updatedProduct = new Product(
                product.getId(),
                product.getName(),
                calculatePrice(price),
                product.getDescription(),
                product.getCategory()
        );

        productRepository.save(updatedProduct);
    }

    @CacheEvict(allEntries = true)
    public void updateProductCategory(final String id, final String categoryId) {
        var product = getProduct(id);
        var updatedProduct = new Product(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                categoryService.getCategory(categoryId)
        );

        productRepository.save(updatedProduct);
    }

    @CacheEvict(allEntries = true)
    public void deleteProduct(final String id) {
        productRepository.delete(getProduct(id));
    }

    @Cacheable(key = "#id")
    public ProductDto getProductById(final String id) {
        return ProductDto.convert(getProduct(id));
    }

    @Cacheable(key = "#name")
    public ProductDto getProductByName(final String name) {
        return ProductDto.convert(rules.checkIfProductExistsByNameReturnProduct(name));
    }

    @Cacheable(key = "#categoryId")
    public ProductResponse getProductsByCategoryId(final String categoryId, final int page, final int size) {
        var pageable = PageRequest.of(page, size);
        var products = productRepository.findByCategory_Id(categoryId, pageable);

        rules.checkIfProductListIsEmpty(products.getContent());

        return new ProductResponse(
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.hasNext(),
                products.hasPrevious(),
                products.getContent().stream()
                        .map(ProductDto::convert)
                        .toList()
        );

    }

    @Cacheable(key = "#root.methodName")
    public ProductResponse getAllProducts(final int page, final int size) {
        var pageable = PageRequest.of(page, size);
        var products = productRepository.findAll(pageable);

        rules.checkIfProductListIsEmpty(products.getContent());

        return new ProductResponse(
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.hasNext(),
                products.hasPrevious(),
                products.getContent().stream()
                        .map(ProductDto::convert)
                        .toList()
        );
    }

    private Product getProduct(final String id) {
        return rules.checkIfProductExists(id);
    }
}
