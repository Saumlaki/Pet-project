package ru.saumlaki.price_dynamic.service.factory;

import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.PriceDynamic;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.service.interfaces.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализует фабрику для получения сервисов по работе с БД
 */
public class ServiceFactory {

    /**
     * Метод возвращает объект <code>ServiceImpl</code> для заданного типа класса
     *
     * @return Service
     * @see Shop
     * @see Price
     * @see Product
     * @see Service
     */
    public static Service getService(Class tempClass) {

        Map<Class, Service> map = new HashMap<>();
        map.put(Shop.class, Main.applicationContext.getBean("shopServiceImpl", ShopServiceImpl.class));
        map.put(Price.class, Main.applicationContext.getBean("priceServiceImpl", PriceServiceImpl.class));
        map.put(Product.class, Main.applicationContext.getBean("productServiceImpl", ProductServiceImpl.class));

        return map.get(tempClass);
    }
}