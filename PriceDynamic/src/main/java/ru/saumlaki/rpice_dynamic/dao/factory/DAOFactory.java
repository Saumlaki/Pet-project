package ru.saumlaki.rpice_dynamic.dao.factory;

import ru.saumlaki.rpice_dynamic.dao.PriceDAOImpl;
import ru.saumlaki.rpice_dynamic.dao.ProductDAOImpl;
import ru.saumlaki.rpice_dynamic.dao.ShopDAOImpl;
import ru.saumlaki.rpice_dynamic.dao.interfaces.DAO;
import ru.saumlaki.rpice_dynamic.entity.Price;
import ru.saumlaki.rpice_dynamic.entity.Product;
import ru.saumlaki.rpice_dynamic.entity.Shop;
import ru.saumlaki.rpice_dynamic.service.interfaces.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализует фабрику для получения DAO по работе с БД
 */
public class DAOFactory {

    /**
     * Метод возвращает объект <code>DAOImpl</code> для заданного типа класса
     *
     * @return Service
     * @see Shop
     * @see Price
     * @see Product
     * @see Service
     */
    public static DAO getDAO(Class tempClass) {

        Map<Class, DAO> map = new HashMap<>();
        map.put(Shop.class, new ShopDAOImpl());
        map.put(Price.class, new PriceDAOImpl());
        map.put(Product.class, new ProductDAOImpl());

        return map.get(tempClass);
    }
}