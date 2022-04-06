package ru.saumlaki.price_dynamic.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saumlaki.price_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.price_dynamic.dao.interfaces.DAO;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    DAO dao = DAOFactory.getDAO(Shop.class);

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
        return (Shop) dao.getByID(id);
    }

    @Override
    public List<Shop> getAll() {

        List<Shop> list = new ArrayList<>();
        list.add(new Shop(1,"Пятерочка"));
        list.add(new Shop(2,"Перекресток"));
        list.add(new Shop(3,"Дикси"));
        list.add(new Shop(4, "Магнит"));

        return list;
        //return dao.getAll();
    }
}
