package ru.saumlaki.price_dynamic.controllers.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.view.element.ProductElementStarter;

@Component
@FxmlView("ProductList.fxml")
public class ProductListController extends AbstractListController<Product> {

    @Autowired
    ProductElementStarter elementStarter;

    @Autowired
    ProductServiceImpl service;

    @Autowired
    ObservableList<Product> prices;

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Product.class);
    }

    @Override
    public void updateForm() {
        if (list != null) {
            list.getItems().clear();
            list.setItems(prices);
        }
    }

    @Override
    public void addObject() {
        elementStarter.showForm(currentStage, new Product());
    }

    @Override
    public void changeObject(Product object) {
        elementStarter.showForm(currentStage, object);
    }

    @Override
    public void removeObject(Product object) {
        service.remove(getCurrentObject());
    }
}
