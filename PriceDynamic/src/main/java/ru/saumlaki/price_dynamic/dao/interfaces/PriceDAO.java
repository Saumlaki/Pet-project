package ru.saumlaki.price_dynamic.dao.interfaces;

import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;

import java.util.Date;

public interface PriceDAO extends DAO<Price> {

    Double getPriceForDate(Shop shop, Product product, Date date);
}
