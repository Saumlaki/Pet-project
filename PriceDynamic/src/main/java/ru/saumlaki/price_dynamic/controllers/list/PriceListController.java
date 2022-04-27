package ru.saumlaki.price_dynamic.controllers.list;

import javafx.collections.ObservableList;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.starters.element.PriceElementStarter;

@Component
@FxmlView("PriceList.fxml")
public class PriceListController extends AbstractListController<Price> {

    @Autowired
    PriceElementStarter elementStarter;

    @Autowired
    PriceServiceImpl service;

    @Autowired
    ObservableList<Price> obsList;

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Price.class);
    }

    @Override
    public void updateForm() {
        list.setItems(obsList);
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
