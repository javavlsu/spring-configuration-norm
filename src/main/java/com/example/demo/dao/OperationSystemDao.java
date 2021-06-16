package com.example.demo.dao;

import com.example.demo.model.OperationSystem;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary
public interface OperationSystemDao {
    public List<OperationSystem> getAllOperationSystems();
    public OperationSystem getOperationSystemById(int id);
    public void addingOperationSystem(OperationSystem os);
    public void updatingOperationSystem(OperationSystem osold, OperationSystem osnew);
    public void removingOperationSystem(int id);
}
