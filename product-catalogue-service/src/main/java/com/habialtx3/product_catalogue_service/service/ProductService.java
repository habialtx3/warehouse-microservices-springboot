package com.habialtx3.product_catalogue_service.service;

import com.habialtx3.product_catalogue_service.entity.Product;
import com.habialtx3.product_catalogue_service.model.CreateProductRequest;
import com.habialtx3.product_catalogue_service.model.ProductResponse;
import com.habialtx3.product_catalogue_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validation;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public ProductResponse create(CreateProductRequest request) {

        validation.validate(request);

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategoryId(request.getCategoryId());
        product.setPrice(request.getPrice());

        productRepository.save(product);

        return toProductResponse(product);

    }

    private ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .categoryId(product.getCategoryId())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> list() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> toProductResponse(product)).toList();

    }

    @Transactional(readOnly = true)
    public ProductResponse get(String id) {

        Product products = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found")
        );

        return toProductResponse(products);
    }


}
