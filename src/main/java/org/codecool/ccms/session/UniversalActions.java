package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.Role;
import java.util.ArrayList;
import java.util.List;

public class UniversalActions extends Actions {
    public UniversalActions(Session session) {
        super(session);
    }

    private void updateMyDetails(){
        String name = this.getSession().getInputProvider().gatherInput("Provide new name.");
        this.getSession().getUser().setFirstName(name);
        String surname = this.getSession().getInputProvider().gatherInput("Provide new surname.");
        this.getSession().getUser().setSurname(surname);
        String email = this.getSession().getInputProvider().gatherInput("Provide new email.");
        this.getSession().getUser().setEmail(email);
        this.getSession().getUserDao().update(this.getSession().getUser());
    }

    public void exit() {
        this.getSession().setRunning(false);
    }

    private void viewAllStudentsDetails(){
        String [] querryHeaders = {"ID", "NAME", "SURNAME", "EMAIL"};
        this.getSession().getView().setQuerryHeaders(querryHeaders);
        List<Displayable> querryList = new ArrayList<>();
        this.getSession().getUserDao().getObjects("roleId", Integer.toString(Role.STUDENT.getRoleId())).stream().forEach(student -> querryList.add(student));
        this.getSession().getView().setQuerryList(querryList);

    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption("Exit CCMS", this::exit));
        if (this.getSession().getUser() == null){
            return options;
        }
        options.add(new MenuOption("Update My Details.", this::updateMyDetails));
        if ((this.getSession().getUser().getRole().equals(Role.MENTOR)) || (this.getSession().getUser().getRole().equals(Role.MANAGER)))  {
            options.add(new MenuOption("View all students details", this::viewAllStudentsDetails));
        }
        return options;
    }
}
