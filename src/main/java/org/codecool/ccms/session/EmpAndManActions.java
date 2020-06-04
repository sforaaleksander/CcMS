package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.models.Mentor;
import org.codecool.ccms.models.Module;
import org.codecool.ccms.models.Role;
import org.codecool.ccms.models.Student;

import java.util.ArrayList;
import java.util.List;

public class EmpAndManActions extends Actions {

    public EmpAndManActions(Session session) {
        super(session);
    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption("Update mentor details.", this::updateMentorDetails));
        options.add(new MenuOption( "View all students.", this::viewAllStudents));
        options.add(new MenuOption( "Add student", this::addStudent));
        options.add(new MenuOption( "Remove student", this::removeStudent));
        return options;
    }

    public void updateMentorDetails() {
        Mentor mentor;
        String name;
        String surname;
        String email;
        ManagerActions ma = new ManagerActions(this.getSession());
        ma.viewAllMentors();
        this.getSession().getView().displayContent();
        String id = this.getSession().getInputProvider().gatherInput("Enter mentor's ID you would like to edit: ");
        System.out.println("What you want to update:\n1. Name\n2. Surname\n3. Email\n4. All");
        String choice = this.getSession().getInputProvider().gatherInput("Choice: ");
        switch (choice) {
            case "1":
                name = this.getSession().getInputProvider().gatherInput("Provide new name.");
                mentor = (Mentor) this.getSession().getUserDao().getUserById(id);
                mentor.setFirstName(name);
                this.getSession().getUserDao().update(mentor);
                break;
            case "2":
                surname = this.getSession().getInputProvider().gatherInput("Provide new surname.");
                mentor = (Mentor) this.getSession().getUserDao().getUserById(id);
                mentor.setFirstName(surname);
                this.getSession().getUserDao().update(mentor);
                break;
            case "3":
                email = this.getSession().getInputProvider().gatherInput("Provide new email.");
                mentor = (Mentor) this.getSession().getUserDao().getUserById(id);
                mentor.setFirstName(email);
                this.getSession().getUserDao().update(mentor);
                break;
            case "4":
                name = this.getSession().getInputProvider().gatherInput("Provide new name.");
                mentor = (Mentor) this.getSession().getUserDao().getUserById(id);
                mentor.setFirstName(name);
                this.getSession().getUserDao().update(mentor);
                surname = this.getSession().getInputProvider().gatherInput("Provide new surname.");
                mentor = (Mentor) this.getSession().getUserDao().getUserById(id);
                mentor.setFirstName(surname);
                this.getSession().getUserDao().update(mentor);
                email = this.getSession().getInputProvider().gatherInput("Provide new email.");
                mentor = (Mentor) this.getSession().getUserDao().getUserById(id);
                mentor.setFirstName(email);
                this.getSession().getUserDao().update(mentor);
                break;
        }
        ma.viewAllMentors();
    }

    public void viewAllStudents() {
        this.getSession().getView().setQuerryList(this.getSession().getUserDao().getUsersByRoleId(String.valueOf(Role.STUDENT.getRoleId())));
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Surname", "Email"});
    }

    public void addStudent() {
        String name = this.getSession().getInputProvider().gatherInput("Enter name: ");
        String surname = this.getSession().getInputProvider().gatherInput("Enter surname: ");
        String email = this.getSession().getInputProvider().gatherInput("Enter email: ");
        String password = this.getSession().getInputProvider().gatherInput("Enter password: ");
        Role role = Role.STUDENT;
        Module module = Module.PROGBASIC;
        Student student = new Student(0, name, surname, email, password, role, new byte[]{}, module);
        this.getSession().getUserDao().insert(student);
        viewAllStudents();
    }

    public void removeStudent() {
        viewAllStudents();
        this.getSession().getView().displayContent();
        String id = this.getSession().getInputProvider().gatherInput("Enter student's ID: ");
        Student student = (Student) this.getSession().getUserDao().getUserById(id);
        this.getSession().getUserDao().remove(student);
        viewAllStudents();
    }
}
