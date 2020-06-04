package org.codecool.ccms.dao;

import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.WorkDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkDayDao extends SQLDao {
    @Override
    public void update(String id, String columnName, String value) {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void insert(String... values) {

    }

    @Override
    public List<Displayable> getObjects(String columnName, String columnValue) {
        return null;
    }

    public WorkDay getWorkDay(String value) {
        List<Displayable> workDays = new ArrayList<>();
        createStatement();
        String query = "SELECT * FROM WorkDay WHERE date = '" +value+ "';";

        WorkDay workDay = null;
        try {
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String stringDate = results.getString("date");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                formatter = formatter.withLocale(Locale.ENGLISH);
                LocalDate date = LocalDate.parse(stringDate, formatter);

                workDays.add(new WorkDay(date));

            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workDay;
    }
}
