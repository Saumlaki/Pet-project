package ru.saumlaki.price_dynamic.controllers.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.view.element.PriceElementStarter;

@Component
@FxmlView("PriceList.fxml")
public class PriceListController extends AbstractListController<Price> {

    @Autowired
    PriceElementStarter elementStarter;

    @Autowired
    PriceServiceImpl service;

    @Autowired
    ObservableList<Price> prices;

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Price.class);
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
        elementStarter.showForm(currentStage, new Price());
    }

    @Override
    public void changeObject(Price object) {
        elementStarter.showForm(currentStage, getCurrentObject());
    }

    @Override
    public void removeObject(Price object) {
        service.remove(getCurrentObject());
    }


}
