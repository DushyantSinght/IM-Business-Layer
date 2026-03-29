package com.inventory.service;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;
import com.inventory.service.validation.ProductValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;
    private final ProductValidator validator;

    public ProductService(ProductDAO productDAO, ProductValidator validator) {
        this.productDAO = productDAO;
        this.validator = validator;
    }

    public void addProduct(Product product) {
        validator.validate(product);
        productDAO.addProduct(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    public Product getProduct(int id) {
        return productDAO.getProductById(id);
    }
}
