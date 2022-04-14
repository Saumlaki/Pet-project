package ru.saumlaki.price_dynamic.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class ServiceFactory {
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    PriceServiceImpl priceService;

    /**
     * Метод возвращает объект <code>ServiceImpl</code> для заданного типа класса
     *
     * @return Service
     * @see Shop
     * @see Price
     * @see Product
     * @see Service
     */
    public Service getService(Class tempClass) {
        Map<Class, Service> map = new HashMap<>();
        map.put(Shop.class, shopService);
        map.put(Product.class, productService);
        map.put(Price.class, priceService);

        return map.get(tempClass);
    }
}