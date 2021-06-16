package com.example.demo.dao;

import com.example.demo.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    List<Order> getAllOrder();
    Order getDetails(int id);
    void updatingOrder(Order order);
}
