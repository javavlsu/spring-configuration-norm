package com.example.demo.dao;

import com.example.demo.model.Position;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PositionDao {
    List<Position> getAllPositions();
    Position getPositionById(int id);
    void addingPosition(Position position);
    void updatingPosition(Position pos);
    void removingPosition(int id);
    List<User> getUsersInThisPosition(String name);
    public void addingUserToRole(String pos_name, String user_cipher);
    public void removingUserFromRole(String pos_name, String user_cipher);
}
