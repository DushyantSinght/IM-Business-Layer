package com.inventory.service;

import com.inventory.Report.InventoryReportService;
import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;
import com.inventory.service.validation.InventoryValidator;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final ProductDAO productDAO;
    private final InventoryValidator validator;
    private final InventoryReportService reportService;

    public InventoryService(ProductDAO productDAO,
                            InventoryValidator validator,
                            InventoryReportService reportService) {
        this.productDAO = productDAO;
        this.validator = validator;
        this.reportService = reportService;
    }

    //  Reduce stock
    public void reduceStock(int id, int quantity) {

        System.out.println("[Inventory] Reducing stock | ID: " + id + ", Qty: " + quantity);

        validator.validateQuantity(quantity);

        Product product = productDAO.getProductById(id);

        validator.validateProductExists(product, id);
        validator.validateStock(product.getStock(), quantity);

        int newStock = product.getStock() - quantity;

        productDAO.updateStock(id, newStock);

        System.out.println("[Inventory] Stock reduced successfully | New Stock: " + newStock);

        reportService.checkAndSendLowStockAlert();
    }

    //  Increase stock
    public void increaseStock(int id, int quantity) {

        System.out.println("[Inventory] Increasing stock | ID: " + id + ", Qty: " + quantity);

        validator.validateQuantity(quantity);

        Product product = productDAO.getProductById(id);

        validator.validateProductExists(product, id);

        int newStock = product.getStock() + quantity;

        productDAO.updateStock(id, newStock);

        System.out.println("[Inventory] Stock increased successfully | New Stock: " + newStock);
    }


    public int getStock(int id) {

        Product product = productDAO.getProductById(id);

        validator.validateProductExists(product, id);

        return product.getStock();
    }
}