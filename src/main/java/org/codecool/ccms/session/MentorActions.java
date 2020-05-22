package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.dao.UserDao;
import org.codecool.ccms.modules.Assignment;
import org.codecool.ccms.modules.Displayable;
import org.codecool.ccms.modules.Student;
import org.codecool.ccms.modules.WorkDay;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MentorActions extends Actions {

    public MentorActions(Session session) {
        super(session);
    }

    @Override
    public List<MenuOption> returnActions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption("Add new assignment", this::addAssignment));
        options.add(new MenuOption("Check all students' attendance", this::checkAllStudentsAttendance));
        options.add(new MenuOption("Grade student's assignment", this::gradeAssignment));
        options.add(new MenuOption("Update student's attendance", this::updateStudentAttendance));
        return options;
    }

    public void addAssignment() {
        String name = this.getSession().getInputProvider().gatherInput("Assignment name: ");
        String description = this.getSession().getInputProvider().gatherInput("Assignment description: ");
        int moduleId = this.getSession().getInputProvider().gatherIntInput("Module no.: ", 0, 5);
        this.getSession().getUserDao().insertAssignment(name, description, moduleId);
        this.getSession().getView().displayMessage("Assignment added");
    }

    public void gradeAssignment() {
        String[] headers = new String[]{"Id", "Name", "Students Answer", "Module", "Is Passed"};
        List<Displayable> assignments = new ArrayList<>();
        this.getSession().getView().setQuerryHeaders(headers);
        this.getSession().getUserDao().getAssignments().stream().filter(e -> !((Assignment) e).getPassed()).forEach(assignments::add);
        this.getSession().getView().setQuerryList(assignments);
        this.getSession().getView().displayContent();
        int action = this.getSession().getInputProvider().gatherIntInput("Enter coresponding id of project you" +
                                                    " want mark as passed:", 1, assignments.size()+1);
        this.getSession().getUserDao().passAssignment(action);
    }

    public void checkAllStudentsAttendance() {
        UserDao userDao = this.getSession().getUserDao();
        LocalDate todayDate = LocalDate.now();
        String date = todayDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        WorkDay workDay = getPresentDayInstance(userDao, date);

        List<Displayable> students = userDao.getUserBy("roleId", "4");
        for (Displayable displayStudent : students) {
            Student student = (Student) displayStudent;
            this.getSession().getView().displayMessage(student.getFirstName() + " " + student.getSurname());
            String isPresent = this.getSession().getInputProvider().gatherYesNoInput("Is the student present?");
            if (isPresent.equals("Y")) {
                userDao.addAttendance(student.getId(), workDay);
            }
        }
    }

    private WorkDay getPresentDayInstance(UserDao userDao, String date) {
        if (userDao.getWorkDay(date) == null){
            userDao.addWorkDay(date);
        }
        return userDao.getWorkDay(date);
    }

    public void updateStudentAttendance(){
        UserDao userDao = this.getSession().getUserDao();
        LocalDate todayDate = LocalDate.now();
        String date = todayDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        WorkDay workDay = getPresentDayInstance(userDao, date);

        List<Displayable> students = userDao.getUserBy("roleId", "4");
        String[] headers = {"Student's ID", "Student's first name", "Student's surname"};
        this.getSession().getView().setQuerryHeaders(headers);
        this.getSession().getView().setQuerryList(students);
        this.getSession().getView().displayContent();
        int studentID = this.getSession().getInputProvider().gatherIntInput("Provide student's ID to mark as present ");
        String isPresent = this.getSession().getInputProvider().gatherYesNoInput("Is the student present?");

        if (isPresent.equals("Y")) {
            userDao.addAttendance(studentID, workDay);
            this.getSession().getView().displayMessage("Attendance added");
        } else {
            userDao.removeAttendance(studentID, workDay);
            this.getSession().getView().displayMessage("Attendance removed");
        }
    }
}

