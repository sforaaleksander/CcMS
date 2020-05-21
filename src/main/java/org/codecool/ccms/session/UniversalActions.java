package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.modules.Role;

import java.util.ArrayList;
import java.util.List;

public class UniversalActions extends Actions {
    public UniversalActions(Session session) {
        super(session);
    }

    private void updateMyDetails(){
        String id = Integer.toString(this.getSession().getUser().getId());
        String name = this.getSession().getInputProvider().gatherInput("Provide new name.");
        this.getSession().getUserDao().updateUser(id, "first_name", name);
        String surname = this.getSession().getInputProvider().gatherInput("Provide new surname.");
        this.getSession().getUserDao().updateUser(id, "surname", surname);
        String email = this.getSession().getInputProvider().gatherInput("Provide new email.");
        this.getSession().getUserDao().updateUser(id, "email", email);
    }

    public void exit() {
        this.getSession().setRunning(false);
    }

    private void viewAllStudentsDetails(){
        String [] querryHeaders = {"ID", "NAME", "SURNAME", "EMAIL"};
        this.getSession().getView().setQuerryHeaders(querryHeaders);
        this.getSession().getView()
                .setQuerryList(this.getSession().getUserDao().getUserBy("roleId", Integer.toString(Role.STUDENT.getRoleId())));
    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        if (this.getSession().getUser() != null) options.add(new MenuOption("Update My Details.", this::updateMyDetails));
        if (this.getSession().getUser().getRole().equals(Role.MENTOR));
        options.add(new MenuOption("Exit CCMS", this::exit));
        return options;
    }

}
