package ru.saumlaki.price_dynamic.dao;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.ProductDAO;
import ru.saumlaki.price_dynamic.entity.Product;

import java.util.List;

@Component
public class ProductDAOImpl implements ProductDAO {

    @Override
    public void add(Product object) {
    }

    @Override
    public void remove(Product object) {
    }

    @Override
    public Product getByID(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}