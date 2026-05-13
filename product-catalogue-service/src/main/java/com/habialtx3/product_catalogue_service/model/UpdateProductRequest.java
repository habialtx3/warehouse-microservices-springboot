package com.habialtx3.product_catalogue_service.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {

    private String name;


    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal price;


    @Column(name = "category_id")
    private String categoryId;
}
