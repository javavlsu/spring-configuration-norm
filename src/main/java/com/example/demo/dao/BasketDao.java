package com.example.demo.dao;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketDao {
    List<Product> getAllProductsFromBasket(String user_cipher);
    Product getDetails(String name);
    void checkout(String user_cipher);
    int getCountProductsInBasket(User user);
    void removeProductFromBasket(String user_cipher);
}
