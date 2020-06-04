package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.models.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerActions extends Actions {

    public ManagerActions(Session session) {
        super(session);
    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption("View all mentors.", this::viewAllMentors));
        options.add(new MenuOption( "Remove mentor", this::removeMentor));
        options.add(new MenuOption( "Add mentor.", this::addMentor));
        return options;
    }

    public void viewAllMentors() {
        List<Displayable> querryList = new ArrayList<>();
        querryList.addAll(this.getSession().getUserDao().getObjects("id", Integer.toString(Role.MENTOR.getRoleId())));
        this.getSession().getView().setQuerryList(querryList);
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Surname", "Email"});
    }

    public void removeMentor() {
        viewAllMentors();
        this.getSession().getView().displayContent();
        int id = this.getSession().getInputProvider().gatherIntInput("Enter mentor's ID: ");
        User mentor = this.getSession().getUserDao().getUserById("id");
        if (mentor.getRole() == Role.MENTOR){
            this.getSession().getUserDao().remove(mentor);
        }
        viewAllMentors();
    }

    // TODO: create salt hashmentors password
    // id, name, surname, email, password, role, attendance, salt, income

    public void addMentor() {
        String name = this.getSession().getInputProvider().gatherInput("Enter name: ");
        String surname = this.getSession().getInputProvider().gatherInput("Enter surname: ");
        String email = this.getSession().getInputProvider().gatherInput("Enter email: ");
        String password = this.getSession().getInputProvider().gatherInput("Enter password: ");
        this.getSession().getUserDao().insert(new UserFactory().makeUser(0, name, surname, email, password, Role.MENTOR, new byte[]{}));
        viewAllMentors();
    }
}
