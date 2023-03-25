package com.bms.fakestoreapp.controller;

import com.bms.fakestoreapp.dto.CategoryDto;
import com.bms.fakestoreapp.response.CategoryResponse;
import com.bms.fakestoreapp.service.CategoryService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.bms.fakestoreapp.constants.OpenApiConstants.ControllerInfo.*;

@RestController
@RequestMapping("/api/v1/category")
@Validated
@Tag(name = CATEGORY_NAME, description = CATEGORY_DESCRIPTION)
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @Operation(
            method = OPERATION_METHOD_POST,
            summary = CATEGORY_OPERATION_SUMMARY_POST,
            description = CATEGORY_OPERATION_DESCRIPTION_POST,
            tags = {CATEGORY_NAME}
    )
    @PostMapping
    @RateLimiter(name = "basic")
    public ResponseEntity<Void> createCategory(@NotNull @RequestParam String name) {
        service.createCategory(name);
        return ResponseEntity.ok().build();
    }

    @Operation(
            method = OPERATION_METHOD_PUT,
            summary = CATEGORY_OPERATION_SUMMARY_PUT,
            description = CATEGORY_OPERATION_DESCRIPTION_PUT,
            tags = {CATEGORY_NAME}
    )
    @PutMapping("/{id}")
    @RateLimiter(name = "basic")
    public ResponseEntity<Void> updateCategory(@NotNull @PathVariable String id, @NotNull @RequestParam String name) {
        service.updateCategory(id, name);
        return ResponseEntity.ok().build();
    }

    @Operation(
            method = OPERATION_METHOD_DELETE,
            summary = CATEGORY_OPERATION_SUMMARY_DELETE,
            description = CATEGORY_OPERATION_DESCRIPTION_DELETE,
            tags = {CATEGORY_NAME}
    )
    @DeleteMapping("/{id}")
    @RateLimiter(name = "basic")
    public ResponseEntity<Void> deleteCategory(@NotNull @PathVariable String id) {
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            method = OPERATION_METHOD_GET,
            summary = CATEGORY_OPERATION_SUMMARY_GET,
            description = CATEGORY_OPERATION_DESCRIPTION_GET,
            tags = {CATEGORY_NAME}
    )
    @GetMapping("/{id}")
    @RateLimiter(name = "basic")
    public ResponseEntity<CategoryDto> getCategoryById(@NotNull @PathVariable String id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @Operation(
            method = OPERATION_METHOD_GET,
            summary = CATEGORY_OPERATION_SUMMARY_GET_BY_NAME,
            description = CATEGORY_OPERATION_DESCRIPTION_GET_BY_NAME,
            tags = {CATEGORY_NAME}
    )
    @GetMapping("/name/{name}")
    @RateLimiter(name = "basic")
    public ResponseEntity<CategoryDto> getCategoryByName(@NotNull @PathVariable String name) {
        return ResponseEntity.ok(service.getCategoryByName(name));
    }

    @Operation(
            method = OPERATION_METHOD_GET,
            summary = CATEGORY_OPERATION_SUMMARY_GET_ALL,
            description = CATEGORY_OPERATION_DESCRIPTION_GET_ALL,
            tags = {CATEGORY_NAME}
    )
    @GetMapping
    @RateLimiter(name = "basic")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllCategories(page, size));
    }
}
