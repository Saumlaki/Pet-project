package ru.saumlaki.price_dynamic.service;

import org.springframework.stereotype.Service;
import ru.saumlaki.price_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.price_dynamic.dao.interfaces.DAO;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    DAO dao = DAOFactory.getDAO(Product.class);

    @Override
    public void add(Product object) {
        dao.add(object);
    }

    @Override
    public void remove(Product object) {
        dao.remove(object);
    }

    @Override
    public Product getByID(int id) {
        return (Product) dao.getByID(id);
    }

    @Override
    public List<Product> getAll() {

        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"Товар 1"));
        list.add(new Product(2,"Товар 2"));
        list.add(new Product(3,"Товар 3"));
        list.add(new Product(4, "Товар 4"));

        return  list;
        //  return dao.getAll();
    }
}