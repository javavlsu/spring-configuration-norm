package com.example.demo.dao;

import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDao {
    List<Product> getAllProducts();
    Product getProductById(int id);
    void addingProduct(Product product);
    void updatingProduct (Product product);
    void removingProduct(int id);
    public void addingProductToBasket(String user_cipher, Product product);
    List<Product> searchingByName(String name);
    List<Product> searchingByCategory(String category);
    List<Product> searchingByOperationSystem(String os);
    List<Product> searchingBySupplerCompany(String name);
}
