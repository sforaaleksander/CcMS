package org.codecool.ccms.session;
import org.codecool.ccms.controllers.MenuController;
import org.codecool.ccms.dao.AssignmentSQLDao;
import org.codecool.ccms.dao.AttendanceSQLDao;
import org.codecool.ccms.dao.UserCrossAssignmentSQLDao;
import org.codecool.ccms.dao.UserSQLDao;
import org.codecool.ccms.dao.WorkDaySQLDao;
import org.codecool.ccms.inputProvider.InputProvider;
import org.codecool.ccms.models.User;
import org.codecool.ccms.view.View;
import java.util.ArrayList;

public class Session {
    private static Session instance;

    private final AssignmentSQLDao assignmentSQLDao;
    private final AttendanceSQLDao attendanceSQLDao;
    private final UserSQLDao userSQLDao;
    private final UserCrossAssignmentSQLDao userCrossAssignmentSQLDao;
    private final WorkDaySQLDao workDaySQLDao;
    private User user;
    private final InputProvider inputProvider;
    private final View view;
    private final MenuController menuController;
    private Boolean isRunning;

    private Session(String[] args) {
        this.isRunning = true;
        this.assignmentSQLDao = new AssignmentSQLDao();
        this.attendanceSQLDao = new AttendanceSQLDao();
        this.userCrossAssignmentSQLDao = new UserCrossAssignmentSQLDao();
        this.userSQLDao = new UserSQLDao();
        this.workDaySQLDao = new WorkDaySQLDao();
        this.inputProvider = new InputProvider(args);
        this.view = new View();
        this.menuController = new MenuController(this);
        this.view.setCommandList(new ArrayList<>(menuController.getActionMap().values()));
    }

    public static Session getSession(String[] args){
        if (instance == null) {
            instance = new Session(args);
            instance.view.displayMessage("Welcome to Codecool Management System, new session has been created.");
        }
        return instance;
    }

    public void nextFrame(){
        this.view.displayContent();
        int action = inputProvider.gatherIntInput("Choose action:", 0, menuController.getActionMap().size());
        menuController.getActionMap().get(action).getAction().run();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRunning(Boolean running) { isRunning = running; }

    public Boolean getIsRunning() {
        return isRunning;
    }

    public UserSQLDao getUserDao() {
        return userSQLDao;
    }

    public UserCrossAssignmentSQLDao getUserCrossAssignmentSQLDao() {
        return userCrossAssignmentSQLDao;
    }

    public InputProvider getInputProvider() {
        return inputProvider;
    }

    public View getView() {
        return view;
    }

    public MenuController getMenuController() {
        return menuController;
    }
}
