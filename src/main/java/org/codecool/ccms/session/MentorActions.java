package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.modules.Displayable;
import org.codecool.ccms.modules.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        UserDao userDao = this.getSession().getUserDao();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        userDao.addWorkDay(date);
        int workDayId = userDao.getWorkDayIdByDate(date).getDayId();
        List<Displayable> students = userDao.getUserBy("roleId", "4");
        for (Displayable displayStudent : students) {
            Student student = (Student) displayStudent;
            this.getSession().getView().displayMessage(student.getFirstName() + " " + student.getSurname());
            String isPresent = this.getSession().getInputProvider().gatherYesNoInput("Is the student present?");
            userDao.addAttendance(student.getId());

        }
    }

    public void updateStudentAttendance(){

    }
}
