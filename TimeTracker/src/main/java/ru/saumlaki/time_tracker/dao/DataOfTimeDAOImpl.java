package ru.saumlaki.time_tracker.dao;

import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.dao.interfaces.DataOfTimeDAO;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.supporting.Error;
import ru.saumlaki.time_tracker.supporting.data.SimpleCalendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataOfTimeDAOImpl implements DataOfTimeDAO {
    @Override
    public void add(DataOfTime object) {

        String line = String.format("(%s, '%s', %s, %s)",
                object.getId(),
                SimpleCalendar.getCalendarToSQLFormat(object.getCalendar()),
                object.getTime().getId(),
                object.getValues());

        String sqlQuery = "INSERT INTO data_fo_time VALUES " + line;

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[DataOfTimeDAOImpl]Ошибка вставки данных", ex.getMessage());
        }
    }

    @Override
    public void update(DataOfTime object) {

        String line = "SET " +
                "date_day = \'" + SimpleCalendar.getCalendarToSQLFormat(object.getCalendar()) + "\', " +
                "time_id = " + object.getTime().getId() + ", " +
                "seconds = " + object.getValues() + " " +
                "WHERE id =" + object.getId();

        String sqlQuery = "UPDATE data_fo_time  " + line;

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[DataOfTimeDAOImpl]Ошибка обновления данных", ex.getMessage());
        }
    }

    @Override
    public void remove(DataOfTime object) {

        String sqlQuery = "DELETE FROM data_fo_time  WHERE id =  " + object.getId();

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            Error.showError("[DataOfTimeDAOImpl]Ошибка удаления", ex.getMessage());
        }
    }

    @Override
    public DataOfTime getByID(int id) {

        DataOfTime result = null;

        //Заполняем лист
        String sqlQuery = "SELECT * FROM data_fo_time where id = " + id;

        try (Statement stmt = TimeTracker.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlQuery)) {

            while (resultSet.next()) {

                Date date = resultSet.getDate("date_day");
                int time_id = resultSet.getInt("time_id");
                int seconds = resultSet.getInt("seconds");

                result = new DataOfTime(id,
                        SimpleCalendar.getCalendarToDate(date),
                        new TimeDAOImpl().getByID(time_id),
                        seconds);
            }
        } catch (SQLException ex) {
            Error.showError("[DataOfTimeDAOImpl]Ошибка выборки данных по id", ex.getMessage());
        }
        return result;
    }

    @Override
    public List<DataOfTime> getAll() {

        List<DataOfTime> list = new ArrayList<>();

        //Заполняем лист
        String sqlQuery = "SELECT * FROM data_fo_time ";

        try (Statement stmt = TimeTracker.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("date_day");
                int time_id = resultSet.getInt("time_id");
                int seconds = resultSet.getInt("seconds");

                DataOfTime dataOfTime = new DataOfTime(id,
                        SimpleCalendar.getCalendarToDate(date),
                        new TimeDAOImpl().getByID(time_id),
                        seconds);

                list.add(dataOfTime);
            }
        } catch (SQLException ex) {
            Error.showError("[DataOfTimeDAOImpl]Ошибка выборки данных", ex.getMessage());
        }

        return list;
    }

    @Override
    public int getNextId() {
        int id = 1;

        String sqlQuery = "SELECT max(id) as id FROM data_fo_time";

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
        //1. Создаем таблицу если она отсутствует
        String sqlQuery = "CREATE TABLE IF NOT EXISTS data_fo_time(id int  PRIMARY KEY,date_day date,time_id int,seconds int,CONSTRAINT time_id FOREIGN KEY (time_id) REFERENCES time(id))";

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);

        } catch (SQLException ex) {
            System.out.println("err.Ошибка создания таблицы таблицы DataOfTime");
            System.out.println("--" + ex.getMessage());
        }
    }
}
