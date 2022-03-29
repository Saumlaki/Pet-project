package ru.saumlaki.rpice_dynamic.service;

import ru.saumlaki.rpice_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.rpice_dynamic.dao.interfaces.DAO;
import ru.saumlaki.rpice_dynamic.entity.Product;
import ru.saumlaki.rpice_dynamic.entity.Shop;
import ru.saumlaki.rpice_dynamic.service.interfaces.ShopService;

import java.util.List;

public class ProductServiceImpl implements ShopService {
    DAO dao = DAOFactory.getDAO(Product.class);

    @Override
    public void add(Shop object) {

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
        return null;
    }}