package com.example.demo.sevice;

import com.example.demo.dao.MouseDao;
import com.example.demo.model.Mouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class MouseServiceImpl implements MouseService {
    private MouseDao dao;
    @Autowired
    public void setDao(MouseDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Mouse> getAllMouses() {
        return dao.getAllMouses();
    }

    @Override
    public Mouse getDetails(int id) {
        return dao.getDetails(id);
    }

    @Override
    public void addingMouse(Mouse mouse) {
        dao.addingMouse(mouse);
    }

    @Override
    public void updatingMouse(Mouse mouseold, Mouse mouseNew) {
        dao.updatingMouse(mouseold, mouseNew);
    }

    @Override
    public void removingMouse(int id) {
        dao.removingMouse(id);
    }
}
