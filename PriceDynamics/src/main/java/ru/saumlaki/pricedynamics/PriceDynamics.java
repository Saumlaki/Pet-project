package ru.saumlaki.pricedynamics;

import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.saumlaki.pricedynamics.config.DataConfig;
import ru.saumlaki.pricedynamics.entity.City;

@Component
public class PriceDynamics extends Application{
    @Autowired
    private Session session;

    public static void main(String[] args) {

        Configuration hbConfiguration = new Configuration();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DataConfig.class);
        context.refresh();

        //launch();
    }
    @Transactional
    protected void createElement() {

        City city = new City();
        city.setName("Город номер " + (int)(Math.random()*100));

        session.save(city);
    }
    @Override
    public void start(Stage stage) throws Exception {


//
//        FXMLLoader fxmlLoader = new FXMLLoader(PriceDynamics.class.getResource("/view/mainView.fxml"));
//        VBox root = null;
//        try {
//            root = fxmlLoader.load();
//            MainViewController mainViewController = fxmlLoader.getController();
//            mainViewController.addElements();
//        } catch (IOException e) {
//            System.out.println("-->Ошибка");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        Scene scene  = new Scene(root, 320, 240);
//        stage.setScene(scene);
//        stage.show();



//        TreeTableView<Product> treeTableView = new TreeTableView<Product>();
//
//        // Create column EmpNo (Data type of String).
//        TreeTableColumn<Product, String> empNoCol //
//                = new TreeTableColumn<Product, String>("Emp No");
//
//        // Create column FullName (Data type of String).
//        TreeTableColumn<Product, String> fullNameCol//
//                = new TreeTableColumn<Product, String>("Full Name");
//
//
//        // Defines how to fill data for each cell.
//        // Get value from property of Employee.
//        empNoCol.setCellValueFactory(new TreeItemPropertyValueFactory<Product, String>("nameNNNN"));
//
//
//        // Add columns to TreeTable.
//        treeTableView.getColumns().addAll(empNoCol, fullNameCol);
//
//        // Data
//        Product empBoss = new Product("E00", "Abc@gmail.com");
//
//        Product empSmith = new Product("E01", "Smith@gmail.com");
//
//
//        Product empMcNeil = new Product("E02", "McNeil@gmail.com") ;
//
//
//        // Root Item
//        TreeItem<Product> itemRoot = new TreeItem<Product>(empBoss);
//        TreeItem<Product> itemSmith = new TreeItem<Product>(empSmith);
//        TreeItem<Product> itemMcNeil = new TreeItem<Product>(empMcNeil);
//
//        itemRoot.getChildren().addAll(itemSmith, itemMcNeil);
//        treeTableView.setRoot(itemRoot);
//        //
//        StackPane root = new StackPane();
//        root.setPadding(new Insets(5));
//        root.getChildren().add(treeTableView);
//
//        stage.setTitle("TreeTableView (o7planning.org)");
//
//        Scene scene = new Scene(root, 450, 300);
//        stage.setScene(scene);
//        stage.show();


    }
}


//https://betacode.net/11149/javafx-treetableview