package ru.saumlaki.price_dynamic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Shop;

public class MainLauncher {
    public static void main(String[] args) {



        PriceDynamic.main(args);


        ApplicationContext context= new ClassPathXmlApplicationContext("spring.cfg.xml");

        ShopDAO shopDAO = context.getBean("shopDAOImpl", ShopDAO.class);
        shopDAO.add(new Shop("Шестерочка"));
    }
}
