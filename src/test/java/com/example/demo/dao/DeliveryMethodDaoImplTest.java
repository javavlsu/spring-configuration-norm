package com.example.demo.dao;

import com.example.demo.model.DeliveryMethod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class DeliveryMethodDaoImplTest {
    @Inject
    private ApplicationContext context;
    @Autowired
    private void setContext(){
        this.context = new AnnotationConfigApplicationContext();
    }
    private  SessionFactory factory;
    private  DeliveryMethodDao dao;
    @Autowired
    public void setFactory() {
        this.factory = context.getBean(SessionFactory.class);
    }
     @Autowired
    public void setDao(DeliveryMethodDao dao) {
        this.dao = dao;
    }

    @Test
    void getAllDeliveryMethods() {
        try{

            List<DeliveryMethod> dm = new ArrayList();
            DeliveryMethod item = new DeliveryMethod();
            Session session = factory.getCurrentSession();
            session.getTransaction().begin();
            item = new DeliveryMethod("FledEX", "Доставка при помощи транспортной компании");
            session.save(item);
            item = new DeliveryMethod("ГрандТранстпорт", "Доставка при помощи транспортонй компании");
            session.save(item);
            session.getTransaction().commit();
            dm = dao.getAllDeliveryMethods();
            Assert.assertEquals(dm.size(), 2);
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory.isOpen() && factory!=null)
                factory.close();
        }
    }

    @Test
    void getDetails() {
        try
        {
            Session session = factory.openSession();
            DeliveryMethod data = new DeliveryMethod("DHL","доставка при помощи транспортной компании");
            session.getTransaction().begin();
            session.save(data);
            session.getTransaction().commit();
            DeliveryMethod dm = (DeliveryMethod) session.createSQLQuery("select * from deliverymethods where name=? and description=?").setParameter(1, data.getName()).setParameter(2, data.getDescription()).getSingleResult();
            if (dm!=null){
                data = dao.getDetails(dm.getId());
            }

            Assert.assertEquals(data, dm);
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory.isOpen() && factory!= null){
                factory.close();
            }
        }
    }

    @Test
    void addingDeliveryMethod() {
       try{
           Session session = factory.openSession();
           DeliveryMethod method = new DeliveryMethod("DHL", "Доставка при помощи транспортной компании");
           dao.addingDeliveryMethod(method);
           session.getTransaction().begin();
           DeliveryMethod m = (DeliveryMethod) session.createSQLQuery("select * from deliverymethods where delivery_method=? and descripition=?").setParameter(1,method.getName()).setParameter(2,method.getDescription()).getSingleResult();
           Assert.assertNotNull(m);
           session.getTransaction().commit();
           session.close();

        }
       catch (Exception ex){
           ex.printStackTrace();
       }
       finally {
           if (factory.isOpen() && factory!=null){
               factory.close();
           }
       }
    }

    @Test
    void updatingDeliveryMethod() {
       try{
           Session session = factory.openSession();
           session.getTransaction().begin();
           DeliveryMethod method = new DeliveryMethod("FledEX","Доставка при помощи транспортной компании");
           session.save(method);
           DeliveryMethod newdm = new DeliveryMethod("NewFledex", "Новая служба доставки");
           dao.updatingDeliveryMethod(method, newdm);
           method = (DeliveryMethod) session.createSQLQuery("select * from deliverymethods where delivery_method=? and description=?").setParameter(1, newdm.getName()).setParameter(2, newdm.getDescription()).getSingleResult();
           Assert.assertNotNull(method);
           session.getTransaction().commit();
           session.close();
       }
       catch (Exception exception){
           exception.printStackTrace();
       }
       finally {
           if (factory.isOpen() && factory!=null){
               factory.close();
           }
       }
    }

    @Test
    void removingDeliveryMethod() {
       try{
           Session session = factory.openSession();
           session.getTransaction().begin();
           DeliveryMethod method = new DeliveryMethod("DSE","Доставка при помощи транспортной компании");
           session.save(method);
           DeliveryMethod dm = (DeliveryMethod) session.createSQLQuery("select * from  deliverymethods where delivery_method and description = ?").setParameter(1, method.getName()).setParameter(2, method.getDescription()).getSingleResult();
           if (dm!=null){
               dao.removingDeliveryMethod(dm.getId());
               method =(DeliveryMethod) session.createSQLQuery("select * from  deliverymethods where delivery_method and description = ?").setParameter(1, method.getName()).setParameter(2, method.getDescription()).getSingleResult();
               Assert.assertNull(method);
           }

           session.getTransaction().commit();
           session.close();
       }
       catch (Exception ex){
           ex.printStackTrace();
       }
       finally {
           if (factory.isOpen() && factory!=null){
               factory.close();
           }
       }
    }

}