package org.codecool.ccms.modules;

public abstract class User {
    private int id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private Attendance attendance;
    private int roleId;

    User(int id, String name, String surname, String email, String password, int roleId){}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public int getRoleId() {
        return roleId;
    }
}
