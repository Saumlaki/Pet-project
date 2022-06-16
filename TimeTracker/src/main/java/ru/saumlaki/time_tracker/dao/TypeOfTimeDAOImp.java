package ru.saumlaki.time_tracker.dao;

import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.dao.interfaces.TypeOfTimeDAO;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.supporting.Error;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypeOfTimeDAOImp implements TypeOfTimeDAO {
    @Override
    public void add(TypeOfTime object) {

        String line = String.format("(%s, '%s')", object.getId(), object.getDescription());
        String sqlQuery = "INSERT INTO type_of_time VALUES " + line;

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[TypeOfTimeDAOImp]Ошибка вставки данных", ex.getMessage());
        }
    }

    @Override
    public void update(TypeOfTime object) {

        String line = String.format("SET description = '%s' WHERE id = %s",
                object.getDescription(),
                object.getId());
        String sqlQuery = "UPDATE type_of_time  " + line;

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[TypeOfTimeDAOImp]Ошибка обновления данных", ex.getMessage());
        }
    }

    @Override
    public void remove(TypeOfTime object) {

        String sqlQuery = "DELETE FROM type_of_time  WHERE id =  " + object.getId();

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[TypeOfTimeDAOImp]Ошибка удаления", ex.getMessage());
        }
    }

    @Override
    public TypeOfTime getByID(int id) {

        TypeOfTime result = null;

        //Заполняем лист
        String sqlQuery = "SELECT * FROM type_of_time where id = " + id;

        try (Statement stmt = TimeTracker.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                String description = resultSet.getString("description");

                result = new TypeOfTime(id, description);
            }
        } catch (SQLException ex) {
            Error.showError("[TypeOfTimeDAOImp]Ошибка выборки данных по id", ex.getMessage());
        }
        return result;
    }

    @Override
    public List<TypeOfTime> getAll() {

        List<TypeOfTime> list = new ArrayList<>();

        //Заполняем лист
        String sqlQuery = "SELECT * FROM type_of_time";

        try (Statement stmt = TimeTracker.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");

                list.add(new TypeOfTime(id, description));
            }
        } catch (SQLException ex) {
            Error.showError("[TypeOfTimeDAOImp]Ошибка выборки данных", ex.getMessage());
        }

        return list;
    }

    @Override
    public int getNextId() {

        int id = 1;

        String sqlQuery = "SELECT max(id) as id FROM type_of_time";

        try (Statement stmt = TimeTracker.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                id++;
            }
        } catch (SQLException ex) {
            Error.showError("[TypeOfTimeDAOImp]Ошибка выборки данных", ex.getMessage());
        }

        return id;
    }

    @Override
    public void createTable() {
        //1. Смотрим есть ли таблица в БД
        String sqlQuery = "SELECT count(*) AS tableIsExist FROM sqlite_master WHERE type='table' AND name='type_of_time'";
        boolean tableIsExist = false;

        try (Statement stmt = TimeTracker.connection.createStatement();
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

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            System.out.println("err.Ошибка создания таблицы таблицы TypeOfTime");
            System.out.println("--" + ex.getMessage());
        }

        //3. Если в п.1 таблица не была найдена, то созданную в п.2 таблицу заполняем начальными данными
        if (!tableIsExist) {
            sqlQuery = "INSERT OR IGNORE INTO type_of_time VALUES (1,'Работа'),(2,'Учеба'),(3,'Спорт'),(4,'Еда'),(5,'Отдых'),(6,'Развлечения')";
            try (Statement stmt = TimeTracker.connection.createStatement()) {
                stmt.execute(sqlQuery);
            } catch (SQLException ex) {
                System.out.println("err.Ошибка начального заполнения таблицы таблицы TypeOfTime");
                System.out.println("--" + ex.getMessage());
            }
        }
    }
}
