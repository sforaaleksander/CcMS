package org.codecool.ccms.inputProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class InputProvider {
    public Scanner scan;
    private final Stack<String> commandsStack;

    public InputProvider(String[] args) {
        scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());
        commandsStack = new Stack<>();
        initializeStack(args);
    }

    private void initializeStack(String[] args){
        Iterator<String> commands = getCommandsFromFile(args).iterator();
        while (commands.hasNext()){
            commandsStack.push(commands.next());
        }
    }

    private List<String> getCommandsFromFile(String[] args){
        List<String> commands = new LinkedList<>();
        try {
            File file = new File(args[0]);
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNextLine()) {
                commands.add(0, scanFile.nextLine());
            }
            scanFile.close();
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException e) {
            e.getMessage();
        }
        return commands;
    }

    private String getInput(){
        if (commandsStack.isEmpty()) {
            return validateAgainstSQLinjection(scan.next());
        }
        else {
            return validateAgainstSQLinjection(String.valueOf(commandsStack.pop()));
        }
    }

    private String validateAgainstSQLinjection(String input){
        input.replaceAll("'", "");
        input.replaceAll("\"", "");
        input.replaceAll("%", "");
        input.replaceAll("\\?", "");
        return input;
    }

    private boolean isInputInt(String userInput) {
        if (userInput.matches("^[0-9]*$")) { return true; }
        else { return false; }
    }

    private boolean isNumberInRange(String userInput, int rangeMin, int rangeMax) {
        if (userInput.matches("^[0-9]*$")) {
            int userInt = Integer.parseInt(userInput);
            return userInt >= rangeMin && userInt <= rangeMax;
        }
        return false;
    }

    public String gatherInput(String message) {
        System.out.println(message);
        String userInput =getInput();
        while (userInput == ""){
            System.out.println("Your input must contain at least one character. Enter again: ");
            userInput = getInput();
        }
        return userInput;
    }

    public String gatherYesNoInput(String message) {
        System.out.println(message + " [Y/N]");
        String userInput = getInput();
        while (!(userInput.equals("Y") || userInput.equals("N") ))
            System.out.println("Invalid input. Choose one: [Y/N] ");
            userInput = gatherInput("").toUpperCase();
        return userInput;
    }

    public int gatherIntInput(String message, int rangeMin, int rangeMax) {
        System.out.println(message);
        String userInput = getInput();
        while (!isNumberInRange(userInput, rangeMin, rangeMax-1)) {
            System.out.println("Invalid input, please try again: ");
            userInput = gatherInput("");
        }
        return Integer.parseInt(userInput);
    }

    public int gatherIntInput(String message) {
        System.out.println(message);
        String userInput = getInput();
        while (!isInputInt(userInput)) {
            System.out.println("Provide numeric input, please.");
            userInput = gatherInput("");
        }
        return Integer.parseInt(userInput);
    }
}