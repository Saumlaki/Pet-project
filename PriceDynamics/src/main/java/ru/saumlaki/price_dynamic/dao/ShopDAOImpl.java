package ru.saumlaki.price_dynamic.dao;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Shop;

import java.util.List;

@Component
public class ShopDAOImpl implements ShopDAO {

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
    }
}