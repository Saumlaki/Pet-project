package ru.saumlaki.price_dynamic.service;

import org.springframework.stereotype.Service;
import ru.saumlaki.price_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.price_dynamic.dao.interfaces.DAO;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.interfaces.PriceService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    DAO dao = DAOFactory.getDAO(Price.class);

    @Override
    public void add(Price object) {
        dao.add(object);
    }

    @Override
    public void remove(Price object) {
        dao.remove(object);
    }

    @Override
    public Price getByID(int id) {
        return (Price) dao.getByID(id);
    }

    @Override
    public List<Price> getAll() {

        List<Price> list = new ArrayList<>();
        return list;
        // return dao.getAll();
    }
}