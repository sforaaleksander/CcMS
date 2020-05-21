package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.modules.Displayable;
import org.codecool.ccms.modules.Student;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MentorActions extends Actions {

    MentorActions(Session session) {
        super(session);
    }
    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption("Add new assignment", this::addAssignment));
        options.add(new MenuOption("Check all students' attendance", this::checkAllStudentsAttendance));
        options.add(new MenuOption("Grade student's assignment", this::gradeAssignment));
        options.add(new MenuOption("Update student's assignment", this::updateStudentAttendance));
        return options;
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
        LocalDate todayDate = LocalDate.now();
        String date = todayDate.toString();
        userDao.addWorkDay(date);
        int workDayId = userDao.getWorkDayIdByDate(date).getDayId();
        List<Displayable> students = userDao.getUserBy("roleId", "4");
        for (Displayable displayStudent : students) {
            Student student = (Student) displayStudent;
            this.getSession().getView().displayMessage(student.getFirstName() + " " + student.getSurname());
            String isPresent = this.getSession().getInputProvider().gatherYesNoInput("Is the student present?");
            if (isPresent.equals("Y")) {
                userDao.addAttendance(student.getId(), workDayId);
            }
        }
    }

    public void updateStudentAttendance(){

    }
}
