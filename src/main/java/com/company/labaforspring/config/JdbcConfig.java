package com.company.labaforspring.config;


import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.company.labaforspring.dao")
@ComponentScan("com.company.labaforspring")
@PropertySource("classpath:db.properties")
public class JdbcConfig {
    @Resource
    private Environment environment;
    @Bean
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean factory(){
        Properties properties = new Properties();
        try{
            InputStream fis = getClass().getClassLoader().getResourceAsStream("classpath:db.properties");
            properties.load(fis);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        LocalContainerEntityManagerFactoryBean f = new LocalContainerEntityManagerFactoryBean();
        f.setDataSource(getDataSource());
        f.setPackagesToScan(environment.getActiveProfiles());
        f.setPackagesToScan("com.company.labaforspring");
        f.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        f.setJpaProperties(properties);
        return f;
    }
    @Bean
    public PlatformTransactionManager manager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setDataSource(getDataSource());
        manager.setJpaDialect(new HibernateJpaDialect());
        return manager;
    }

}
