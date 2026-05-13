package com.habialtx3.inventory_service.service;

import com.habialtx3.inventory_service.entity.Inventory;
import com.habialtx3.inventory_service.model.CreateInventoryRequest;
import com.habialtx3.inventory_service.model.InventoryResponse;
import com.habialtx3.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ValidationService validation;

    private InventoryResponse toInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .stockQuantity(inventory.getStockQuantity())
                .warehouseLocation(inventory.getWarehouseLocation())
                .build();
    }

    @Transactional
    public InventoryResponse create(CreateInventoryRequest request) {
        Inventory inventory = new Inventory();

        inventory.setProductId(request.getProductId());
        inventory.setStockQuantity(request.getStockQuantity());
        inventory.setWarehouseLocation(request.getWarehouseLocation());

        inventoryRepository.save(inventory);

        return toInventoryResponse(inventory);
    }
}
