package ru.saumlaki.price_dynamic.controllers.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.view.element.ShopElement;

@Component("priceListController")
@FxmlView("PriceList.fxml")
public class PriceListController extends AbstractListController<Price> {

    @Autowired
    PriceServiceImpl service;


    @Override
    public void createTableColumn() {
        createTableColumnForClass(Price.class);
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
    public void changeObject(Price object) {
        //new ShopElement().showForm(currentStage, getCurrentObject());
    }

    @Override
    public void removeObject(Price object) {
        service.remove(getCurrentObject());
    }


}
