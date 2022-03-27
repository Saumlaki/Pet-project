package ru.saumlaki.time_tracker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.supporting.Error;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.supporting.TimerWatch;
import ru.saumlaki.time_tracker.view.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TimeTracker extends Application {

    //***Общие переменные сосредоточенные в одном месте***
    public static String propertyFileName = "src/main/resources/settings.properties";//Адрес файла настроек
    public static Connection connection;//Подключение для работы с БД

    public static ObservableList<TypeOfTime> typeOfTimeObsList = FXCollections.observableArrayList();
    public static ObservableList<Time> timeObsList = FXCollections.observableArrayList();
    public static ObservableList<DataOfTime> dataOfTimeObsList = FXCollections.observableArrayList();

    public static void main(String[] args) {

        createConnection();
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        new Main().showForm(stage, new TimerWatch());
    }

    @Override
    public void stop() throws Exception {

        if(connection!=null) connection.close();
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
        dataOfTimeObsList.addAll(ServiceFactory.getService(DataOfTime.class).getAll());
    }

    //***Настройка соединения с базой данных***

    /**
     * Метод создает и возвращает подключение к базе данных
     */
    public static void createConnection() {

        //1. Получаем имя файла базы данных
        String dbFilename = TimeTracker.getPropertyForName("DBName");

        //2. Проверяем что файл базы данных существует. Если его нет, то создаем его
        createDBFile(dbFilename);

        //3. Создаем собственно само соединение с БД
        try {
            String url = "jdbc:sqlite:" + new File(dbFilename).getAbsolutePath();
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Error.showError("Ошибка подключения к базе данных", ex.getMessage());
        }

        //4. Проверяем что присутствуют все необходимые таблицы. Если их нет, то создаем их
        createTables();
    }

    /**
     * Метод создает файл базы данных по полному пути <code>dbName</code> в случае его отсутствия
     */
    private static void createDBFile(String dbName) {

        File dbFile = new File(dbName);
        if (!dbFile.exists()) {
            try {
                if (!dbFile.createNewFile()) {
                   Error.showError("Ошибка создания файла базы данных");
                }
            } catch (IOException e) {
                Error.showError("Ошибка создания файла базы данных");
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
            Error.showError("Ошибка проверки наличия таблицы", ex.getMessage());
        }

        //2. Создаем таблицу если она отсутствует
        sqlQuery = "CREATE TABLE IF NOT EXISTS type_of_time(id int PRIMARY KEY,description varchar(150))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("Ошибка создания таблицы", ex.getMessage());
        }

        //3. Если в п.1 таблица не была найдена, то созданную в п.2 таблицу заполняем начальными данными
        if (!tableIsExist) {
            sqlQuery = "INSERT OR IGNORE INTO type_of_time VALUES (1,'Работа'),(2,'Учеба'),(3,'Спорт'),(4,'Еда'),(5,'Отдых'),(6,'Развлечения')";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sqlQuery);
            } catch (SQLException ex) {
                Error.showError("Ошибка заполнения таблицы", ex.getMessage());
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
            Error.showError("Ошибка создания таблицы таблицы", ex.getMessage());
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
            Error.showError("Ошибка создания таблицы таблицы", ex.getMessage());
        }
    }

    //---Прочие---

    /**
     * Метод получения настройки по имени
     */
    public static String getPropertyForName(String propertyName) {

        String value = "";
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream(TimeTracker.propertyFileName)) {

            property.load(fis);

            value = property.getProperty(propertyName);
        } catch (IOException ex) {
            System.out.println("err.Ошибка загрузки файла настроек.");
        }

        return value;


    }
}
