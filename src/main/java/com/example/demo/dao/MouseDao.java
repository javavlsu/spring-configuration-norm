package com.example.demo.dao;

import com.example.demo.model.Mouse;

import java.util.List;

public interface MouseDao {
    List<Mouse> getAllMouses();
    Mouse getDetails(int id);
    void addingMouse(Mouse mouse);
    void updatingMouse(Mouse mouseold, Mouse mouseNew);
    void removingMouse(int id);
}
