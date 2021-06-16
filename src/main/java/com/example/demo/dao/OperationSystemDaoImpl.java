package com.example.demo.dao;

import com.example.demo.model.OperationSystem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OperationSystemDaoImpl implements OperationSystemDao {
    private SessionFactory factory;
    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<OperationSystem> getAllOperationSystems() {
        List<OperationSystem> os = new ArrayList();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            os = session.createSQLQuery("select * from operationsystems").getResultList();
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
        return os;
    }

    @Override
    public OperationSystem getOperationSystemById(int id) {
        OperationSystem os = new OperationSystem();
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            os = session.get(OperationSystem.class, id);
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
        return os;
    }

    @Override
    public void addingOperationSystem(OperationSystem os) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.save(os);
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

    @Override
    public void updatingOperationSystem(OperationSystem osold, OperationSystem osnew) {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            OperationSystem os = (OperationSystem) session.createSQLQuery("select * from operationsystems where name=? and description=?").setParameter(1, osold.getName()).setParameter(2, osold.getDescription());
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

    @Override
    public void removingOperationSystem(int id) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            OperationSystem os = session.get(OperationSystem.class, id);
            if (os!=null){
                session.remove(os);
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
