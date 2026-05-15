package com.habialtx3.inventory_service.repository;

import com.habialtx3.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findAllByProductId(String productId);
}
