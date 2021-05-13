package com.company.labaforspring.service;

import com.company.labaforspring.dao.CarDao;
import com.company.labaforspring.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
 @Service
public class CarServiceImpl implements CarService {
    private CarDao cars;
    @Autowired
    public void setCarsDao(CarDao sere){
        this.cars = sere;
    }

    @Override
    @Transactional
    public List<Car> getAllCars() {
        return  cars.getAllCars();
    }

    @Override
    @Transactional
    public Car getCarById(int id) {
        return cars.getCarById(id);
    }

    @Override
    @Transactional
    public void addingCar(Car c) {
       cars.addingCar(c);
    }

    @Override
    @Transactional
    public void removingCar(int id) {
        cars.removingCar(id);
    }

    @Override
    @Transactional
    public void updatingCar(Car c) {
        cars.updatingCar(c);
    }

    @Override
    @Transactional
    public List<Car> searchingCarByName(String name) {
        return cars.searchingCarByName(name);
    }
}
