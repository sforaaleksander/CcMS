package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;

import java.util.ArrayList;
import java.util.List;

public class ManagerActions extends Actions {

    public ManagerActions(Session session) {
        super(session);
    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption(0,"View all mentors.", this::viewAllMentors));
        options.add(new MenuOption(0, "Remove mentor", this::removeMentor));
        options.add(new MenuOption(0, "Add mentor.", this::addMentor));
        return options;
    }

    public void viewAllMentors(){
        this.getSession().getView().setQuerryList(this.getSession().getUserDao().viewAllMentors());
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Surname", "Email"});
    }

    public void removeMentor(){
        viewAllMentors();
        int id = this.getSession().getInputProvider().gatherIntInput("Enter mentor's ID: ");
        this.getSession().getUserDao().removeMentor(id);
    }

    public void addMentor(){

    }
}
