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
    public void update(WorkDay workDay){
        String[] values = objectToArray(workDay);
        updateRecord(values);
    }

    @Override
    public void remove(WorkDay workDay){
        String id = workDay.toString();
        removeRecord(id);
    }


    @Override
    public void insert(WorkDay workDay) {
        String[] values = objectToArray(workDay);
        insertRecord(values);
    }

    @Override
    public List<WorkDay> getObjects(String columnName, String columnValue) {

        List<WorkDay> workDays = new ArrayList<>();
        ResultSet resultSet = getRecords(columnName, columnValue);

        WorkDay workDay = null;
            try {
            while (resultSet.next()) {
                String stringDate = resultSet.getString("date");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                formatter = formatter.withLocale(Locale.ENGLISH);
                LocalDate date = LocalDate.parse(stringDate, formatter);

                workDays.add(new WorkDay(date));

            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return workDays;
    }

    public List<WorkDay> getWorkDay(String value) {
        String columnName = "date";
        return getObjects(columnName, value);
    }
}
