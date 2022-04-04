package ru.saumlaki.price_dynamic.service;

import ru.saumlaki.price_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.price_dynamic.dao.interfaces.DAO;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.interfaces.PriceService;
import ru.saumlaki.price_dynamic.service.interfaces.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    DAO dao = DAOFactory.getDAO(Product.class);

    @Override
    public void add(Product object) {
        dao.add(object);
    }

    @Override
    public void remove(Product object) {
        dao.remove(object);
    }

    @Override
    public Product getByID(int id) {
        return (Product) dao.getByID(id);
    }

    @Override
    public List<Product> getAll() {
        return dao.getAll();
    }
}