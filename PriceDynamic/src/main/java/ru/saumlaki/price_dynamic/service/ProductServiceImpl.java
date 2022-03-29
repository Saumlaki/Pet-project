package ru.saumlaki.price_dynamic.service;

import ru.saumlaki.price_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.price_dynamic.dao.interfaces.DAO;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.interfaces.PriceService;

import java.util.List;

public class ProductServiceImpl implements PriceService {

    DAO dao = DAOFactory.getDAO(Product.class);

    @Override
    public void add(Price object) {

    }

    @Override
    public void remove(Price object) {

    }

    @Override
    public Price getByID(int id) {
        return null;
    }

    @Override
    public List<Price> getAll() {
        return null;
    }
}