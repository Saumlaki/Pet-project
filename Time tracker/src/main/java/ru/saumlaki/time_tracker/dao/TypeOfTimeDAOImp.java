package ru.saumlaki.time_tracker.dao;

import ru.saumlaki.time_tracker.DialogMessengerElementForm;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.dao.interfaces.TypeOfTimeDAO;
import ru.saumlaki.time_tracker.entity.TypeOfTime;

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
            DialogMessengerElementForm.showError("[TypeOfTimeDAOImp]Ошибка вставки данных", ex.getMessage());
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
            DialogMessengerElementForm.showError("[TypeOfTimeDAOImp]Ошибка обновления данных", ex.getMessage());
        }
    }

    @Override
    public void remove(TypeOfTime object) {

        String sqlQuery = "DELETE FROM type_of_time  WHERE id =  " + object.getId();

        try (Statement stmt = TimeTracker.connection.createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            DialogMessengerElementForm.showError("[TypeOfTimeDAOImp]Ошибка удаления", ex.getMessage());
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
            DialogMessengerElementForm.showError("[TypeOfTimeDAOImp]Ошибка выборки данных по id", ex.getMessage());
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
            DialogMessengerElementForm.showError("[TypeOfTimeDAOImp]Ошибка выборки данных", ex.getMessage());
        }

        return list;
    }
}
