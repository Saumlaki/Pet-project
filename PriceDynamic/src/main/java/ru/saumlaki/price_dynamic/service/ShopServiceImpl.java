package ru.saumlaki.price_dynamic.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saumlaki.price_dynamic.dao.ShopDAOImpl;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDAOImpl dao;

    @Override
    public void add(Shop object) {
        dao.add(object);
    }

    @Override
    public void remove(Shop object) {
        dao.remove(object);
    }

    @Override
    public Shop getByID(int id) {
        return  dao.getByID(id);
    }

    @Override
    public List<Shop> getAll() {
        return dao.getAll();
    }
}
