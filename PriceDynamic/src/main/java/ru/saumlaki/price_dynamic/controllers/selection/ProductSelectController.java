package ru.saumlaki.price_dynamic.controllers.selection;

import javafx.collections.ObservableList;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.selection.abstracts.AbstractSelectListController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.starters.element.ProductElementStarter;

@Component
@FxmlView("ProductSelectList.fxml")
public class ProductSelectController extends AbstractSelectListController<Product> {

    @Autowired
    ProductElementStarter elementStarter;

    @Autowired
    ProductServiceImpl service;

    @Autowired
    ObservableList<Product> obsList;

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Product.class);
    }

    @Override
    public void updateForm() {
        list.setItems(obsList);
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
