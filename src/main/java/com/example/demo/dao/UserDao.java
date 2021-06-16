package com.example.demo.dao;

import com.example.demo.model.Position;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {
    public List<User> getAllUsers();
    User getUserById(int id);
    void addingUser(User user);
    void updatingUser(User user);
    void removingUser(int id);
    List<Position> getPositionsForUser(String cipher);
}
