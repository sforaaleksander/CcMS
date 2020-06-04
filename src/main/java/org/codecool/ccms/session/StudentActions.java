package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.models.Assignment;
import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.Student;

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
        assignment.setContent(answer);
        this.getSession().getUserCrossAssignmentSQLDao().update(assignment);
    }

    public void viewMyGrades(){
        List<Displayable> querryList = new ArrayList<>();
        this.getSession().getUserCrossAssignmentSQLDao().getGradesByStudent((Student)this.getSession().getUser()).stream().forEach(assi -> querryList.add(assi));
        this.getSession().getView().setQuerryList(querryList);
        this.getSession().getView().setQuerryHeaders(new String[]{"Id", "Name", "Description", "Module"});
    }
}
