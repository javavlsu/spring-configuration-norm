package com.example.demo.dao;

import com.example.demo.config.HibernateConfig;
import com.example.demo.model.Mouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
@Repository
@Primary
public class MouseDaoImpl implements MouseDao {
    @PersistenceUnit(unitName = "customEMF")
    private EntityManagerFactory factory;



    @Override
    public List<Mouse> getAllMouses() {
        List<Mouse> mouses = new ArrayList();
        try{

            EntityManager manager = factory.createEntityManager();
            Session session = manager.unwrap(Session.class);
            // Session session = factory.openSession();

            session.getTransaction().begin();
            mouses = session.createSQLQuery("select * from mouse").addEntity(Mouse.class).getResultList();
            session.getTransaction().commit();
            session.close();
            manager.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
           /* if (this.factory!=null && factory.isOpen())
                factory.close();*/
           /* if (factory.isOpen() && factory!=null)
                factory.close();*/
        }
        return mouses;

    }

    @Override
    public Mouse getDetails(int id) {
        Mouse mouse = new Mouse();
        try{
            EntityManager manager = factory.createEntityManager();
            Session session = (Session) manager.getDelegate();
           //Session session = manager.unwrap(Session.class);
            // Session session = factory.openSession();

            session.getTransaction().begin();
            mouse = session.get(Mouse.class, id);
            session.getTransaction().commit();
            session.close();
            manager.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
           /* if (this.factory!=null && factory.isOpen())
                factory.close();*/
        }
        return mouse;
    }

    @Override
    public void addingMouse(Mouse mouse) {
        try{
            EntityManager manager = factory.createEntityManager();
            Session session = (Session) manager.getDelegate();
            //Session session = manager.unwrap(Session.class);
            session.getTransaction().begin();
            session.save(mouse);
            session.getTransaction().commit();
            session.close();
            manager.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            /*if (factory != null && factory.isOpen())
                factory.close();*/
        }
    }

    @Override
    public void updatingMouse(Mouse mouseold, Mouse mouseNew) {
        try {
            EntityManager manager = factory.createEntityManager();
            Session session =(Session) manager.getDelegate();
           // Session session = manager.unwrap(Session.class);
            session.getTransaction().begin();
            Mouse m = (Mouse) session.createSQLQuery("select * from mouse where name=? and description=?").setParameter(1, mouseold.getName()).setParameter(2, mouseold.getDescription()).addEntity(Mouse.class).getSingleResult();
            if (m!=null){
                m.setName(mouseNew.getName());
                m.setDescription(mouseNew.getDescription());
                session.update(m);
            }
            session.getTransaction().commit();
            session.close();
            manager.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
           /* if (factory != null && factory.isOpen())
                factory.close();*/
        }
    }

    @Override
    public void removingMouse(int id) {
        try {
            EntityManager manager = factory.createEntityManager();
            Session session = (Session) manager.getDelegate();
            //Session session = manager.unwrap(Session.class);
            session.getTransaction().begin();
            session.delete(session.get(Mouse.class, id));
            session.getTransaction().commit();
            session.close();
            manager.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            /*if (factory != null && factory.isOpen())
                factory.close();*/
            //EntityManagerFactory->sessionfactory
        }
    }
}
