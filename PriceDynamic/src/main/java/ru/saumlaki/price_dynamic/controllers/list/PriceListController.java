package ru.saumlaki.price_dynamic.controllers.list;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Price;

@Component("priceListController")
public class PriceListController extends AbstractListController<Price> {

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Price.class);
    }

    @Override
    public void addObject() {
        System.out.println("addObject");
    }

    @Override
    public void changeObject(Price object) {
        System.out.println("changeObject");
    }

    @Override
    public void removeObject(Price object) {
        System.out.println("removeObject");
    }
}
