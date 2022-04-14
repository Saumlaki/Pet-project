package ru.saumlaki.price_dynamic.controllers.selection;

import javafx.collections.ObservableList;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.selection.abstracts.AbstractSelectListController;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.starters.element.ShopElementStarter;

@Component
@FxmlView("ShopSelectList.fxml")
public class ShopSelectController extends AbstractSelectListController<Shop> {

    @Autowired
    ShopElementStarter elementStarter;

    @Autowired
    ShopServiceImpl service;

    @Autowired
    ObservableList<Shop> obsList;

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Shop.class);
    }

    @Override
    public void updateForm() {
        list.setItems(obsList);
    }

    @Override
    public void addObject() {
        elementStarter.showForm(currentStage, new Shop());
    }

    @Override
    public void changeObject(Shop object) {
        elementStarter.showForm(currentStage, object);
    }

    @Override
    public void removeObject(Shop object) {
        service.remove(getCurrentObject());
    }
}