package com.company.labaforspring.dao;

import com.company.labaforspring.model.Car;

import org.apache.logging.log4j.core.LogEvent;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.*;
import com.company.labaforspring.model.Car;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.*;
import org.springframework.stereotype.Service;

@Repository(value = "CarDao")
@Transactional
public class CarDaoImpl implements CarDao {
    static Logger logger = LoggerFactory.getLogger(CarDaoImpl.class);
    @PersistenceContext
    EntityManager manager;
    @Autowired
    private void setEntityManager(EntityManager em){
        manager = em;
    }
    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try{
            cars =  manager.createNativeQuery("select * from car", Car.class).getResultList();
        }
        catch (TransactionException ex){
            logger.debug("throwable transaction exception "+ex);
        }
        catch (Exception e2){
            logger.debug("throwable other exception "+e2);
        }
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        Car c = new Car();
        try{
            Query query =  manager.createNativeQuery("select * from car where id=?", Car.class);
            query.setParameter(1, id);
            c = (Car)query.getSingleResult();
        }
        catch (TransactionException ex){
            logger.info("throwing exception"+ex);
        }
        catch (Exception ex4){
            logger.debug("throwing exception "+ex4);
        }
        return c;
    }

    @Override
    public void addingCar(Car c) {
        try{
            manager.getTransaction().begin();
            manager.getTransaction().begin();
            manager.persist(c);
            manager.getTransaction().commit();
        }
        catch (Exception ex){
            logger.info("throwing exception "+ex);

        }
    }

    @Override
    public void removingCar(int id) {
        try {
            if (id>0){
                manager.getTransaction().commit();
                Car c = getCarById(id);
                manager.remove(c);
                manager.getTransaction().commit();
            }
            else {
                logger.info("please enter correct values");
            }
        }
        catch (Exception ex){
            logger.info("throwing exception "+ex);
        }
    }

    @Override
    public void updatingCar(Car c) {
        if (c.getName()!=null && c.getDescription() != null){
            try{
                manager.getTransaction().begin();
                Query query = manager.createNativeQuery("select * from cars where date = ?  name =? and description = ?");
                query.setParameter(1, new java.sql.Date(c.getDate().getTime()));
                query.setParameter(2, c.getName());
                query.setParameter(3, c.getDescription());
                Car car = (Car)query.getSingleResult();
                car.setName(c.getName());
                car.setDescription(c.getDescription());
                car.setDate(c.getDate());
                manager.merge(car);
                manager.getTransaction().commit();
            }
            catch (Exception ex){
                logger.info("throwing exception "+ex);
            }

        }
    }

    @Override
    public List<Car> searchingCarByName(String name) {
        List<Car> cars = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("select  * from cars where name =?");
            query.setParameter(1,name);
            cars = query.getResultList();
        }
        catch (Exception ex){
            logger.info("throw exception"+ex);
        }
        return cars;
    }
}
