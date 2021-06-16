package com.example.demo.dao;

import com.example.demo.model.DeliveryMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary

public interface DeliveryMethodDao {
    List<DeliveryMethod> getAllDeliveryMethods();
    DeliveryMethod getDetails(int id);
    void addingDeliveryMethod(DeliveryMethod dm);
    void updatingDeliveryMethod(DeliveryMethod dmold, DeliveryMethod dmnew);
    void removingDeliveryMethod(int id);

}
