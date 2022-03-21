package ru.saumlaki.time_tracker;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.dao.interfaces.DAO;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.view.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class TimeTracker extends Application {

    /**
     * Общие переменные сосредоточенные в одном месте
     */
    public static String propertyFileName = "src/main/resources/settings.properties";
    public static Connection connection;


    public static void main(String[] args) {

        createConnection();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        new Main().showElementForm(stage);
    }

    /////////////////////////////////////////////////
    // Прочие методы

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

    /////////////////////////////////////////////////
    // Настройка соединения с базой данных

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
            url.replace("\\","/");
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            DialogMessengerElementForm.showError("Ошибка подключения к базе данных", ex.getMessage());
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
                dbFile.createNewFile();
            } catch (IOException e) {
                System.out.println("err. Ошибка создания файла базы данных");
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
            DialogMessengerElementForm.showError("Ошибка проверки наличия таблицы", ex.getMessage());
        }

        //2. Создаем таблицу если она отсутствует
        sqlQuery = "CREATE TABLE IF NOT EXISTS type_of_time(id int PRIMARY KEY,description varchar(150))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            DialogMessengerElementForm.showError("Ошибка создания таблицы", ex.getMessage());
        }

        //3. Если в п.1 таблица не была найдена, то созданную в п.2 таблицу заполняем начальными данными
        if (!tableIsExist) {
            sqlQuery = "INSERT OR IGNORE INTO type_of_time VALUES (1,'Работа'),(2,'Учеба'),(3,'Спорт'),(4,'Еда'),(5,'Отдых'),(6,'Развлечения')";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sqlQuery);
            } catch (SQLException ex) {
                DialogMessengerElementForm.showError("Ошибка заполнения таблицы", ex.getMessage());
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
            DialogMessengerElementForm.showError("Ошибка создания таблицы таблицы", ex.getMessage());
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
            DialogMessengerElementForm.showError("Ошибка создания таблицы таблицы", ex.getMessage());
        }
    }
}
