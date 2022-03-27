package ru.saumlaki.time_tracker.dao;

import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.dao.interfaces.TimeDAO;
import ru.saumlaki.time_tracker.supporting.Error;
import ru.saumlaki.time_tracker.entity.Time;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TimeDAOImpl implements TimeDAO {

    @Override
    public void add(Time object) {

        String line = String.format("(%s, '%s', %s)", object.getId(), object.getDescription(), object.getTypeOfTime().getId());
        String sqlQuery = "INSERT INTO time VALUES " + line;

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[TimeDAOImpl]Ошибка вставки данных", ex.getMessage());
        }
    }

    @Override
    public void update(Time object) {

        String line = String.format("SET description = '%s', type_of_time_id = %s WHERE id = %s",
                object.getDescription(),
                object.getTypeOfTime().getId(),
                object.getId());
        String sqlQuery = "UPDATE time  " + line;

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[TimeDAOImpl]Ошибка обновления данных", ex.getMessage());
        }
    }

    @Override
    public void remove(Time object) {

        String sqlQuery = "DELETE FROM time  WHERE id =  " + object.getId();

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[TimeDAOImpl]Ошибка удаления", ex.getMessage());
        }
    }

    @Override
    public Time getByID(int id) {

        Time result = null;

        //Заполняем лист
        String sqlQuery = "SELECT * FROM time where id = " + id;

        try (Statement stmt = TimeTracker.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                String description = resultSet.getString("description");
                int type_of_time_id = resultSet.getInt("type_of_time_id");

                result = new Time(id, description, new TypeOfTimeDAOImp().getByID(type_of_time_id));
            }
        } catch (SQLException ex) {
            Error.showError("[TimeDAOImpl]Ошибка выборки данных по id", ex.getMessage());
        }
        return result;
    }

    @Override
    public List<Time> getAll() {

        List<Time> list = new ArrayList<>();

        //Заполняем лист
        String sqlQuery = "SELECT * FROM time";

        try (Statement stmt = TimeTracker.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                int type_of_time_id = resultSet.getInt("type_of_time_id");

                list.add(new Time(id, description, new TypeOfTimeDAOImp().getByID(type_of_time_id)));
            }
        } catch (SQLException ex) {
            Error.showError("[TimeDAOImpl]Ошибка выборки данных", ex.getMessage());
        }

        return list;
    }

    @Override
    public int getNextId() {
        int id = 1;

        String sqlQuery = "SELECT max(id) as id FROM time";

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
}
