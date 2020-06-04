package org.codecool.ccms.session;

import org.codecool.ccms.controllers.MenuOption;
import org.codecool.ccms.dao.AttendanceSQLDao;
import org.codecool.ccms.dao.UserSQLDao;
import org.codecool.ccms.dao.WorkDaySQLDao;
import org.codecool.ccms.models.*;
import org.codecool.ccms.models.Module;

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
        Assignment assignment = new Assignment(0, name, description, Module.valueOf(moduleId), false);
        this.getSession().getAssignmentSQLDao().insert(assignment);
        this.getSession().getView().displayMessage("Assignment added");
    }

    public void gradeAssignment() {
        String[] headers = new String[]{"Id", "Students Answer", "Name"};
        List<Displayable> assignments = new ArrayList<>();
        this.getSession().getView().setQuerryHeaders(headers);
        this.getSession().getUserCrossAssignmentSQLDao().getAllAssignments().stream().filter(e -> !e.getPassed()).forEach(assignments::add);
        this.getSession().getView().setQuerryList(assignments);
        this.getSession().getView().displayContent();
        int action = this.getSession().getInputProvider().gatherIntInput("Enter coresponding id of project you" +
                                                    " want mark as passed:", 1, assignments.size()+1);
        Assignment assignment = (Assignment)this.getSession().getView().getQuerryList().get(action);
        assignment.setPassed(true);
        this.getSession().getAssignmentSQLDao().update(assignment);
    }

    public void checkAllStudentsAttendance() {
        WorkDaySQLDao workDaySQLDao = this.getSession().getWorkDaySQLDao();
        LocalDate todayDate = LocalDate.now();
        String date = todayDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        WorkDay workDay = getPresentDayInstance(workDaySQLDao, todayDate);

        List<Displayable> students = new ArrayList<>();
        students.addAll(this.getSession().getUserDao().getObjects("roleId", "4"));
        for (Displayable displayStudent : students) {
            Student student = (Student) displayStudent;
            this.getSession().getView().displayMessage(student.getFirstName() + " " + student.getSurname());
            String isPresent = this.getSession().getInputProvider().gatherYesNoInput("Is the student present?");
            if (isPresent.equals("Y")) {
                // TODO: ADD ATTENDANCE
                // this.getSession().getAttendanceSQLDao().update(new Attendance());
            }
        }
    }

    private WorkDay getPresentDayInstance(WorkDaySQLDao workDaySQLDao, LocalDate date) {
        if (workDaySQLDao.getWorkDay(date.toString()) == null){
            workDaySQLDao.insert(new WorkDay(date));
        }
        return workDaySQLDao.getWorkDay(date.toString()).get(0);
    }

    public void updateStudentAttendance(){
        UserSQLDao userDao = this.getSession().getUserDao();
        LocalDate todayDate = LocalDate.now();
        String date = todayDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        WorkDay workDay = getPresentDayInstance(this.getSession().getWorkDaySQLDao(), todayDate);
        List<Displayable> students = new ArrayList<>();
        students.addAll(userDao.getObjects("roleId", "4"));
        String[] headers = {"Student's ID", "Student's first name", "Student's surname"};
        this.getSession().getView().setQuerryHeaders(headers);
        this.getSession().getView().setQuerryList(students);
        this.getSession().getView().displayContent();
        int studentID = this.getSession().getInputProvider().gatherIntInput("Provide student's ID to mark as present ");
        String isPresent = this.getSession().getInputProvider().gatherYesNoInput("Is the student present?");

        if (isPresent.equals("Y")) {
            // TODO: ADD ATTENDANCE
            // this.getSession().getAttendanceSQLDao().update(new Attendance());
            this.getSession().getView().displayMessage("Attendance added");
        } else {
            // TODO: ReMOve ATTENDANCE
            // this.getSession().getAttendanceSQLDao().update(new Attendance());
            this.getSession().getView().displayMessage("Attendance removed");
        }
    }
}

