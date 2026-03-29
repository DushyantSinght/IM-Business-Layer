package com.inventory.service.validation;

import org.springframework.stereotype.Component;

@Component
public class InventoryValidator {

    public void validateQuantity(int quantity) {

        if(quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }
    }
}
