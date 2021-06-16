package com.example.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;


@SpringBootTest
class DemoApplicationTests {
	@Inject
    private ApplicationContext context;
    private SessionFactory factory;

    @Autowired
	public void setContext() {
		this.context = new AnnotationConfigApplicationContext();

	}
	@Autowired
	public void setFactory() {
		this.factory = context.getBean(SessionFactory.class);
	}

	@Test
	void contextLoads() {
	}
	@Test
	public void testConnection(){

		try {

			Session session = factory.openSession();
			if (session!=null){
				System.out.println("not null");
			}
			else {
				System.out.println("null");
			}
			session.close();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}
