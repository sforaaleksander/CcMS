package org.codecool.ccms.modules;

public abstract class User implements Displayable{
    private int id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private Attendance attendance;

    public User(int id, String firstName, String surname, String email, String password, Role role, Attendance attendance) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.attendance = attendance;
    }

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

    public Role getRole() {
        return role;
    }

    public String[] toStringList() {
        String[] stringList = {this.firstName, this.surname, this.email, this.attendance.toString()};
        return stringList;
    }
}
