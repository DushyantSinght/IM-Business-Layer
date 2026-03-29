package com.inventory.controller;

import com.inventory.service.ProductService;
import com.inventory.Report.InventoryReportService;
import com.inventory.service.InventoryService;

public class Controller {

    ProductService productService = new ProductService();
    InventoryService inventoryService = new InventoryService();
    InventoryReportService reportService = new InventoryReportService();
// I have commented out so, that I can run my files feel free to change and
// add CRUD methods in their code files and their methods to controller.

//    public void addProduct(String name, double price, int quantity){
//        System.out.println("Controller: Adding product...");
//        productService.addProduct(name, price, quantity);
//    }
//
//    public void updateStock(int productId, int quantity){
//        System.out.println("Controller: Updating stock...");
//        inventoryService.updateStock(productId, quantity);
//    }

    public void generateReport(){
        System.out.println("Controller: Generating report...");
        reportService.checkAndSendLowStockAlert();
    }
}