package ru.saumlaki.price_dynamic.controllers.list;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Shop;

@Component("shopListController")
public class ShopListController extends AbstractListController<Shop> {

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Shop.class);
    }

    @Override
    public void addObject() {
        System.out.println("addObject");
    }

    @Override
    public void changeObject(Shop object) {
        System.out.println("changeObject");
    }

    @Override
    public void removeObject(Shop object) {
        System.out.println("removeObject");
    }
}
