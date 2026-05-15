package com.habialtx3.inventory_service.client;

import com.habialtx3.inventory_service.model.ProductResponse;
import com.habialtx3.inventory_service.model.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ProductClient {

    @Autowired
    private RestClient restClient;

    public ProductResponse getProductById(String id) {
        try {
            WebResponse<ProductResponse> response = restClient.get()
                    .uri("http://localhost:8080/api/products/{id}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            if (response == null || response.getData() == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                );
            }

            return response.getData();
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
    }
}
