package org.codecool.ccms.dao;

import org.codecool.ccms.models.Displayable;
import org.codecool.ccms.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserSQLDao extends SQLDao implements IDao<User> {

    UserSQLDao() {
        this.table = "User";
        this.columns = new String[]{"id", "first_name", "surname", "email", "passwordHash", "passwordSalt", "roleId"};
    }

    private String[] userToArray(User user){
        String id = Integer.toString(user.getId());
        String firstName = user.getFirstName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String passwordHash = user.getPassword();  // change password into hash
        String passwordSalt = user.getSalt().toString();
        String role = user.getRole().toString();
        return new String[]{id, firstName, surname, email, passwordHash, passwordSalt, role};
    }

    @Override
    public void update(User user) throws SQLException {
        updateRecord(userToArray(user));
    }

    @Override
    public void remove(User user) throws SQLException {
        executeRemove(Integer.toString(user.getId()));
    }

    @Override
    public void insert(User user) {
        updateRecord(userToArray(user));
    }

    @Override
    public List<Displayable> getObjects(String columnName, String columnValue) {
        // TODO: select using sqldao
        return null;
    }
}


