package com.habialtx3.inventory_service.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private String categoryId;

}

