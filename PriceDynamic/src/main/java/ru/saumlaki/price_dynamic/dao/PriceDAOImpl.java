package ru.saumlaki.price_dynamic.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.saumlaki.price_dynamic.dao.interfaces.PriceDAO;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;

import java.util.Date;
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

    public Double getPriceForDate(Shop shop, Product product, Date date) {

        String query = "SELECT price, product_id, shop_id FROM price " +
                "WHERE (shop_id, product_id, date) = (SELECT shop_id, product_id, max(date) FROM price " +
                "WHERE" +
                "  product_id = :productID " +
                "  AND shop_id = :shopId " +
                "  AND date<= :date " +
                "ORDER BY  shop_id, product_id)";

        List result = currentSession().createSQLQuery(query)
                .setParameter("productID", product.getId())
                .setParameter("shopId", shop.getId())
                .setParameter("date", date).list();

        if (result.size() > 0) {
            Object[] obj= (Object[]) result.get(0);

            return (Integer)(obj[0])*1.0;
        } else

            return 0.0;

    }
}
