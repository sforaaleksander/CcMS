package org.codecool.ccms.dao;

import org.codecool.ccms.models.Attendance;

import java.util.List;

public class AttendanceSQLDao extends SQLDao<Attendance> implements IDao<Attendance>  {

    public AttendanceSQLDao() {
        this.table = "Attendance";
        this.columns = new String[]{"id", "userId", "workDayId"};
    }


    @Override
    public void update(Attendance attendance) {
        String[] values = objectToArray(attendance);
        updateRecord(values);
    }

    @Override
    public void remove(Attendance attendance) {
        String id = attendance.getId();
        removeRecord(id);
    }

    @Override
    public void insert(Attendance attendance) {
        String[] values = objectToArray(attendance);
        insertRecord(values);
    }

    @Override
    public List<Attendance> getObjects(String columnName, String columnValue) {
        return null;
    }

    @Override
    protected String[] objectToArray(Attendance attendance) {
        String id = attendance.getId();
        String date =  attendance.getDate().toString();
        String firstName = attendance.getFirstName();
        String surname = attendance.getSurName();
        String role = attendance.getRole().toString();
        return new String[]{id, date, firstName, surname, role};
    }
}
