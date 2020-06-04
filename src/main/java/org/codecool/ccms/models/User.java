package org.codecool.ccms.models;

public abstract class User implements Displayable{
    private final int id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private Attendance attendance;
    private final byte[] salt;

    public User(int id, String firstName, String surname, String email, String password, Role role, Attendance attendance, byte[] salt) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.attendance = attendance;
        this.salt = salt;
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

    public byte[] getSalt() {return this.salt;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public String[] toStringList() {
        String[] stringList = {this.getFirstName(), this.getSurname(), this.getEmail(),
                this.getRole().toString(), this.getAttendance().toString()};
        return stringList;
    }
}
