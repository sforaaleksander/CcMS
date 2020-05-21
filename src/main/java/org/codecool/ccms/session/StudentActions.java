package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class StudentActions extends Actions {


    public StudentActions(Session session) {
        super(session);

    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption(0,"Update My Assignement.", this::updateMyAssignement));
        options.add(new MenuOption(0, "View my grades.", this::viewMyGrades));
        return options;
    }

    public void updateMyAssignement(){ }

    public void viewMyGrades(){
        this.getSession().getView().setQuerryList(this.getSession().getUserDao().getGradesByStudentId(this.getSession().getUser().getId()));
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Description", "Module Id", "Is Passed"});
    }

}
