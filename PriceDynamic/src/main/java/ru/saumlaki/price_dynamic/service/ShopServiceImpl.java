package ru.saumlaki.price_dynamic.service;

import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saumlaki.price_dynamic.dao.ShopDAOImpl;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;
import ru.saumlaki.price_dynamic.supporting.AlertMessage;

import java.sql.SQLDataException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDAOImpl dao;

    @Autowired
    ObservableList<Shop> list;

    @Override
    public void add(Shop object) throws SQLDataException {

        List<Shop> listTemp = getByName(object.getDescription());

        if (object.getId() == 0) {
            if (listTemp.size() == 0) {
                dao.add(object);
                updateList();
            } else {
                updateList();
                AlertMessage.showError("Ошибка сохранения", "Элемент с таким наименованием уже есть в базе");
                throw new SQLDataException("Элемент с таким наименованием уже есть в базе");
            }
        } else {
            if (listTemp.stream().filter(a->a.getId()!=object.getId()).count()!=0) {
                updateList();
                AlertMessage.showError("Ошибка сохранения", "Элемент с таким наименованием, но другим ID уже есть в базе");
                throw new SQLDataException("Элемент с таким наименованием уже есть в базе");
            } else {
                updateList();
                dao.update(object);
                updateList();
            }
        }
    }

    @Override
    public void remove(Shop object) {
        dao.remove(object);
    }

    @Override
    public Shop getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<Shop> getAll() {
        return dao.getAll();
    }

    public List<Shop> getByName(String name) {
        return getAll().stream().filter(a -> a.getDescription().equals(name)).collect(Collectors.toList());
    }

    @Override
    public void updateList() {
        list.clear();
        list.addAll(getAll());
    }
}
