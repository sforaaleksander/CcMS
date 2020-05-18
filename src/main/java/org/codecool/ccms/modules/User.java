package org.codecool.ccms.modules;

public abstract class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Attendance attendance;

    User(){}

    public String getName() {
        return name;
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
}
