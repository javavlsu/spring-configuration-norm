package com.example.demo.sevice;

import com.example.demo.model.Mouse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MouseService {
    List<Mouse> getAllMouses();
    Mouse getDetails(int id);
    void addingMouse(Mouse mouse);
    void updatingMouse(Mouse mouseold, Mouse mouseNew);
    void removingMouse(int id);
}
