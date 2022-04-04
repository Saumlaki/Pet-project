package ru.saumlaki.price_dynamic.controllers.list;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.view.element.ShopElement;

@Component("shopListController")
public class ShopListController extends AbstractListController<Shop> {

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Shop.class);
    }

    @Override
    public void addObject() {

        new ShopElement().showForm(currentStage,new Shop());

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
