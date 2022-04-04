package ru.saumlaki.price_dynamic.controllers.list;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.view.element.ShopElement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("shopListController")
@FxmlView("ShopList.fxml")
//public class ShopListController extends AbstractListController<Shop> {
public class ShopListController  {
//    @Override
//    public void createTableColumn() {
//        createTableColumnForClass(Shop.class);
//    }
//
//    @Override
//    public void addObject() {
//
//        new ShopElement().showForm(currentStage,new Shop());
//    }
//
//    @Override
//    public void changeObject(Shop object) {
//        System.out.println("changeObject");
//    }
//
//    @Override
//    public void removeObject(Shop object) {
//        System.out.println("removeObject");
//    }









    @FXML
    private Button addButton;

    @FXML
    private Button changeButton;

    @FXML
    private Button removeButton;

    @FXML
    private MenuItem addButtonCM;

    @FXML
    private MenuItem changeButtonCM;

    @FXML
    private MenuItem removeButtonCM;

    @Getter
    @FXML
    private TableView<Shop> list;

    //*****
    @FXML
    public void initialize(){

        addButton.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        changeButton.setGraphic(new ImageView(Helper.getPropertyForName("ChangeElementIcon")));
        removeButton.setGraphic(new ImageView(Helper.getPropertyForName("RemoveElementIcon")));

        addButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        changeButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("ChangeElementIcon")));
        removeButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("RemoveElementIcon")));

       // createTableColumn();
    }

    //*****

    @FXML
    void addOnAction(ActionEvent event) {

      //  addObject();
    }

    @FXML
    void changeOnAction(ActionEvent event) {

      //  changeObject(getCurrentObject());
    }

    @FXML
    void removeOnAction(ActionEvent event) {

      //  removeObject(getCurrentObject());
    }

    @FXML
    void addOnActionCM(ActionEvent event) {

    //    addObject();
    }

    @FXML
    void changeOnActionCM(ActionEvent event) {

      //  changeObject(getCurrentObject());
    }

    @FXML
    void removeOnActionCM(ActionEvent event) {

       // removeObject(getCurrentObject());
    }

//    //*****
//
//    private T getCurrentObject() {
//
//        return list.getSelectionModel().getSelectedItem();
//    }
//
//    public abstract void addObject();
//
//    public abstract void changeObject(T object);
//
//    public abstract void removeObject(T object);
//
//    //*****
//
//    public abstract void createTableColumn();
//
//    public void createTableColumnForClass(Class objectClass) {
//
//        @AllArgsConstructor
//        class IndexedColumn implements Comparable<IndexedColumn>{
//            int order;
//            String name;
//
//            @Override
//            public int compareTo(IndexedColumn o) {
//                return order - o.order;
//            }
//        }
//        List<IndexedColumn> columnList = new ArrayList<>();
//
//        Field fields[] = objectClass.getDeclaredFields();
//
//
//
//        for (Field field : fields) {
//
//            if (field.isAnnotationPresent(TableViewColumn.class)) {
//                columnList.add(new IndexedColumn(field.getAnnotation(TableViewColumn.class).order(), field.getAnnotation(TableViewColumn.class).name()));
//            }
//        }
//
//        Collections.sort(columnList);
//
//        columnList.stream().forEach(a->{
//            TableColumn<T, String> column = new TableColumn<>(a.name);
//            list.getColumns().add(column);});
//    }
}
