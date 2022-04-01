package ru.saumlaki.price_dynamic.controllers.list;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Product;

@Component("productListController")
public class ProductListController extends AbstractListController<Product> {

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Product.class);
    }

    @Override
    public void addObject() {
        System.out.println("addObject");
    }

    @Override
    public void changeObject(Product object) {
        System.out.println("changeObject");
    }

    @Override
    public void removeObject(Product object) {
        System.out.println("removeObject");
    }
}
