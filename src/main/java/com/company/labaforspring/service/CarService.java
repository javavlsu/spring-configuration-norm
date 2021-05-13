package com.company.labaforspring.service;

import com.company.labaforspring.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(int id);
    void addingCar(Car c);
    void removingCar(int id);
    void updatingCar(Car c);
    List<Car> searchingCarByName(String name);
}
