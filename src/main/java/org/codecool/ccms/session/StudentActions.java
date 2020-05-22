package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.modules.Assignment;

import java.util.ArrayList;
import java.util.List;

public class StudentActions extends Actions {

    public StudentActions(Session session) {
        super(session);
    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption("Update My Assignement.", this::SubmitMyAssignement));
        options.add(new MenuOption("View my grades.", this::viewMyGrades));
        return options;
    }

    public void SubmitMyAssignement(){
        viewMyGrades();
        this.getSession().getView().displayContent();
        int range = this.getSession().getView().getQuerryListLenght();
        int assi = this.getSession().getInputProvider().gatherIntInput("Enter assgiment id:", 1, range+1)-1;
        Assignment assignment = (Assignment)this.getSession().getView().getQuerryList().get(assi);
        this.getSession().getView().displayMessage(assignment.getContent());
        String answer = this.getSession().getInputProvider().gatherInput("Enter your answer:");
        this.getSession().getUserDao().updatAssigment(assi, this.getSession().getUser().getId(), answer);
    }

    public void viewMyGrades(){
        this.getSession().getView().setQuerryList(this.getSession().getUserDao().getGradesByStudentId(this.getSession().getUser().getId()));
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Description", "Module"});
    }
}
