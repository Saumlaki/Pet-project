package ru.saumlaki.price_dynamic.service;

import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saumlaki.price_dynamic.dao.ProductDAOImpl;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.interfaces.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAOImpl dao;

    @Autowired
    PriceServiceImpl priceService;

    @Autowired
    ObservableList<Product> list;

    @Override
    public void add(Product object) {
        if (object.getId() == 0) dao.add(object);
        else dao.update(object);
        updateList();
    }

    @Override
    public void remove(Product object) {
        priceService.getAll().stream().filter(a->a.getProduct().equals(object)).forEach(a->priceService.remove(a));
        dao.remove(object);
        updateList();
        priceService.updateList();
    }

    @Override
    public Product getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<Product> getAll() {
        return dao.getAll();
    }

    @Override
    public void updateList() {
        list.clear();
        list.addAll(getAll());
    }
}