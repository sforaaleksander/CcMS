package org.codecool.ccms.modules;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    MANAGER(1), MENTOR(2), EMPLOYEE(3), STUDENT(4), GUEST(0);

    private final int roleId;
    private static final Map<Integer, Role> map = new HashMap<>();

    Role(int roleId) {
        this.roleId = roleId;
    }

    static {
        for (Role role : Role.values()) {
            map.put(role.roleId, role);
        }
    }

    public static Role valueOf(int roleId) {
        return (Role) map.get(roleId);
    }

    public int getRoleId() {
        return roleId;
    }
}
