package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActions extends Actions {


    public EmployeeActions(Session session) {
        super(session);
    }

    private void viewAnyStudentContact(){
        String surname = this.getSession().getInputProvider().gatherInput("Please provide students surname.");
        this.getSession().getView().setQuerryList(this.getSession().getUserDao().getStudentByName(surname));
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Surname", "Email"});
    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption( "View any student contact", this::viewAnyStudentContact));
        return options;
    }
}
