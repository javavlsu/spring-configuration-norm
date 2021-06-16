package com.example.demo.dao;

import com.example.demo.model.PayMethod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class PayMethodDaoImpl implements PayMethodDao {
    private SessionFactory factory;
    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<PayMethod> getAllPayMethods() {
        List<PayMethod> array = new ArrayList();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            array = session.createSQLQuery("select * from supplercompanies").getResultList();
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
        return array;
    }

    @Override
    public PayMethod getPayMethodById(int id) {
        PayMethod method = new PayMethod();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            method = session.get(PayMethod.class, id);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){

        }
        finally {
            if (factory.isOpen() && factory!=null)
                factory.close();
        }
        return method;
    }

    @Override
    public void addingPayMethod(PayMethod pm) {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.save(pm);
            session.getTransaction().commit();
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
    public void updatingPayMethod(PayMethod oldpm, PayMethod newpm) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            PayMethod pm = (PayMethod)session.createSQLQuery("select * from paymethods where name=? and description=?").setParameter(1,oldpm.getName()).setParameter(2, oldpm.getDescription()).getSingleResult();
            pm.setName(newpm.getName());
            pm.setDescription(pm.getDescription());
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
    public void removingPayMethod(int id) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.remove(id);
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
