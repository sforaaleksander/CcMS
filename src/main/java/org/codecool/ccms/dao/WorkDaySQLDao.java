package org.codecool.ccms.dao;

import org.codecool.ccms.models.WorkDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkDaySQLDao extends SQLDao<WorkDay> implements IDao<WorkDay> {
    WorkDaySQLDao() {
        this.table = "Workday";
        this.columns = new String[]{"date"};
    }

    @Override
    protected String[] objectToArray(WorkDay workDay) {
        String date = workDay.toString();
        return new String[]{date};
    }

    @Override
    public void update(WorkDay workDay) throws SQLException {
        String[] values = objectToArray(workDay);
        updateRecord(values);
    }

    @Override
    public void remove(WorkDay workDay) throws SQLException {
        String[] values = objectToArray(workDay);
        removeRecord(values);
    }

    @Override
    public void insert(WorkDay workDay) {
        String[] values = objectToArray(workDay);
        insertRecord(values);
    }

    @Override
    public List<WorkDay> getObjects(String columnName, String columnValue) {

        List<WorkDay> workDays = new ArrayList<>();
        connect();
        String query = "SELECT * FROM WorkDay WHERE date = '" +columnValue+ "';";

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
            return workDays;
    }

    public List<WorkDay> getWorkDay(String value) {
        String columnName = "date";
        getObjects(columnName, value);

    }
}
