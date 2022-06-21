package ru.saumlaki.price_dynamic.controllers.main;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.controllers.listGroup.Imageeble;
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

import java.io.IOException;
import java.net.URL;
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
    private TreeTableView<PriceDataElement> list;

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
        setOnMouseClicked();
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

        TreeTableColumn<PriceDataElement, String> columnTemp = new TreeTableColumn("");
        TreeTableColumn<PriceDataElement, ImageView> columnImg = new TreeTableColumn();
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

        list.getColumns().addAll(columnTemp, columnImg, columnShop, columnPrice, columnDynamic);
        list.getColumns().addAll(columnImg, columnShop, columnPrice, columnDynamic);

        columnShop.setCellValueFactory(a -> a.getValue().getValue().getDescription());
        columnPrice1.setCellValueFactory(a -> a.getValue().getValue().getPriceToBeginYear());
        columnPrice2.setCellValueFactory(a -> a.getValue().getValue().getPriceToBeginMonth());
        columnPrice3.setCellValueFactory(a -> a.getValue().getValue().getPriceActual());
        columnDynamic1.setCellValueFactory(a -> a.getValue().getValue().getDeviationToBeginYear());
        columnDynamic2.setCellValueFactory(a -> a.getValue().getValue().getDeviationToLastMonth());
        columnImg.setCellValueFactory(a -> new ObservableValueBase<ImageView>() {
                    @Override
                    public ImageView getValue() {
                        if (a.getValue().getValue().getShop() != null) {
                            URL iconURL = Helper.getResourcesURLForPropertyName("ShopIcon");
                            Image image = null;
                            try {
                                image = new Image(iconURL.openStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return new ImageView(image);
                        } else {
                            return a.getValue().getValue().getProduct().getImage();
                        }
                    }
                }
        );

        columnTemp.setPrefWidth(50);

        //Cоздание корня таблицы
        update();

        list.setShowRoot(false);
    }

    protected void createItems(TreeItem root, Product group) {
        /*Заполнение верхнего уровня*/
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
        shopService.getAll().stream().forEach(a -> {
            root.getChildren().add(new TreeItem<>(new PriceDataElement(a, group)));
        });
    }

    @Override
    public void close() {

        if (CLoseProgramMessage.show()) {
            currentStage.close();
        }
    }

    protected void setOnMouseClicked() {
        list.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                changeElement();
            }
        });
    }

    //**** КОМАНДЫ ТАБЛИЧНОЙ ЧАСТИ

    @FXML
    void shopAddOnAction(ActionEvent event) {
        shopAdd();
    }

    @FXML
    void productAddOnAction(ActionEvent event) {
        productAdd();
    }

    @FXML
    void priceAddOnAction(ActionEvent event) {
        priceAdd();
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        update();
    }

    //**** КОМАНДЫ ГЛАВНОГО МЕНЮ ФОРМЫ

    @FXML
    void shopListOnAction(ActionEvent event) {
        shopListOpen();
    }

    @FXML
    void productListOnAction(ActionEvent event) {
        productListOpen();
    }

    @FXML
    void priceListOnAction(ActionEvent event) {
        priceListOpen();
    }

    @FXML
    void aboutOnAction(ActionEvent event) {
        aboutStarter.showForm(currentStage);
    }

    @FXML
    void exitOnAction(ActionEvent event) {
        close();
    }

    //**** КОМАНДЫ КОНТЕКСТНОГО МЕНЮ

    @FXML
    void updateOnActionCM(ActionEvent event) {
        update();
    }

    @FXML
    void shopAddOnActionCM(ActionEvent event) {
        shopAdd();
    }

    @FXML
    void productAddOnActionCM(ActionEvent event) {
        productAdd();
    }

    @FXML
    void priceAddOnActionCM(ActionEvent event) {
        priceAdd();
    }

    @FXML
    void changeOnActionCM(ActionEvent event) {
        changeElement();
    }

    //**** МЕТОДЫ ДОБАВЛЕНИЯ ЭЛЕМЕНТОВ

    private void shopAdd() {
        shopElementStarter.showForm(currentStage, new Shop());
    }

    private void productAdd() {
        productElementStarter.showForm(currentStage, new Product());
    }

    private void priceAdd() {
        priceElementStarter.showForm(currentStage, new Price());
    }

    //**** МЕТОДЫ ИЗМЕНЕНИЯ ЭЛЕМЕНТОВ

    private void changeElement() {
        PriceDataElement priceDataElement = getCurrentObject();

        if (priceDataElement == null) return;

        if (priceDataElement.getProduct() != null && priceDataElement.getProduct().getId() == -1) return;

        if (priceDataElement.getProduct() != null && priceDataElement.getShop() != null) {
            shopChange(priceDataElement.getShop());
        } else if (priceDataElement.getProduct() != null) {
            productChange(priceDataElement.getProduct());
        }
    }

    private void shopChange(Shop element) {
        shopElementStarter.showForm(currentStage, element);
    }

    private void productChange(Product element) {
        productElementStarter.showForm(currentStage, element);
    }

    private void priceChange(Price element) {
        priceElementStarter.showForm(currentStage, element);
    }

    //**** МЕТОДЫ ОТКРЫТИЯ СПИСКОВ ЭЛЕМЕНТОВ

    private void shopListOpen() {
        shopListStarter.showForm(currentStage);
    }

    private void productListOpen() {
        productListStarter.showForm(currentStage);
    }

    private void priceListOpen() {
        priceListStarter.showForm(currentStage);
    }

    //**** МЕТОДЫ ОБНОВЛЕНИЯ ФОРМЫ

    protected void update() {
        /*Заполнение верхнего уровня*/
        TreeItem<PriceDataElement> root = new TreeItem<PriceDataElement>(new PriceDataElement(new Product(-1, null, true, "Все товары")));
        list.setRoot(root);

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

    //**** МЕТОДЫ ОБНОВЛЕНИЯ ГРАФИКА

//    protected void updateGraphik

    //**** ПРОЧИЕ МЕТОДЫ

    /**
     * Метод возвращает значение текущего элемента списка
     */
    protected PriceDataElement getCurrentObject() {
        if (list.getSelectionModel().getSelectedItem() == null) return null;
        else
            return list.getSelectionModel().getSelectedItem().getValue();
    }

    //**** СПОМОГАТЕЛЬНЫЕ КЛАССЫ

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

}
