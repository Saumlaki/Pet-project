package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.view.main.MainView;

public class PriceDynamic extends Application{

    public static void main(String[] args) {

  //Helper.applicationContext = new ClassPathXmlApplicationContext("spring.cfg.xml");
      //  Helper.applicationContext = new AnnotationConfigApplicationContext("ru/saumlaki/price_dynamic");


//        ShopDAO shopDAO = context.getBean("shopDAOImpl", ShopDAO.class);
  //      shopDAO.add(new Shop("Шестерочка"));


        launch();
    }

    @Override
    public void start(Stage stage) {

        new MainView().showForm();
       // Helper.applicationContext.getBean("mainView", MainView.class).showForm();

     //   new MainView().showForm();
    }
}
