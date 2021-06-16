package com.example.demo.config;

import com.example.demo.dao.DeliveryMethodDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableAutoConfiguration
@EnableTransactionManagement
@Primary
public class HibernateConfig {
    private  HibernateJpaSessionFactoryBean bean;
    private SessionFactory factory;



    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        bean = new HibernateJpaSessionFactoryBean();
        return bean;

    }
    @Bean
    public SessionFactory getSessionFactory(){
        return bean.getObject();
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(bean.getObject());
        return txManager;
    }

   /* public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);
        for (String beanname: ctx.getBeanDefinitionNames()){
            System.out.println(beanname);
        }
    }*/
}
