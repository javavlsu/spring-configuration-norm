package com.example.demo.dao;

import com.example.demo.model.PayMethod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
public class PayMethodDaoImplTest {
    @Inject
    private ApplicationContext context;
    @Autowired
    public void setContext() {
        this.context = new AnnotationConfigApplicationContext();
    }
    SessionFactory factory;
    @Autowired
    public void setFactory() {
        this.factory = context.getBean(SessionFactory.class);
    }

    private PayMethodDao dao;
    @Autowired
    public void setDao(PayMethodDao dao) {
        this.dao = dao;
    }

    @Test
    public void getAllPayMethods() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            List<PayMethod> array = new ArrayList();
            PayMethod method = new PayMethod("PayAniWay", "Оплата при помощи сервиса онлайн платежей");
            session.save(method);
            Assert.assertEquals(array.size(), dao.getAllPayMethods().size());
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory!=null && factory.isOpen())
                factory.close();
        }
    }

    @Test
    public void getPayMethodById() {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            PayMethod  method = new PayMethod("Яндекс.Деньги","Оплата при помощи сервиса онлайн платежей");
            session.save(method);
            method = (PayMethod) session.createSQLQuery("select * from paymethods where pay_method=? and description=?").setParameter(1, method.getName()).setParameter(2,method.getDescription()).getSingleResult();
            Assert.assertNotNull(method);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory!=null && factory.isOpen())
                factory.close();
        }
    }

    @Test
    public void addingPayMethod() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            PayMethod method = new PayMethod("WebMoney", "доставка при помощи сервиса онлайн платежей");
            session.save(method);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory!=null && factory.isOpen())
                factory.close();
        }
    }

    @Test
    public void updatingPayMethod() {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            PayMethod pm = new PayMethod("Apple Pay","оплата при помощи сервиса сервиса онлайн платежей");
            session.save(pm);
            PayMethod newpm = new PayMethod("new apple pay","оплата при помощи  сервиса онлайн платежей");
            dao.updatingPayMethod(pm, newpm);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
           ex.printStackTrace();
        }
        finally {
            if (factory!=null && factory.isOpen())
                factory.close();
        }
    }

    @Test
    public void removingPayMethod() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            PayMethod pm = new PayMethod("Some service","Оплата при помощи сервиса онлайн платежей");
            session.save(pm);
            PayMethod method = (PayMethod) session.createSQLQuery("select * from paymethods where pay_method=? and description=?").setParameter(1,pm.getName()).setParameter(2,pm.getDescription()).getSingleResult();

            if (method!=null){
                dao.removingPayMethod(method.getId());
                method = (PayMethod) session.createSQLQuery("select * from paymethods where pay_method=? and description=?").setParameter(1,pm.getName()).setParameter(2,pm.getDescription()).getSingleResult();
                Assert.assertNull(method);
            }
            else {
                Assert.assertTrue(false);
            }
            session.getTransaction().commit();
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
}