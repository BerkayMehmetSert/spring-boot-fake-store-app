package com.bms.fakestoreapp.repository;

import com.bms.fakestoreapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Product> findByNameIgnoreCase(String name);

    Page<Product> findByCategory_Id(String categoryId, PageRequest pageable);

}