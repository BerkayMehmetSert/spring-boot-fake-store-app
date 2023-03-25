package com.bms.fakestoreapp.controller;

import com.bms.fakestoreapp.dto.ProductDto;
import com.bms.fakestoreapp.request.CreateProductRequest;
import com.bms.fakestoreapp.request.UpdateProductRequest;
import com.bms.fakestoreapp.response.ProductResponse;
import com.bms.fakestoreapp.service.ProductService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.bms.fakestoreapp.constants.OpenApiConstants.ControllerInfo.*;

@RestController
@RequestMapping("/api/v1/product")
@Validated
@Tag(name = PRODUCT_NAME, description = PRODUCT_DESCRIPTION)
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(
            method = OPERATION_METHOD_POST,
            summary = PRODUCT_OPERATION_SUMMARY_POST,
            description = PRODUCT_OPERATION_DESCRIPTION_POST,
            tags = {PRODUCT_NAME}
    )
    @PostMapping
    @RateLimiter(name = "basic")
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductRequest request) {
        service.createProduct(request);
        return ResponseEntity.ok().build();
    }

    @Operation(
            method = OPERATION_METHOD_PUT,
            summary = PRODUCT_OPERATION_SUMMARY_PUT,
            description = PRODUCT_OPERATION_DESCRIPTION_PUT,
            tags = {PRODUCT_NAME}
    )
    @PutMapping("/{id}")
    @RateLimiter(name = "basic")
    public ResponseEntity<Void> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest request) {
        service.updateProduct(id, request);
        return ResponseEntity.ok().build();
    }

    @Operation(
            method = OPERATION_METHOD_PUT,
            summary = PRODUCT_OPERATION_SUMMARY_PUT,
            description = PRODUCT_OPERATION_DESCRIPTION_PUT_PRICE,
            tags = {PRODUCT_NAME}
    )
    @PutMapping("/price/{id}")
    @RateLimiter(name = "basic")
    public ResponseEntity<Void> updateProductPrice(@PathVariable String id, @RequestParam double price) {
        service.updateProductPrice(id, price);
        return ResponseEntity.ok().build();
    }

    @Operation(
            method = OPERATION_METHOD_PUT,
            summary = PRODUCT_OPERATION_SUMMARY_PUT,
            description = PRODUCT_OPERATION_DESCRIPTION_PUT_CATEGORY,
            tags = {PRODUCT_NAME}
    )
    @PutMapping("/category/{id}")
    @RateLimiter(name = "basic")
    public ResponseEntity<Void> updateProductCategory(@PathVariable String id, @RequestParam String categoryId) {
        service.updateProductCategory(id, categoryId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            method = OPERATION_METHOD_DELETE,
            summary = PRODUCT_OPERATION_SUMMARY_DELETE,
            description = PRODUCT_OPERATION_DESCRIPTION_DELETE,
            tags = {PRODUCT_NAME}
    )
    @DeleteMapping("/{id}")
    @RateLimiter(name = "basic")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            method = OPERATION_METHOD_GET,
            summary = PRODUCT_OPERATION_SUMMARY_GET,
            description = PRODUCT_OPERATION_DESCRIPTION_GET,
            tags = {PRODUCT_NAME}
    )
    @GetMapping("/{id}")
    @RateLimiter(name = "basic")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @Operation(
            method = OPERATION_METHOD_GET,
            summary = PRODUCT_OPERATION_SUMMARY_GET_BY_NAME,
            description = PRODUCT_OPERATION_DESCRIPTION_GET_BY_NAME,
            tags = {PRODUCT_NAME}
    )
    @GetMapping("/name/{name}")
    @RateLimiter(name = "basic")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getProductByName(name));
    }

    @Operation(
            method = OPERATION_METHOD_GET,
            summary = PRODUCT_OPERATION_SUMMARY_GET_BY_CATEGORY,
            description = PRODUCT_OPERATION_DESCRIPTION_GET_BY_CATEGORY,
            tags = {PRODUCT_NAME}
    )
    @GetMapping("/category/{categoryId}")
    @RateLimiter(name = "basic")
    public ResponseEntity<ProductResponse> getProductsByCategoryId(@PathVariable String categoryId,
                                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getProductsByCategoryId(categoryId, page, size));
    }

    @Operation(
            method = OPERATION_METHOD_GET,
            summary = PRODUCT_OPERATION_SUMMARY_GET_ALL,
            description = PRODUCT_OPERATION_DESCRIPTION_GET_ALL,
            tags = {PRODUCT_NAME}
    )
    @GetMapping
    @RateLimiter(name = "basic")
    public ResponseEntity<ProductResponse> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllProducts(page, size));
    }
}
