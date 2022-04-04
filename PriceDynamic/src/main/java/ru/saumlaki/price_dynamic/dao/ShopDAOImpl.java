package ru.saumlaki.price_dynamic.dao;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Shop;

import java.util.List;

@NoArgsConstructor
@Repository
@Transactional
public class ShopDAOImpl implements ShopDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Автоматическое внедрение sessionFactory
    @Autowired
    public ShopDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Shop object) {
        currentSession().save(object);
    }

    @Override
    public void update(Shop object) {
        currentSession().update(object);
    }

    @Override
    public void remove(Shop object) {
        currentSession().remove(object);
    }

    @Override
    public Shop getByID(int id) {
        return currentSession().get(Shop.class, id);
    }

    @Override
    public List<Shop> getAll() {
        return currentSession().createQuery("SELECT a FROM shop a", Shop.class).getResultList();
    }

}
