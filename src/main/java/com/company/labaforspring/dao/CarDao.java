package com.company.labaforspring.dao;

import com.company.labaforspring.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAllCars();
    Car getCarById(int id);
    void addingCar(Car c);
    void removingCar(int id);
    void updatingCar(Car c);
    List<Car> searchingCarByName(String name);
}
