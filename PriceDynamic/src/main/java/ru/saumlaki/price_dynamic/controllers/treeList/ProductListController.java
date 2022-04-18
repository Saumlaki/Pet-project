package ru.saumlaki.price_dynamic.controllers.treeList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.controllers.treeList.abstracts.AbstractTreeListController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.starters.element.ProductElementStarter;
import ru.saumlaki.price_dynamic.starters.element.ProductGroupStarter;

@Component
@FxmlView("ProductList.fxml")
public class ProductListController extends AbstractTreeListController<Product> {

    @Autowired
    ProductElementStarter elementStarter;

    @Autowired
    ProductGroupStarter groupStarter;

    @Autowired
    ProductServiceImpl service;

    @Autowired
    ObservableList<Product> obsList;

    @FXML
    TreeTableView<Product> list;

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Product.class);
    }

    @Override
    public void updateForm() {

        TreeItem<Product> root = new TreeItem<Product>();
        list.setRoot(root);

        obsList.stream().filter(a->a.getParent()==null).forEach(a->{

            TreeItem<Product> elementGroup = new TreeItem<>(a);
            createItems(elementGroup, a);
            root.getChildren().add(elementGroup);

        });
    }

    protected void createItems(TreeItem root, Product group) {
        obsList.stream().filter(a->a.getParent().equals(group)).forEach(a->{

            TreeItem<Product> elementGroup = new TreeItem<>(a);
            createItems(elementGroup, a);
            root.getChildren().add(elementGroup);
        });
    }

    @Override
    public void addObject() {
        elementStarter.showForm(currentStage, new Product());
    }

    @FXML
    public void addGroupOnAction() {
        groupStarter.showForm(currentStage, new Product());
    }

    @FXML
    public void addGroupOnActionCM() {
        groupStarter.showForm(currentStage, new Product());
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
