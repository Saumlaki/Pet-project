package ru.saumlaki.pricedynamics.config;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**Конфигурационный класс Spring. В данном классе определяем необходимые для работы бины
 * */

//Указываем что данный класс является конфигурационным. Все bean будут описаны в данном классе через аннотацию @Bean
@Configuration

//Активирует возможности Spring бесшовной транзакции через @Transactional
@EnableTransactionManagement

//Указывает на файл с настройками
@PropertySource("classpath:app.properties")

@ComponentScan("ru.saumlaki.pricedynamics")
public class DataConfig {

    //Аннотаций автоматического внедрения. Сюда Spring загрузит свойства из @PropertySource
    @Autowired
    Environment environment;

    /**
     * Подключение к базе данных
     * @see "http://hibernate-refdoc.3141.ru/ch3.Configuration"
     */
    //Данная аннотация помещает bean hbConfiguration в Spring Bean Scope. Данный бин используется только внутри класса
    @Bean

    protected org.hibernate.cfg.Configuration hbConfiguration() {

        //Создаем настройку Hibernate
        org.hibernate.cfg.Configuration hbConfiguration = new org.hibernate.cfg.Configuration();

        //Устанавливаем настройки подключения к БД из файла свойств
        hbConfiguration.setProperty("hibernate.connection.url", environment.getProperty("connection.url"));
        hbConfiguration.setProperty("hibernate.connection.driver_class", environment.getProperty("connection.driver_class"));
        hbConfiguration.setProperty("hibernate.current_session_context_class", environment.getProperty("current_session_context_class"));
        hbConfiguration.setProperty("hibernate.dialect", environment.getProperty("dialect"));
        hbConfiguration.setProperty("hibernate.show_sql", environment.getProperty("show_sql"));
        hbConfiguration.setProperty("hibernate.connection.username", environment.getProperty("connection.username"));
        hbConfiguration.setProperty("hibernate.connection.password", environment.getProperty("connection.password"));
        hbConfiguration.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

        //указываем пакет для сканирования классов
        hbConfiguration.setProperty("entitymanager.packages.to.scan", environment.getProperty("hibernate.hbm2ddl.auto"));

        return hbConfiguration;
    }

    /**
     * Создание SessionFactory
     */
    //Данная аннотация помещает bean SessionFactory в Spring Bean Scope
    @Bean
    protected SessionFactory sessionFactory() {

        return hbConfiguration().buildSessionFactory();
    }


    /**
     * Создание session для работы с БД
     */
    //Данная аннотация помещает bean session в Spring Bean Scope
    @Bean
    protected Session session() {

        return sessionFactory().getCurrentSession();
    }

}
