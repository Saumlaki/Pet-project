package ru.saumlaki.price_dynamic.controllers.selectionGroup;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.selectionGroup.abstracts.AbstractGroupSelectListController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.starters.element.ProductElementStarter;
import ru.saumlaki.price_dynamic.starters.group.ProductGroupStarter;

/**
 * Контроллер списка выбора групп товаров
 */
@Component
@FxmlView("ProductGroupSelectList.fxml")
public class ProductGroupSelectController extends AbstractGroupSelectListController<Product> {

    @Autowired
    ProductElementStarter elementStater;

    @Autowired
    ProductGroupStarter groupStater;

    @Autowired
    ProductServiceImpl service;

    @Autowired
    ObservableList<Product> dataList;

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Product.class);
    }

    @Override
    public void updateForm() {
        TreeItem<Product> root = new TreeItem<Product>(new Product(-1, null, true, "Все группы товаров"));
        list.setRoot(root);

        dataList.stream().filter(a -> a.getParent() == null && a.isGroup()).forEach(b -> {

            TreeItem<Product> elementGroup = new TreeItem<>(b);
            root.getChildren().add(elementGroup);

            createItems(elementGroup, b);
        });
    }

    @Override
    public void addObject() {
        elementStater.showForm(currentStage, new Product());
    }

    @Override
    public void addGroup() {
        groupStater.showForm(currentStage, new Product());
    }

    @Override
    public void changeObject(Product object) {
        groupStater.showForm(currentStage, object);
    }

    @Override
    public void removeObject(Product object) {
        service.remove(getCurrentObject());
    }

    protected void createItems(TreeItem root, Product group) {

        dataList.stream().filter(a -> a.getParent() != null && a.getParent().equals(group) && a.isGroup()).forEach(b -> {

            TreeItem<Product> elementGroup = new TreeItem<>(b);
            root.getChildren().add(elementGroup);

            createItems(elementGroup, b);
        });
    }

    @Override
    public void selectObject() {
        Product currentObject= getCurrentObject();
        if (currentObject.getId() != -1) {
            resultAction.onSelect(getCurrentObject());
            closeForm();
        }
    }
}
