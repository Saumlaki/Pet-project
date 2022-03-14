package ru.saumlaki.price_dynamic.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Конфигурационный класс Spring. В данном классе определяем необходимые для работы бины
 */

//Указываем что данный класс является конфигурационным. Все bean будут описаны в данном классе через аннотацию @Bean
@Configuration

//Активирует возможности Spring бесшовной транзакции через @Transactional
@EnableTransactionManagement

//Указывает на файл с настройками
@PropertySource("classpath:app.properties")

//Указываем пакет в котором ищем компоненты
@ComponentScan("ru.saumlaki.price_dynamic")
public class DataConfig {

    //Аннотаций автоматического внедрения. Сюда Spring загрузит свойства из @PropertySource
    @Autowired
    protected Environment environment;

    @Bean
    protected ComboPooledDataSource dataSource() {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        try {
            comboPooledDataSource.setDriverClass(environment.getProperty("driver_class"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        comboPooledDataSource.setJdbcUrl(environment.getProperty("jdbcUrl"));

        return comboPooledDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan(environment.getProperty("packagesToScan"));
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;
    }

    @Bean
    protected HibernateTransactionManager transactionManager() {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

}