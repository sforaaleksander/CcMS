package org.codecool.ccms.view;

import com.jakewharton.fliptables.FlipTable;
import org.codecool.ccms.inputProvider.IO;

import java.io.PrintStream;
import java.sql.ResultSet;

public class UI {
    private IO io;

    public UI() {
        this.io = new IO();
    }

    public void gatherEmptyInput(String message) {
        io.gatherEmptyInput(message);
    }

    public String gatherInput(String message) {
        return io.gatherInput(message);
    }

    public int gatherIntInput(String message, int rangeMin, int rangeMax) {
        return io.gatherIntInput(message, rangeMin, rangeMax);
    }

    public int gatherIntInput(String message) {
        return io.gatherIntInput(message);
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
