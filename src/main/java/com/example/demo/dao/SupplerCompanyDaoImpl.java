package com.example.demo.dao;

import com.example.demo.model.SupplerCompany;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SupplerCompanyDaoImpl implements SupplerCompanyDao {
    private SessionFactory factory;
    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<SupplerCompany> getAllSupplerCompanies() {
        List<SupplerCompany> sk = new ArrayList();
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            sk = session.createSQLQuery("select * from suppler_companies").getResultList();
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
        return sk;
    }

    @Override
    public SupplerCompany getSupplerCompanyById(int id) {
        SupplerCompany company = new SupplerCompany();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            company = session.get(SupplerCompany.class, id);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory!= null && factory.isOpen())
                factory.close();
        }
        return company;
    }

    @Override
    public void addingSupplerCompany(SupplerCompany sk) {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();

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

    @Override
    public void removingSupplerCompany(int id) {
        try {
           Session session = factory.openSession();
           session.getTransaction().begin();
           SupplerCompany sk = session.get(SupplerCompany.class, id);
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

    @Override
    public void updatingSupplerCompany(SupplerCompany skold, SupplerCompany sknew ) {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            SupplerCompany sk = (SupplerCompany) session.createSQLQuery("select * from suppler_companies where name=?, address=?, description=?, rating=?").setParameter(1, skold.getName()).setParameter(2, skold.getDescription()).setParameter(3, skold.getRating()).getSingleResult();
            if (sk!=null){
                sk.setName(sknew.getName());
                sk.setDescription(sknew.getDescription());
                sk.setRating(sknew.getRating());
                session.update(sk);
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
