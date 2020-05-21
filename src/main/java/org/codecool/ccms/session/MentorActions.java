package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;

import java.util.List;

public class MentorActions extends Actions {

    MentorActions(Session session) {
        super(session);
    }
    @Override
    public List<MenuOption> returnActions() {
        return null;
    }

    public void addAssignment(){
        String name = this.getSession().getInputProvider().gatherInput("Assignment name: ");
        String description = this.getSession().getInputProvider().gatherInput("Assignment description: ");
        this.getSession().getUserDao().insertAssignment(name, description);
        this.getSession().getView().displayMessage("Assignment added");
    }

    public void gradeAssignment() {

    }

    public void checkAllStudentsAttendance() {

    }

    public void updateStudentAttendance(){

    }
}
