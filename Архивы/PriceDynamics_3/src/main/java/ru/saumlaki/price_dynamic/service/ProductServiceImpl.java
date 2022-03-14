package ru.saumlaki.price_dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.ProductDAO;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.interfaces.ProductService;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO dao;

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

        return dao.getByID(id);
    }

    @Override
    public List<Product> getAll() {

        return dao.getAll();
    }
}

