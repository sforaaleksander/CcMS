package org.codecool.ccms.modules;

import java.util.HashMap;
import java.util.Map;

public enum Module {
    PROGBASIC(1),
    JAVA(2),
    WEB(3),
    ADVANCED(4);

    private final int value;
    private static final Map<Integer, Module> map = new HashMap<>();

    Module(int value) {
        this.value = value;
    }

    static {
        for (Module module : Module.values()) {
            map.put(module.value, module);
        }
    }

    public static Module valueOf(int value) { return (Module) map.get(value); }

    public int getValue() {
        return value;
    }

}
