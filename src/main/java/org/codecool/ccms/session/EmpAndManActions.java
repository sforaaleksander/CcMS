package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;

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

    public void updateMentorDetails(){
        String id = this.getSession().getInputProvider().gatherInput("Enter mentor's ID you would like to edit: ");
        System.out.println("What you want to update:\n1. Name\n2. Surname\n3. Email\n4. All");
        String choice = this.getSession().getInputProvider().gatherInput("Choice: ");
        switch (choice) {
            case "1":
                String name = this.getSession().getInputProvider().gatherInput("Provide new name.");
                this.getSession().getUserDao().updateUser(id, "first_name", name);
                break;
            case "2":
                String surname = this.getSession().getInputProvider().gatherInput("Provide new surname.");
                this.getSession().getUserDao().updateUser(id, "surname", surname);
                break;
            case "3":
                String email = this.getSession().getInputProvider().gatherInput("Provide new email.");
                this.getSession().getUserDao().updateUser(id, "email", email);
                break;
            case "4":
                String name1 = this.getSession().getInputProvider().gatherInput("Provide new name.");
                this.getSession().getUserDao().updateUser(id, "first_name", name1);
                String surname1 = this.getSession().getInputProvider().gatherInput("Provide new surname.");
                this.getSession().getUserDao().updateUser(id, "surname", surname1);
                String email1 = this.getSession().getInputProvider().gatherInput("Provide new email.");
                this.getSession().getUserDao().updateUser(id, "email", email1);
                break;
        }
    }

    public void viewAllStudents(){
        this.getSession().getView().setQuerryList(this.getSession().getUserDao().viewStudentsContact());
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Surname", "Email"});
    }

    public void addStudent(){
        String name = this.getSession().getInputProvider().gatherInput("Enter name: ");
        String surname = this.getSession().getInputProvider().gatherInput("Enter surname: ");
        String email = this.getSession().getInputProvider().gatherInput("Enter email: ");
        String password = this.getSession().getInputProvider().gatherInput("Enter password: ");
        String roleId = "4";
        this.getSession().getUserDao().AddUser(name, surname, email, password, roleId);
        viewAllStudents();
    }

    public void removeStudent(){

    }
}
