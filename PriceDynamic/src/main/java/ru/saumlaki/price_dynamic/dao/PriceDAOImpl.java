package ru.saumlaki.price_dynamic.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.saumlaki.price_dynamic.dao.interfaces.PriceDAO;
import ru.saumlaki.price_dynamic.entity.Price;

import java.util.List;

@Repository
@Transactional
public class PriceDAOImpl implements PriceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Price object) {
        currentSession().save(object);
    }

    @Override
    public void update(Price object) {
        currentSession().update(object);
    }

    @Override
    public void remove(Price object) {
        currentSession().remove(object);
    }

    @Override
    public Price getByID(int id) {
        return currentSession().get(Price.class, id);
    }

    @Override
    public List<Price> getAll() {
        return currentSession().createQuery("SELECT a FROM price a", Price.class).getResultList();
    }
}
