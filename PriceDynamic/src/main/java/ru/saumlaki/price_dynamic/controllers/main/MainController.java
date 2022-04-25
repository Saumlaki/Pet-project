package ru.saumlaki.price_dynamic.controllers.main;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
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
import ru.saumlaki.price_dynamic.supporting.CLoseProgramMessage;
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

    //НЕОБХОДИМЫЕ ДЛЯ РАБОТЫ БИНЫ
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

    @Autowired
    ObservableList<Product> productObsList;

    //ЭЛЕМЕНТЫ ФОРМЫ
    @FXML
    private TreeTableView<PriceDataElement> priceData;

    @FXML
    private Button shopAdd;
    @FXML
    private Button productAdd;
    @FXML
    private Button priceAdd;

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

    //****ИНИЦИАЛИЗАЦИЯ ФОРМЫ
    @FXML
    public void initialize() {
        setIcons();
        createTable();
    }

    protected void setIcons() {
        shopAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        priceAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        productAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));

        shopList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        productList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        priceList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        setting.setGraphic(new ImageView(Helper.getPropertyForName("SettingIcon")));
        about.setGraphic(new ImageView(Helper.getPropertyForName("AboutIcon")));
    }

    protected void createTable() {

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


        //Cоздание корня таблицы
   updateTable();
    }

    protected void createItems(TreeItem root, Product group) {
        productObsList.stream().filter(a -> a.getParent() != null && a.getParent().equals(group)).forEach(b -> {
            TreeItem<PriceDataElement> elementGroup = new TreeItem<>(new PriceDataElement(b));
            if (b.isGroup()) {
                createItems(elementGroup, b);
            } else {
                createItemsShop(elementGroup, b);
            }

            root.getChildren().add(elementGroup);
        });
    }

    protected void createItemsShop(TreeItem root, Product group) {
        shopService.getAll().stream().forEach(a ->{
            root.getChildren().add(new TreeItem<>(new PriceDataElement(a, group)));
        });
    }

    //****БЫСТРЫЕ КНОПКИ ДОБАВЛЕНИЯ ЭЛЕМЕНТОВ
    @FXML
    void shopAddOnAction(ActionEvent event) {
        shopElementStarter.showForm(currentStage, new Shop());
    }

    @FXML
    void productAddOnAction(ActionEvent event) {
        productElementStarter.showForm(currentStage, new Product());
    }

    @FXML
    void priceAddOnAction(ActionEvent event) {
        priceElementStarter.showForm(currentStage, new Price());
    }

    //****КНОПКИ ОТКРЫТИЯ СПИСКОВ
    @FXML
    void shopListOnAction(ActionEvent event) {
        shopListStarter.showForm(currentStage);
    }

    @FXML
    void productListOnAction(ActionEvent event) {
        productListStarter.showForm(currentStage);
    }

    @FXML
    void priceListOnAction(ActionEvent event) {
        priceListStarter.showForm(currentStage);
    }

    //****КНОПКА "О ПРОГРАММЕ"
    @FXML
    void aboutOnAction(ActionEvent event) {
        aboutStarter.showForm(currentStage);
    }

    //****КНОПКА ВЫХОДА
    @FXML
    void exitOnAction(ActionEvent event) {
        close();
    }


    @FXML
    void updateOnAction(ActionEvent event) {
        updateTable();
    }

    @FXML
    void updateOnActionCM(ActionEvent event) {
        updateTable();
    }


    protected void updateTable() {

        TreeItem<PriceDataElement> root = new TreeItem<PriceDataElement>(new PriceDataElement(new Product(-1, null, true, "Все товары")));
        priceData.setRoot(root);

        productObsList.stream().filter(a -> a.getParent() == null).forEach(b -> {
            TreeItem<PriceDataElement> elementGroup = new TreeItem<>(new PriceDataElement(b));
            if (b.isGroup()) {
                createItems(elementGroup, b);
            } else {
                createItemsShop(elementGroup, b);
            }
            root.getChildren().add(elementGroup);
        });

    }

    /**
     * Вспомогательный класс для заполнения главной таблицы
     */
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
            this.description = new SimpleStringProperty(shop.getDescription());
        }

        public PriceDataElement(Product product) {
            this.product = product;
            this.description = new SimpleStringProperty(product.getDescription());
        }

        public PriceDataElement(Shop shop, Product product) {
            this.product = product;
            this.shop = shop;
            this.description = new SimpleStringProperty(shop.getDescription());

            fillPriceToBeginYear();
            fillPriceToBeginMonth();
            fillPriceActual();
            fillDeviationToBeginYear();
            fillDeviationToLastMonth();
        }

        protected void fillPriceToBeginYear() {
            Calendar calendar = SimpleCalendar.getBeginningCurrentYear();

            LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
            priceToBeginYear = new SimpleDoubleProperty(priceService.getPriceForDate(shop, product, localDate));
        }

        protected void fillPriceToBeginMonth() {
            Calendar calendar = SimpleCalendar.getBeginningCurrentMonth();
            LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
            priceToBeginMonth = new SimpleDoubleProperty(priceService.getPriceForDate(shop, product, localDate));
        }

        protected void fillPriceActual() {
            Calendar calendar = SimpleCalendar.getBeginningCurrentMonth();
            LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
            priceActual = new SimpleDoubleProperty(priceService.getPriceForDate(shop, product, localDate));
        }

        protected void fillDeviationToBeginYear() {


        }

        protected void fillDeviationToLastMonth() {


        }

    }



    @Override
    public void close() {

       if(CLoseProgramMessage.show()){






        currentStage.close();}
    }
}
