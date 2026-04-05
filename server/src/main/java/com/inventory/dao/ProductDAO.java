package com.inventory.dao;

import com.inventory.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO products(name,price,stock,category) VALUES(?,?,?,?)";

        jdbcTemplate.update(sql,
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory());
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("category"),
                        rs.getInt("minQuantity")
                ));
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id=?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("category"),
                        rs.getInt("minQuantity")
                ),
                id);
    }

    public void updateStock(int id, int stock) {
        String sql = "UPDATE products SET stock=? WHERE id=?";
        jdbcTemplate.update(sql, stock, id);
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
