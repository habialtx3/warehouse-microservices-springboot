package com.habialtx3.inventory_service.service;

import com.habialtx3.inventory_service.entity.Inventory;
import com.habialtx3.inventory_service.model.*;
import com.habialtx3.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private RestClient restClient;

    @Autowired
    private ValidationService validation;

    private InventoryResponse toInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder().id(inventory.getId()).productId(inventory.getProductId()).stockQuantity(inventory.getStockQuantity()).warehouseLocation(inventory.getWarehouseLocation()).build();
    }

    @Transactional
    public InventoryResponse create(CreateInventoryRequest request) {
        try {
            WebResponse<ProductResponse> response = restClient.get()
                    .uri("http://localhost:8080/api/products/{id}", request.getProductId())
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});

            if (response == null || response.getData() == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                );
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found"
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Product service not available"
            );
        }


        Inventory inventory = new Inventory();

        inventory.setProductId(request.getProductId());
        inventory.setStockQuantity(request.getStockQuantity());
        inventory.setWarehouseLocation(request.getWarehouseLocation());

        inventoryRepository.save(inventory);

        return toInventoryResponse(inventory);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> list() {

        List<Inventory> inventories = inventoryRepository.findAll();

        return inventories.stream().map(inventory -> toInventoryResponse(inventory)).toList();
    }

    @Transactional(readOnly = true)
    public InventoryResponse getById(String id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory id not found")
        );

        return toInventoryResponse(inventory);
    }


    @Transactional(readOnly = true)
    public List<InventoryResponse> getByProductId(String id) {
        List<Inventory> inventories = inventoryRepository.findAllByProductId(id);

        if (inventories.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Inventory from Product " + id + " is not found"
            );
        }
        return inventories.stream().map(inventory -> toInventoryResponse(inventory)).toList();
    }

    @Transactional
    public InventoryResponse update(String id, UpdateInventoryRequest request) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory id not found")
        );

        if (Objects.nonNull(request.getProductId())) {
            inventory.setProductId(request.getProductId());
        }

        if (Objects.nonNull(request.getWarehouseLocation())) {
            inventory.setWarehouseLocation(request.getWarehouseLocation());
        }

        if (Objects.nonNull(request.getStockQuantity())) {
            inventory.setStockQuantity(request.getStockQuantity());
        }

        inventoryRepository.save(inventory);

        return toInventoryResponse(inventory);
    }

    public void  delete(String id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory id not found")
        );

        inventoryRepository.delete(inventory);


    }
}
