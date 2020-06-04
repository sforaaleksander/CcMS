package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.models.Displayable;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActions extends Actions {

    public EmployeeActions(Session session) {
        super(session);
    }

    private void viewAnyStudentContact(){
        String surname = this.getSession().getInputProvider().gatherInput("Please provide students surname.");
        List<Displayable> students = new ArrayList<>();
        students.addAll(this.getSession().getUserDao().getObjects("surname", surname));
        this.getSession().getView().setQuerryList(students);
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Surname", "Email"});
    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption( "View any student contact", this::viewAnyStudentContact));
        return options;
    }
}
