package com.habialtx3.inventory_service.controller;

import com.habialtx3.inventory_service.model.CreateInventoryRequest;
import com.habialtx3.inventory_service.model.InventoryResponse;
import com.habialtx3.inventory_service.model.WebResponse;
import com.habialtx3.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<InventoryResponse> create(@RequestBody CreateInventoryRequest request) {
        InventoryResponse response = inventoryService.create(request);

        return WebResponse.<InventoryResponse>builder()
                .data(response)
                .build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<InventoryResponse>> list() {
        List<InventoryResponse> responses = inventoryService.list();
        return WebResponse.<List<InventoryResponse>>builder()
                .data(responses)
                .build();
    }
}
