package org.codecool.ccms.controllers;

import org.codecool.ccms.modules.Displayable;

public class MenuOption implements Displayable {

    private int id;
    private String name;
    private Runnable action;

    public MenuOption(int id, String name, Runnable action){
        this.id = id;
        this.name = name;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public Runnable getAction() {
        return action;
    }

    @Override
    public String[] toStringList() {
        return new String[]{Integer.toString(this.getId()), this.getName()};
    }
}
