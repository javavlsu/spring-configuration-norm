package com.example.demo.dao;

import com.example.demo.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao{
    private SessionFactory factory;
    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            categories = session.createSQLQuery("select * from categories").getResultList();
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
        return categories;
    }

    @Override
    public Category getCategoryById(int id) {
        Category category = new Category();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            category = session.get(Category.class, id);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (factory!=null && factory.isOpen())
                factory.isOpen();
        }
        return category;
    }

    @Override
    public void addingCategory(Category c) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.save(c);
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
    }

    @Override
    public void updatingCategory(Category oldc, Category newc) {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            Category category = (Category) session.createSQLQuery("select * from categories where category=? and description=?").setParameter(1, oldc.getName()).setParameter(2, oldc.getDescription());
            if (category!=null){
                category.setName(newc.getName());
                category.setDescription(newc.getDescription());
                session.update(category);
            }
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception ex){

        }
        finally {
            if (factory!= null && factory.isOpen())
                factory.close();
        }
    }

    @Override
    public void removingCategory(int id) {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            Category category = session.get(Category.class, id);
            if (category!=null){
                session.delete(category);
            }
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
}
