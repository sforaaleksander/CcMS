package org.codecool.ccms.modules;

public enum Role {
    MENEGER(1), MENTOR(2), EMPLOYEE(3), STUDENT(4);

    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
