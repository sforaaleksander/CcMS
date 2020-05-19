package org.codecool.ccms.view;

import com.jakewharton.fliptables.FlipTable;
import org.codecool.ccms.inputProvider.InputProvider;

import java.sql.ResultSet;

public class UI {
    private InputProvider inputProvider;

    public UI() {
        this.inputProvider = new InputProvider();
    }

    public void gatherEmptyInput(String message) {
        inputProvider.gatherEmptyInput(message);
    }

    public String gatherInput(String message) {
        return inputProvider.gatherInput(message);
    }

    public int gatherIntInput(String message, int rangeMin, int rangeMax) {
        return inputProvider.gatherIntInput(message, rangeMin, rangeMax);
    }

    public int gatherIntInput(String message) {
        return inputProvider.gatherIntInput(message);
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public void printTableFromDB(ResultSet results) {
        //TODO
    }

    public void welcomeMessage() {
        System.out.println("Welcome to Codecool Management System");
    }



    public void displayMenu(String[][] data) {
        String[] headers = {"no", "action"};
        System.out.println(FlipTable.of(headers, data));
    }
}
