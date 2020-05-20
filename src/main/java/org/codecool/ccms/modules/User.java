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
        String[] stringList = {this.getFirstName(), this.getSurname(), this.getEmail(),
                this.getRole().toString(), this.getAttendance().toString()};
        return stringList;
    }

    static class Builder {
        private int id;
        private String firstName;
        private String surname;
        private String email;
        private String password;
        private Role role;
        private Attendance attendance;

        private Builder(){}

        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder role(Role role){
            this.role = role;
            return this;
        }
        public Builder attendance(Attendance attendance){
            this.attendance = attendance;
            return this;
        }
    }

    public static Builder builder(){
        return new Builder();
    }
}
