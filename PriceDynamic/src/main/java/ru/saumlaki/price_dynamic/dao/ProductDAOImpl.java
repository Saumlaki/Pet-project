package ru.saumlaki.price_dynamic.dao;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.saumlaki.price_dynamic.dao.interfaces.ProductDAO;
import ru.saumlaki.price_dynamic.entity.Product;

import java.util.List;

@NoArgsConstructor
@Repository
public class ProductDAOImpl implements ProductDAO {

    private SessionFactory sessionFactory;

    //Автоматическое внедрение sessionFactory
   // @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Product object) {
        currentSession().save(object);
    }

    @Override
    public void update(Product object) {
        currentSession().update(object);
    }

    @Override
    public void remove(Product object) {
        currentSession().remove(object);
    }

    @Override
    public Product getByID(int id) {
        return currentSession().get(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        return currentSession().createQuery("SELECT a FROM product a", Product.class).getResultList();
    }
}
