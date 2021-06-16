package com.example.demo.dao;

import com.example.demo.model.OrderState;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary
public interface OrderStateDao {
    List<OrderState> getAllOrderStates();
    OrderState geOrderStateById(int id);
    void addingOrderState(OrderState os);
    void updatingOrderState(OrderState osold, OrderState osnew);
    void removingOrderState(int id);
}
