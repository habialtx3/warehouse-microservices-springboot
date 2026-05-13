package com.habialtx3.product_catalogue_service.repository;

import com.habialtx3.product_catalogue_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String > {

    List<Product> findAllOrderByOrderByIdDesc();
}
