package org.codecool.ccms.modules;

public abstract class User {
    private int id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private Attendance attendance;

    User(int id, String firstName, String surname, String email, String password){}

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
}
