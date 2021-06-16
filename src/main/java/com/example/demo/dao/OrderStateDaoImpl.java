package com.example.demo.dao;

import com.example.demo.model.OrderState;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderStateDaoImpl implements OrderStateDao {

    private SessionFactory factory;
    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<OrderState> getAllOrderStates() {
        List<OrderState> os = new ArrayList();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            os = session.createSQLQuery("select * from orderstates").getResultList();
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
        return os;
    }

    @Override
    public OrderState geOrderStateById(int id) {
        OrderState os = new OrderState();
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            os = session.get(OrderState.class, id);
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
        return os;
    }

    @Override
    public void addingOrderState(OrderState os) {
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
    public void updatingOrderState(OrderState osold, OrderState osnew) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            OrderState state = (OrderState) session.createSQLQuery("select * from orderstates where orderstate=? and description=?").setParameter(1, osold.getName()).setParameter(2, osold.getDescription()).getSingleResult();
            if (state!=null){
                state.setName(osnew.getName());
                state.setDescription(osnew.getDescription());
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

    @Override
    public void removingOrderState(int id) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            OrderState os = session.get(OrderState.class, id);
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
