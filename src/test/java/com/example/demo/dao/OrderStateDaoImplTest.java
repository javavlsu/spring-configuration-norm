package com.example.demo.dao;

import com.example.demo.model.OrderState;
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
public class OrderStateDaoImplTest {
    @Inject
    private ApplicationContext context;
    @Autowired
    private void setContext(){
        this.context = new AnnotationConfigApplicationContext();
    }
    private SessionFactory factory;
    private OrderStateDao dao;
    @Autowired
    public void setFactory() {
        this.factory = context.getBean(SessionFactory.class);
    }
    @Autowired
    public void setDao(OrderStateDao dao) {
        this.dao = dao;
    }

    @Test
    public void getAllOrderStates() {
        try{
            Session session = factory.openSession();
            List<OrderState> os = new ArrayList();
            OrderState item = new OrderState("Заказ обрабатывается","этот заказ обрабатывается");
            os.add(item);
            item = new OrderState("Заказ обработан","Этот заказ обработан");
            os.add(item);
            Assert.assertEquals(os.size(), dao.getAllOrderStates().size());
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
    public void geOrderStateById() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            OrderState os = new OrderState("заказ принят", "заказ принят в обработку");
            session.save(os);
            OrderState state = (OrderState) session.createSQLQuery("select * from orderstates where orderstate=? and description=?").setParameter(1, os.getName()).setParameter(2, os.getDescription());
            if (state!=null){
                OrderState item = dao.geOrderStateById(state.getId());
                Assert.assertEquals(item, state);
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
    public void addingOrderState() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            OrderState state = new OrderState("заказ отменен","заказ отменен");
            dao.addingOrderState(state);
            OrderState u = (OrderState) session.createSQLQuery("select  * from orderstates where orderstate=? and description=?").setParameter(1,state.getName()).setParameter(2, state.getDescription()).uniqueResult();
            Assert.assertNull(u);
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
    public void updatingOrderState() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            OrderState stold = new OrderState("state 1", "this is state #1");
            OrderState stnew = new OrderState("State 2", "this is orders state #2");
            dao.updatingOrderState(stold, stnew);
            OrderState state = (OrderState) session.createSQLQuery("select * from orderstates where orderstate=? and description=?").setParameter(1, stnew.getName()).setParameter(2, stnew.getDescription()).getSingleResult();
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
    public void removingOrderState() {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            OrderState os = new OrderState("State #1","this is order state");
            session.save(os);
            OrderState state = (OrderState) session.createSQLQuery("select * from orderstates where orderstsate=? and description=?").setParameter(1, os.getName()).setParameter(2, os.getDescription());
            if (state!=null)
            {
                dao.removingOrderState(state.getId());
                OrderState d = (OrderState) session.createSQLQuery("select * from orderstates where orderstsate=? and description=?").setParameter(1, os.getName()).setParameter(2, os.getDescription());
                Assert.assertNull(d);
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
            if (factory!=null && factory.isOpen())
                factory.close();
        }
    }
}