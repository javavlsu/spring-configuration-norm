package com.example.demo.dao;

import com.example.demo.model.DeliveryMethod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DeliveryMethodDaoImpl implements DeliveryMethodDao {
    private Logger logger = LoggerFactory.getLogger(DeliveryMethodDaoImpl.class);
    private SessionFactory factory;
    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<DeliveryMethod> getAllDeliveryMethods() {
        List<DeliveryMethod> array = new ArrayList();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            array = session.createSQLQuery("select * from deliverymethods").getResultList();
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            logger.error("exception");
            ex.printStackTrace();
        }
        finally {
            if (factory.isOpen() && factory!=null){
                factory.close();
            }
        }
        return array;
    }

    @Override
    public DeliveryMethod getDetails(int id) {
        DeliveryMethod dm = new DeliveryMethod();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            dm = session.get(DeliveryMethod.class, id);
            session.getTransaction().commit();
        }
        catch (Exception ex){
            logger.error("please enter delivery method");
            ex.printStackTrace();
        }
        finally {
            if (factory.isOpen() && factory!=null){
                factory.close();
            }
        }
        return dm;
    }

    @Override
    public void addingDeliveryMethod(DeliveryMethod dm) {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.save(dm);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            logger.error("please enter pay method");
            ex.printStackTrace();
        }
        finally {
            if (factory.isOpen() && factory!=null){
                factory.close();
            }
        }
    }

    @Override
    public void updatingDeliveryMethod(DeliveryMethod dmold, DeliveryMethod dmnew) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            DeliveryMethod dm = (DeliveryMethod) session.createSQLQuery("select * fom deliverymethods where delivery_method=? ana description=?").setParameter(1, dmold.getName()).setParameter(2, dmold.getDescription()).getSingleResult();
            if (dm!=null){
                session.createSQLQuery("update deliverymethods set delivery_method=?, description=? where id=?").setParameter(1, dmnew.getName()).setParameter(2, dmnew.getDescription()).setParameter(3, dm.getId()).executeUpdate();
            }
            session.getTransaction().commit();
        }
        catch (Exception ex){
            logger.error("exception");
            ex.printStackTrace();
        }
    }


    @Override
    public void removingDeliveryMethod(int id) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            DeliveryMethod dm = getDetails(id);
            if (dm!=null)
            {
                session.delete(dm);
            }
            session.getTransaction().commit();
        }
        catch (Exception ex){
            logger.error("error");
            ex.printStackTrace();
        }
        finally {
            if (factory.isOpen() && factory!=null){
                factory.close();
            }
        }
    }

}
