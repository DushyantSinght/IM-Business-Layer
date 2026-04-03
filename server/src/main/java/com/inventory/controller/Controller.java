package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import com.inventory.service.InventoryService;
import com.inventory.Report.InventoryReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final ProductService productService;
    private final InventoryService inventoryService;
    private final InventoryReportService reportService;

    public Controller(ProductService productService,
                      InventoryService inventoryService,
                      InventoryReportService reportService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.reportService = reportService;
    }

    // ---------------- PRODUCT ----------------

    @PostMapping("/product")
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "Product added";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Deleted";
    }
    
    // ---------------- INVENTORY ----------------

    @PostMapping("/reduce/{id}/{qty}")
    public String reduceStock(@PathVariable int id,
                              @PathVariable int qty) {
        inventoryService.reduceStock(id, qty);
        return "Stock reduced";
    }

    @PostMapping("/increase/{id}/{qty}")
    public String increaseStock(@PathVariable int id,
                                @PathVariable int qty) {
        inventoryService.increaseStock(id, qty);
        return "Stock increased";
    }
    
        // ---------------- REPORT ----------------
    @GetMapping("/report")
    public String generateReport(){
        System.out.println("Controller: Generating report...");
        reportService.checkAndSendLowStockAlert();
        return "Report generated - check your email";
    }
}
