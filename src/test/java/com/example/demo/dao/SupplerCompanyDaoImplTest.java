package com.example.demo.dao;

import com.example.demo.model.SupplerCompany;
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
public class SupplerCompanyDaoImplTest {
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
    private SupplerCompanyDao dao;
    @Autowired
    public void setDao(SupplerCompanyDao dao) {
        this.dao = dao;
    }
    @Test
    public void getAllSupplerCompanies() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            List<SupplerCompany> array = new ArrayList();

            SupplerCompany company = new SupplerCompany("LG","Владимир, Центральная, д. 14", "это хорошая компания", 8.4);
            array.add(company);
            session.save(company);
            company = new SupplerCompany("Samsung","Владимир, Центральная, д. 45","Это хорошая компания",8.4);
            array.add(company);
            session.save(company);
            session.getTransaction().commit();
            Assert.assertEquals(dao.getAllSupplerCompanies().size(), array.size());
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
    public void getSupplerCompanyById() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            SupplerCompany sk = new SupplerCompany("Nokia","г. Владимир, ул. Зеленая, д. 18","это хорошая компания",8.7);
            session.save(sk);
            sk = (SupplerCompany) session.createSQLQuery("select * from supplercompanies where name=? and address=? and description=? and rating=?").setParameter(1,sk.getName()).setParameter(2,sk.getAddress()).setParameter(3, sk.getDescription()).setParameter(4, sk.getRating()).getSingleResult();
            if (sk!=null){
                Assert.assertEquals(sk, dao.getSupplerCompanyById(sk.getId()));
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
            if (factory!=null && factory.isOpen()){
                factory.close();
            }
        }
    }

    @Test
    public void addingSupplerCompany() {
        try{
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

    @Test
    public void removingSupplerCompany() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            SupplerCompany sk = new SupplerCompany("HP", "г. Владимир, ул. Центральная, д. 45", "это надежная компания",8.5);
            session.save(sk);
            sk = (SupplerCompany) session.createSQLQuery("select * from suppler_companies where name=? and description=? and address=? and rating=?").setParameter(1,sk.getName()).setParameter(2, sk.getDescription()).setParameter(3,sk.getAddress()).setParameter(4,sk.getRating()).getSingleResult();
            if (sk!=null){
               dao.removingSupplerCompany(sk.getId());
                sk = (SupplerCompany) session.createSQLQuery("select * from suppler_companies where name=? and description=? and address=? and rating=?").setParameter(1,sk.getName()).setParameter(2, sk.getDescription()).setParameter(3,sk.getAddress()).setParameter(4,sk.getRating()).getSingleResult();
                Assert.assertNull(sk);
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
            if (factory.isOpen() &&  factory!=null){
                factory.close();
            }
        }
    }

    @Test
    public void updatingSupplerCompany() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            SupplerCompany sk = new SupplerCompany("Samsung","г. Владимир, ул. Центральная, д. 14","это надежная компания",8.4);
            session.save(sk);
            SupplerCompany sk1 = new SupplerCompany("NEWSamsung","г. Владимир, ул. Центральная, д. 45","это надежная компания",9.4);
            dao.updatingSupplerCompany(sk, sk1);
            sk1 = (SupplerCompany) session.createSQLQuery("select * from suppler_companies where name=? and  address=? and description=? and rating=?").setParameter(1, sk.getName()).setParameter(2,sk.getAddress()).setParameter(3, sk.getDescription()).setParameter(4,sk.getRating()).getSingleResult();
            if (sk1!=null){
                Assert.assertEquals(sk1.getName(), "NEWSamsung");
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
            if (factory!=null && factory.isOpen());
        }
    }
}