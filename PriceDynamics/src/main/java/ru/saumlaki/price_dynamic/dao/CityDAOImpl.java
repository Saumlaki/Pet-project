package ru.saumlaki.price_dynamic.dao;import org.springframework.stereotype.Component;import ru.saumlaki.price_dynamic.dao.interfaces.CityDAO;import ru.saumlaki.price_dynamic.entity.City;import java.util.List;@Componentpublic class CityDAOImpl implements CityDAO {    @Override    public void add(City object) {    }    @Override    public void remove(City object) {    }    @Override    public City getByID(int id) {        return null;    }    @Override    public List<City> getAll() {        return null;    }}