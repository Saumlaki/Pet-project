package ru.saumlaki.time_tracker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.DataOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.supporting.TimerWatch;
import ru.saumlaki.time_tracker.supporting.data.SimpleCalendar;
import ru.saumlaki.time_tracker.view.Main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class TimeTracker extends Application {

    //***Общие переменные сосредоточенные в одном месте***
    public static String propertyFileName = "settings.properties";//Адрес файла настроек
    public static Connection connection;//Подключение для работы с БД

    public static ObservableList<TypeOfTime> typeOfTimeObsList = FXCollections.observableArrayList();
    public static ObservableList<Time> timeObsList = FXCollections.observableArrayList();
    public static ObservableList<DataOfTime> dataOfTimeObsList = FXCollections.observableArrayList();
    public static ObservableList<DataOfTime> dataOfTimeObsListALL = FXCollections.observableArrayList();

    public static void main(String[] args) {

        //Заставку пока отключим...
        //System.setProperty("javafx.preloader", "view.PreloaderElement");
        //Application.launch(TimeTracker.class, args);
        launch();
    }

    @Override
    public void start(Stage stage) throws InterruptedException {

        //Подготавливаем приложение
        //1. Создание соединения с БД
        createConnection();

        //2. Заполнение списков
        typeOfTimeObsListUpdate();
        timeObsListUpdate();
        dataOfTimeObsListUpdate();
        dataOfTimeObsListAllUpdate();

        //3. Закрываем заставку
        //Thread.sleep(500);
        //this.notifyPreloader(new Preloader.StateChangeNotification(null));

        //4. Начинаем работу основного приложения
        new Main().showForm(stage, null, new TimerWatch());
    }

    @Override
    public void stop() throws Exception {

        if (connection != null) connection.close();
    }

    //***Прочие методы***

    //---Группа методов обновления листов---

    public static void typeOfTimeObsListUpdate() {

        typeOfTimeObsList.clear();
        typeOfTimeObsList.addAll(ServiceFactory.getService(TypeOfTime.class).getAll());
    }

    public static void timeObsListUpdate() {

        timeObsList.clear();
        timeObsList.addAll(ServiceFactory.getService(Time.class).getAll());
    }

    public static void dataOfTimeObsListUpdate() {

        dataOfTimeObsList.clear();
        dataOfTimeObsList.addAll(new DataOfTimeServiceImpl().getByPeriod(SimpleCalendar.getBeginningCurrentDay().getTime(), SimpleCalendar.getEndCurrentDay().getTime()));
    }

    public static void dataOfTimeObsListAllUpdate() {

        dataOfTimeObsListALL.clear();
        dataOfTimeObsListALL.addAll(ServiceFactory.getService(DataOfTime.class).getAll());
    }

    //***Настройка соединения с базой данных***

    /**
     * Метод создает и возвращает подключение к базе данных
     */
    public static void createConnection() {

        //1. Получаем имя файла базы данных
        String dbFileName = TimeTracker.getPropertyForName("DBName");

        //2.Создаем файл БД рядом с .jar или рядом с .CLASS
        URL url = new TimeTracker().getClass().getClassLoader().getResource("");
        String extURL = url.toExternalForm();

        if (extURL.endsWith(".jar"))
            extURL = extURL.substring(0, extURL.lastIndexOf("/"));
        else {
            String suffix = "/" + (new TimeTracker().getClass().getName()).replace(".", "/") + ".class";
            extURL = extURL.replace(suffix, "");
            if (extURL.startsWith("jar:") && extURL.endsWith(".jar!"))
                extURL = extURL.substring(4, extURL.lastIndexOf("/"));
        }

        if (!extURL.endsWith("/")) extURL = extURL + "/";
        try {
            url = new URL(extURL + dbFileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        //3. Проверяем что файл базы данных существует. Если его нет, то создаем его
        createDBFile(url);

        //4. Создаем собственно само соединение с БД
        try {
            String path = url.getPath();
            path = path.startsWith("/") ? path.substring(1, path.length()) : path;
            path = "jdbc:sqlite:" + path;

            connection = DriverManager.getConnection(path);
        } catch (SQLException ex) {
            System.out.println("err.Ошибка подключения к базе данных");
            System.out.println("--" + ex.getMessage());
        }

        //4. Проверяем что присутствуют все необходимые таблицы. Если их нет, то создаем их
        createTables();
    }

    /**
     * Метод создает файл базы данных по полному пути <code>dbName</code> в случае его отсутствия
     */
    private static void createDBFile(URL dbName) {

        System.out.println("Проверка наличия файла БД:");
        System.out.println("--Путь: " + dbName);

        File dbFile = null;
        try {
            dbFile = new File(dbName.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (!dbFile.exists()) {
            try {
                System.out.println("Файл БД не обнаружен. Пытаюсь создать");
                if (!dbFile.createNewFile()) {
                    System.out.println("err.Не смог создать файл БД");
                }
            } catch (IOException ex) {
                System.out.println("err.Ошибка создания файла БД");
                System.out.println("--" + ex.getMessage());
            }
        }
    }

    /**
     * Метод объединяет в себе вызов методов для проверки существования рабочих таблиц
     */
    private static void createTables() {
        createTableTypeOfTime();
        createTableTime();
        createTableDataOfTime();
    }

    /**
     * Создание таблицы для класса <code>TypeOfTime</code>
     *
     * @see TypeOfTime
     */
    private static void createTableTypeOfTime() {

        //1. Смотрим есть ли таблица в БД
        String sqlQuery = "SELECT count(*) AS tableIsExist FROM sqlite_master WHERE type='table' AND name='type_of_time'";
        boolean tableIsExist = false;

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                tableIsExist = resultSet.getBoolean("tableIsExist");
            }
        } catch (SQLException ex) {
            System.out.println("err.Ошибка проверки наличия таблицы TypeOfTime");
            System.out.println("--" + ex.getMessage());
        }

        //2. Создаем таблицу если она отсутствует
        sqlQuery = "CREATE TABLE IF NOT EXISTS type_of_time(id int PRIMARY KEY,description varchar(150))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            System.out.println("err.Ошибка создания таблицы таблицы TypeOfTime");
            System.out.println("--" + ex.getMessage());
        }

        //3. Если в п.1 таблица не была найдена, то созданную в п.2 таблицу заполняем начальными данными
        if (!tableIsExist) {
            sqlQuery = "INSERT OR IGNORE INTO type_of_time VALUES (1,'Работа'),(2,'Учеба'),(3,'Спорт'),(4,'Еда'),(5,'Отдых'),(6,'Развлечения')";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sqlQuery);
            } catch (SQLException ex) {
                System.out.println("err.Ошибка начального заполнения таблицы таблицы TypeOfTime");
                System.out.println("--" + ex.getMessage());
            }
        }
    }

    /**
     * Создание таблицы для класса <code>Time</code>
     *
     * @see Time
     */
    private static void createTableTime() {

        //1. Создаем таблицу если она отсутствует
        String sqlQuery = "CREATE TABLE IF NOT EXISTS time(id int PRIMARY KEY, description varchar(150), type_of_time_id int, CONSTRAINT type_of_time_id FOREIGN KEY (type_of_time_id) REFERENCES type_of_time(id))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlQuery);

        } catch (SQLException ex) {
            System.out.println("err.Ошибка создания таблицы таблицы Time");
            System.out.println("--" + ex.getMessage());
        }
    }

    /**
     * Создание таблицы для класса <code>DataOfTime</code>
     *
     * @see DataOfTime
     */
    private static void createTableDataOfTime() {

        //1. Создаем таблицу если она отсутствует
        String sqlQuery = "CREATE TABLE IF NOT EXISTS data_fo_time(id int  PRIMARY KEY,date_day date,time_id int,seconds int,CONSTRAINT time_id FOREIGN KEY (time_id) REFERENCES time(id))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlQuery);

        } catch (SQLException ex) {
            System.out.println("err.Ошибка создания таблицы таблицы DataOfTime");
            System.out.println("--" + ex.getMessage());
        }
    }

    //---Прочие---

    /**
     * Метод получения настройки по имени
     */
    public static String getPropertyForName(String propertyName) {

        String value = "";
        Properties property = new Properties();
        try {
            property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("settings.properties"));

            value = property.getProperty(propertyName);
        } catch (IOException ex) {
            System.out.println("err.Ошибка загрузки файла настроек.");
            System.out.println("--" + ex.getMessage());
        }

        return value;
    }
}
