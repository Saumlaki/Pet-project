package ru.saumlaki.price_dynamic.controllers.listGroup;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.listGroup.abstracts.AbstractTreeListController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.starters.element.ProductElementStarter;
import ru.saumlaki.price_dynamic.starters.group.ProductGroupStarter;

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

    @Override
    public void createTableColumn() {
        createTableColumnForClass(Product.class);
    }

    @Override
    public void updateForm() {

        TreeItem<Product> root = new TreeItem<Product>(new Product(-1,null,true,"Все товары"));
        list.setRoot(root);

        obsList.stream().filter(a->a.getParent()==null).forEach(b->{

            TreeItem<Product> elementGroup = new TreeItem<>(b);
            root.getChildren().add(elementGroup);

            createItems(elementGroup, b);
        });
    }

    protected void createItems(TreeItem root, Product group) {

        obsList.stream().filter(a->a.getParent()!=null&&a.getParent().equals(group)).forEach(b->{

            TreeItem<Product> elementGroup = new TreeItem<>(b);
            root.getChildren().add(elementGroup);

            createItems(elementGroup, b);
        });
    }

    @Override
    public void addObject() {
        elementStarter.showForm(currentStage, new Product());
    }

    @Override
    public void addGroup() {
        groupStarter.showForm(currentStage, new Product());
    }

    @Override
    public void changeObject(Product object) {
        if(object.getId()==-1) return;//Корневой элемент

        if(object.isGroup())
            groupStarter.showForm(currentStage, object);
        else
            elementStarter.showForm(currentStage, object);
    }

    @Override
    public void removeObject(Product object) {
        service.remove(getCurrentObject());
    }


}
