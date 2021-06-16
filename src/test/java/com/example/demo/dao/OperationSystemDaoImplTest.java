package com.example.demo.dao;

import com.example.demo.model.OperationSystem;
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
public class OperationSystemDaoImplTest {
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
    private OperationSystemDao dao;
    @Autowired
    public void setDao(OperationSystemDao dao) {
        this.dao = dao;
    }

    @Test
    public void getAllOperationSystems() {
        List<OperationSystem> osl = new ArrayList();
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            OperationSystem item = new OperationSystem("linux","this is operation system");
            session.save(item);
            osl.add(item);
            item = new OperationSystem("windows","this is operation system");
            session.save(item);
            osl.add(item);
            Assert.assertEquals(osl.size(), dao.getAllOperationSystems().size());
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
    public void getOperationSystemById() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            OperationSystem os = new OperationSystem("MAC OS","this is operation system");
            session.save(os);
            OperationSystem data = (OperationSystem) session.createSQLQuery("select * from operationsystems where name =? and description=?").setParameter(1, os.getName()).setParameter(2, os.getDescription()).getSingleResult();
            if (data!=null){
                Assert.assertEquals(data, dao.getOperationSystemById(data.getId()));
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

    @Test
    public void addingOperationSystem() {
        try{
            Session session = factory.openSession();
            OperationSystem os = new OperationSystem("Data","this is operation system");
            session.getTransaction().begin();
            session.save(os);
            OperationSystem item = (OperationSystem) session.createSQLQuery("select * from operationsystems where name=? and description=?").setParameter(1, os.getName()).setParameter(2, os.getDescription()).getSingleResult();
            if (item!=null){
                Assert.assertEquals(dao.getOperationSystemById(item.getId()), item);
            }
            else{
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

    @Test
    public void updatingOperationSystem() {
        try {

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            
        }
    }

    @Test
    public void removingOperationSystem() {
    }
}