package ru.saumlaki.price_dynamic.controllers.list;

import javafx.collections.FXCollections;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.view.element.ShopElement;

@Component("productListController")
@FxmlView("ProductList.fxml")
public class ProductListController extends AbstractListController<Product> {

    @Autowired
    ProductServiceImpl service;

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Product.class);
    }

    @Override
    public void updateForm() {

        list.getItems().clear();
        list.setItems(FXCollections.observableList(service.getAll()));
    }

    @Override
    public void addObject() {
        System.out.println("addObject");
    }

    @Override
    public void changeObject(Product object) {
        //new ShopElement().showForm(currentStage, getCurrentObject());
    }

    @Override
    public void removeObject(Product object) {
        service.remove(getCurrentObject());
    }


}
