package org.codecool.ccms.controllers;

public class MenuOption {
    private String optionName;
    private Runnable runnable;

    public MenuOption(String optionName, Runnable runnable) {
        this.optionName = optionName;
        this.runnable = runnable;
    }

    public String getOptionName() {
        return optionName;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
