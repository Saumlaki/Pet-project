package ru.saumlaki.price_dynamic.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Shop;

import java.util.List;

@Component
public class ShopDAOImpl implements ShopDAO {

    @Override
    public void add(Shop object) {

        SessionFactory sessionFactory = (SessionFactory) Main.applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();

        session.save(object);
    }

    @Override
    public void remove(Shop object) {
    }

    @Override
    public Shop getByID(int id) {
        return null;
    }

    @Override
    public List<Shop> getAll() {

        SessionFactory sessionFactory = (SessionFactory) Main.applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Shop").getResultList();
    }
}