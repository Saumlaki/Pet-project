package ru.saumlaki.price_dynamic.controllers.main;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.supporting.SimpleCalendar;
import ru.saumlaki.price_dynamic.starters.about.AboutStarter;
import ru.saumlaki.price_dynamic.starters.element.PriceElementStarter;
import ru.saumlaki.price_dynamic.starters.element.ProductElementStarter;
import ru.saumlaki.price_dynamic.starters.element.ShopElementStarter;
import ru.saumlaki.price_dynamic.starters.list.PriceListStarter;
import ru.saumlaki.price_dynamic.starters.list.ProductListStarter;
import ru.saumlaki.price_dynamic.starters.list.ShopListStarter;

import java.time.LocalDate;
import java.util.Calendar;


@Component
@FxmlView("MainView.fxml")
public class MainController extends AbstractController {

    @Autowired
    ShopListStarter shopListStarter;

    @Autowired
    ProductListStarter productListStarter;

    @Autowired
    PriceListStarter priceListStarter;

    @Autowired
    ShopElementStarter shopElementStarter;

    @Autowired
    ProductElementStarter productElementStarter;

    @Autowired
    PriceElementStarter priceElementStarter;

    @Autowired
    AboutStarter aboutStarter;

    @Autowired
    ShopServiceImpl shopService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    PriceServiceImpl priceService;


    @FXML
    private TreeTableView<PriceDataElement> priceData;

    @FXML
    private Button shopAdd;

    @FXML
    private Button productAdd;

    @FXML
    private Button priceAdd;

    //*****

    @FXML
    private Menu setting;

    @FXML
    private MenuItem shopList;

    @FXML
    private MenuItem productList;

    @FXML
    private MenuItem priceList;

    @FXML
    private MenuItem about;

    //*****

    @FXML
    public void initialize() {

        shopAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        priceAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        productAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        setting.setGraphic(new ImageView(Helper.getPropertyForName("SettingIcon")));
        about.setGraphic(new ImageView(Helper.getPropertyForName("AboutIcon")));

        shopList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        productList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        priceList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));




        TreeTableColumn<PriceDataElement, String> columnShop = new TreeTableColumn("Магазин/Товар");

        TreeTableColumn<PriceDataElement, Number> columnPrice = new TreeTableColumn("Цена");
        TreeTableColumn<PriceDataElement, Number> columnPrice1 = new TreeTableColumn("Начало года");
        TreeTableColumn<PriceDataElement, Number> columnPrice2 = new TreeTableColumn("Предыдущий месяц");
        TreeTableColumn<PriceDataElement, Number> columnPrice3 = new TreeTableColumn("Текущая");
        columnPrice.getColumns().addAll(columnPrice1, columnPrice2, columnPrice3);

        TreeTableColumn<PriceDataElement, Number> columnDynamic = new TreeTableColumn("Динамика");
        TreeTableColumn<PriceDataElement, Number> columnDynamic1 = new TreeTableColumn("С начала года");
        TreeTableColumn<PriceDataElement, Number> columnDynamic2 = new TreeTableColumn("С прошлого месяца");
        columnDynamic.getColumns().addAll(columnDynamic1, columnDynamic2);

        priceData.getColumns().addAll(columnShop, columnPrice, columnDynamic);


        columnShop.setCellValueFactory(a -> a.getValue().getValue().getDescription());
        columnPrice1.setCellValueFactory(a -> a.getValue().getValue().getPriceToBeginYear());
        columnPrice2.setCellValueFactory(a -> a.getValue().getValue().getPriceToBeginMonth());
        columnPrice3.setCellValueFactory(a -> a.getValue().getValue().getPriceActual());
        columnDynamic1.setCellValueFactory(a -> a.getValue().getValue().getDeviationToBeginYear());
        columnDynamic2.setCellValueFactory(a -> a.getValue().getValue().getDeviationToLastMonth());

        createItems();

    }


    public void createItems() {
        TreeItem<PriceDataElement> rootItem = new TreeItem<>(new PriceDataElement());
        priceData.setRoot(rootItem);

        for (Shop shop : shopService.getAll()) {

            TreeItem<PriceDataElement> shopItem = new TreeItem<>(new PriceDataElement(shop));
            for (Product product : productService.getAll()) {


                shopItem.getChildren().add(new TreeItem<>(new PriceDataElement(shop, product)));
            }

            rootItem.getChildren().add(shopItem);
        }
    }









    //*****

    @FXML
    void shopAddOnAction(ActionEvent event) {
        shopElementStarter.showForm(currentStage, new Shop());
    }

    @FXML
    void shopListOnAction(ActionEvent event) {
        shopListStarter.showForm(currentStage);
    }

    @FXML
    void productAddOnAction(ActionEvent event) {
        productElementStarter.showForm(currentStage, new Product());
    }

    @FXML
    void productListOnAction(ActionEvent event) {
        productListStarter.showForm(currentStage);
    }

    @FXML
    void priceAddOnAction(ActionEvent event) {
        priceElementStarter.showForm(currentStage, new Price());
    }

    @FXML
    void priceListOnAction(ActionEvent event) {
        priceListStarter.showForm(currentStage);
    }

    @FXML
    void aboutOnAction(ActionEvent event) {
        aboutStarter.showForm(currentStage);
    }

    @FXML
    void exitOnAction(ActionEvent event) {
        currentStage.close();
    }


    class PriceDataElement {

        @Getter
        @Setter
        SimpleStringProperty description;

        @Getter
        @Setter
        Product product;

        @Getter
        @Setter
        Shop shop;

        @Getter
        SimpleDoubleProperty priceToBeginYear;

        @Getter
        SimpleDoubleProperty priceToBeginMonth;

        @Getter
        SimpleDoubleProperty priceActual;

        @Getter
        SimpleDoubleProperty deviationToBeginYear;

        @Getter
        SimpleDoubleProperty deviationToLastMonth;

        public PriceDataElement() {
            this.description = new SimpleStringProperty("Список товаров");
        }

        public PriceDataElement(Shop shop) {
            this.shop = shop;
            this.description =  new SimpleStringProperty(shop.getDescription());
        }

        public PriceDataElement(Shop shop, Product product) {
            this.product = product;
            this.shop = shop;
            this.description =  new SimpleStringProperty(product.getDescription());

            fillPriceToBeginYear();
            fillPriceToBeginMonth();
            fillPriceActual();
            fillDeviationToBeginYear();
            fillDeviationToLastMonth();
        }

        protected void fillPriceToBeginYear() {
            Calendar calendar = SimpleCalendar.getBeginningCurrentYear();

            LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1, 1);
            priceToBeginYear = new SimpleDoubleProperty(priceService.getPriceForDate(shop, product, localDate));
        }

        protected void fillPriceToBeginMonth() {
            Calendar calendar = SimpleCalendar.getBeginningCurrentMonth();
            LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), 1);
            priceToBeginMonth = new SimpleDoubleProperty(priceService.getPriceForDate(shop, product, localDate));
        }
        protected void fillPriceActual() {
            Calendar calendar = SimpleCalendar.getBeginningCurrentMonth();
            LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1, 1);
            priceActual = new SimpleDoubleProperty(priceService.getPriceForDate(shop, product,localDate));
        }
        protected void fillDeviationToBeginYear() {


        }
        protected void fillDeviationToLastMonth() {


        }

    }
}
