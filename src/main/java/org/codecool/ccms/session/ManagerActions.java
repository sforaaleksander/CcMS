package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.models.Role;
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
        this.getSession().getView().setQuerryList(this.getSession().getUserDao().viewUsersByRoleId(Role.MENTOR.getRoleId()));
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Surname", "Email"});
    }

    public void removeMentor() {
        viewAllMentors();
        this.getSession().getView().displayContent();
        int id = this.getSession().getInputProvider().gatherIntInput("Enter mentor's ID: ");
        this.getSession().getUserDao().removeUser(id, Role.MENTOR.getRoleId());
        viewAllMentors();
    }

    public void addMentor() {
        String name = this.getSession().getInputProvider().gatherInput("Enter name: ");
        String surname = this.getSession().getInputProvider().gatherInput("Enter surname: ");
        String email = this.getSession().getInputProvider().gatherInput("Enter email: ");
        String password = this.getSession().getInputProvider().gatherInput("Enter password: ");
        String roleId = String.valueOf(Role.MENTOR.getRoleId());
        this.getSession().getUserDao().AddUser(name, surname, email, password, roleId);
        viewAllMentors();
    }
}
