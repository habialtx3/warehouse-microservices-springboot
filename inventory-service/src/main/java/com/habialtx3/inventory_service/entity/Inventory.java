package com.habialtx3.inventory_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @NotNull
    @Size(max = 20)
    private String productId;

    @NotNull
    private Integer stockQuantity;

    @Size(max = 20)
    @NotNull
    private String warehouseLocation;


}
