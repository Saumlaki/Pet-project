package ru.saumlaki.time_tracker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.DataOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.TimeServiceImpl;
import ru.saumlaki.time_tracker.service.TypeOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.supporting.data.SimpleCalendar;
import ru.saumlaki.time_tracker.view.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс отвечает за инициализацию программы. Запуск программы осуществляется из класса <code>MainLauncher</code>
 *
 * @see MainLauncher
 */
public class TimeTracker extends Application {

    //***Общие переменные сосредоточенные в одном месте***

    /*Настройки*/
    public static String propertyFileName = "settings.properties";
    private static Properties property;

    /*Соединение с базой данных*/
    public static Connection connection;

    /*Списки в которых храним данные с которыми работаем, которые отображаем на форме*/
    public static ObservableList<TypeOfTime> typeOfTimeObsList = FXCollections.observableArrayList();
    public static ObservableList<Time> timeObsList = FXCollections.observableArrayList();
    public static ObservableList<DataOfTime> dataOfTimeObsList = FXCollections.observableArrayList();
    public static ObservableList<DataOfTime> dataOfTimeObsListALL = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws InterruptedException {
        //1. Создание соединения с БД
        createConnection();

        //2. Заполнение списков
        typeOfTimeObsListUpdate();
        timeObsListUpdate();
        dataOfTimeObsListUpdate();
        dataOfTimeObsListAllUpdate();

        //4. Начинаем работу основного приложения
        new Main().showForm(stage, null);
    }

    @Override
    public void stop() throws Exception {
        if (connection != null) connection.close();
    }

    /********************************************************/
    /**ОБНОВЛЕНИЕ СПИСКОВ ЗНАЧЕНИЙ>*/

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

    /********************************************************/
    /**МЕТОДЫ РАБОТЫ С БАЗОЙ ДАННЫХ>*/

    /**
     * Метод создает и возвращает подключение к базе данных
     */
    public static void createConnection() {

        //1. Получаем путь до БД во временных файлах
        String dbAddress = getTempFileCatalog() + "\\DB.sqlite";

        //2. Проверяем что файл базы данных существует. Если его нет, то создаем его
        createDBFile(dbAddress);

        //3. Создаем собственно само соединение с БД
        try {
            dbAddress = "jdbc:sqlite:" + dbAddress;
            connection = DriverManager.getConnection(dbAddress);
        } catch (SQLException ex) {
            System.out.println("err.Ошибка подключения к базе данных");
            System.out.println("--" + ex.getMessage());
        }

        //4. Проверяем что присутствуют все необходимые таблицы. Если их нет, то создаем их
        createTables();
    }

    /**
     * Метод объединяет в себе вызов методов для проверки существования рабочих таблиц
     */
    private static void createTables() {
        new TypeOfTimeServiceImpl().createTable();
        new TimeServiceImpl().createTable();
        new DataOfTimeServiceImpl().createTable();
    }

    /********************************************************/
    /**МЕТОДЫ РАБОТЫ С ФАЙЛОВОЙ СИСТЕМОЙ*/

    /**
     * Метод возвращает путь до каталога временных файлов
     */
    private static String getTempFileCatalog() {
        String tempFile = "";
        try {
            tempFile = Files.createTempFile("", ".tmp").toFile().getParentFile().getCanonicalPath();
        } catch (IOException e) {
            System.out.println("err.Ошибка получения каталога временных файлов");
            e.printStackTrace();
        }
        return tempFile;
    }

    /**
     * Метод создает файл базы данных по полному пути <code>dbName</code> в случае его отсутствия
     */
    private static void createDBFile(String dbName) {
        System.out.println("Проверка наличия файла БД:");
        System.out.println("--Путь: " + dbName);

        File dbFile = new File(dbName);

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

    /********************************************************/
    /**МЕТОДЫ РАБОТЫ С ФАЙЛОМ СВОЙСТВ*/

    /**
     * Метод получения настройки по имени
     *
     * @param propertyName - имя параметра
     * @return String - значение параметра
     */
    public static String getPropertyForName(String propertyName) {
        if (property == null) {
            property = new Properties();
            try {
                property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("settings.properties"));

            } catch (IOException ex) {
                System.out.println("err.Ошибка загрузки файла настроек.");
                System.out.println("--" + ex.getMessage());
            }
        }
        return property.getProperty(propertyName);
    }
}
