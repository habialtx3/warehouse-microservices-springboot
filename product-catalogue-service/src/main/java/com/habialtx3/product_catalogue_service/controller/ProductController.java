package com.habialtx3.product_catalogue_service.controller;

import com.habialtx3.product_catalogue_service.entity.Product;
import com.habialtx3.product_catalogue_service.model.CreateProductRequest;
import com.habialtx3.product_catalogue_service.model.ProductResponse;
import com.habialtx3.product_catalogue_service.model.WebResponse;
import com.habialtx3.product_catalogue_service.repository.ProductRepository;
import com.habialtx3.product_catalogue_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> create(@RequestBody CreateProductRequest request) {
        ProductResponse response = productService.create(request);
        return WebResponse.<ProductResponse>builder()
                .data(response)
                .build();
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    WebResponse<List<ProductResponse>> list() {
        List<ProductResponse> responses = productService.list();
        return  WebResponse.<List<ProductResponse>>builder()
                .data(responses)
                .build();
    }
}
