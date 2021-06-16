package com.example.demo.dao;

import com.example.demo.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class CategoryDaoImplTest {
    @Inject
    private ApplicationContext context;
    @Autowired
    public void setContext() {
        this.context = new AnnotationConfigApplicationContext();
    }

    SessionFactory factory;
    CategoryDao dao;
    @Autowired
    public void setFactory() {
        this.factory = context.getBean(SessionFactory.class);
    }
    @Autowired
    public void setDao(CategoryDao dao) {
        this.dao = dao;
    }

    @Test
    void getAllCategories() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.save(new Category("Сотовые телефоны", "это категория сотовых телефонов"));
            session.save(new Category("Ноутбуки","это категория ноутбуков"));
            session.getTransaction().commit();
            List<Category> data = dao.getAllCategories();
            Assert.assertEquals(data.size(), 2);
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
    void getCategoryById() {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.save(new Category("Компьютеры","эта категория компьютеров"));
            Category cat = (Category) session.createSQLQuery("select * from categories where category=? and description=?").setParameter(1,"Компьютеры").setParameter(2,"это категория компьютеров").getSingleResult();
            session.getTransaction().commit();
            if (cat!=null){
                Assert.assertEquals(cat, dao.getCategoryById(cat.getId()));
            }
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
    void addingCategory() {
        try {
            Session session = factory.openSession();

        }
        catch (Exception ex){

        }
        finally {

        }
    }

    @Test
    void updatingCategory() {
    }

    @Test
    void removingCategory() {
    }
}