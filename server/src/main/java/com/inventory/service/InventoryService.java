package com.inventory.service;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;
import com.inventory.service.validation.InventoryValidator;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final ProductDAO productDAO;
    private final InventoryValidator validator;

    public InventoryService(ProductDAO productDAO, InventoryValidator validator) {
        this.productDAO = productDAO;
        this.validator = validator;
    }

    public void reduceStock(int id, int quantity) {

        validator.validateQuantity(quantity);

        Product product = productDAO.getProductById(id);

        int newStock = product.getStock() - quantity;

        if(newStock < 0) {
            throw new RuntimeException("Insufficient stock");
        }

        productDAO.updateStock(id, newStock);
    }

    public void increaseStock(int id, int quantity) {

        validator.validateQuantity(quantity);

        Product product = productDAO.getProductById(id);

        int newStock = product.getStock() + quantity;

        productDAO.updateStock(id, newStock);
    }
}
