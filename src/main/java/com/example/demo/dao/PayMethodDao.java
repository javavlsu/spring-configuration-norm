package com.example.demo.dao;

import com.example.demo.model.PayMethod;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary
public interface PayMethodDao {
    public List<PayMethod> getAllPayMethods();
    PayMethod getPayMethodById(int id);
    void addingPayMethod(PayMethod pm);
    void updatingPayMethod(PayMethod oldpm, PayMethod newpm);
    void removingPayMethod(int id);
}
