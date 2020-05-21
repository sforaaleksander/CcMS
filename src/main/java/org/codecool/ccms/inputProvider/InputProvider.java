package org.codecool.ccms.inputProvider;


import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.Stack;



public class InputProvider {
    public Scanner scan;
    private final Stack<String> commands;

    public InputProvider(String[] args) {
        scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());
        commands = new Stack<String>();
        initializeStack(args);
    }

    private void initializeStack(String[] args){
        Stack midStack = new Stack();
        try {
            File file = new File(args[0]);
            Scanner scanFile = new Scanner(file);

            while (scanFile.hasNextLine()) {
                midStack.push(scanFile.nextLine());
            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (!midStack.isEmpty()){
            commands.push((String) midStack.pop());
        }
    }

    public Stack<String> getCommands() {
        return commands;
    }

    private String tryToGrabStringFromStack(){
        if (commands.isEmpty()){
            return scan.next();
        }
        String command = String.valueOf(commands.pop());
        return command;
    }

    public String gatherInput(String title) {
        System.out.println(title);
        boolean validInput = true;
        String userInput;
        do {
            if (!validInput) {
                System.out.println("Your input must contain at least one character. Enter again: ");
            }
            validInput = false;

            userInput = tryToGrabStringFromStack();

            if (!userInput.equals("")) {
                validInput = true;
            }
        } while (!validInput);
        return userInput;
    }

    public String gatherYesNoInput(String title) {
        System.out.println(title + " [Y/N]");
        boolean validInput = true;
        String userInput;
        do {
            if (!validInput) {
                System.out.println("Invalid input. Enter again: ");
            }
            validInput = false;
            userInput = tryToGrabStringFromStack().toUpperCase();
            if (userInput.equals("Y") || userInput.equals("N")) {
                validInput = true;
            }
        } while (!validInput);
        return userInput;
    }

    public void gatherEmptyInput(String message) {
        System.out.println(message);
        scan.next();
    }

    public int gatherIntInput(String message, int rangeMin, int rangeMax) {
        System.out.println(message);
        String userInput = "";
        boolean validInput = false;
        while (!validInput) {
            userInput = tryToGrabStringFromStack();
            validInput = isNumberInRange(userInput, rangeMin, rangeMax-1);
        }
        return Integer.parseInt(userInput);
    }

    public int gatherIntInput(String message) {
        System.out.println(message);
        String userInput = "";
        boolean validInput = false;
        while (!validInput) {
            userInput = tryToGrabStringFromStack();
            validInput = isInputInt(userInput);
        }
        return Integer.parseInt(userInput);
    }

    private boolean isInputInt(String userInput) {
        if (!userInput.equals("")) {
            if (userInput.matches("^[0-9]*$")) {
                return true;
            }
        }
        System.out.println("Invalid input, please try again: ");
        return false;
    }

    private boolean isNumberInRange(String userInput, int rangeMin, int rangeMax) {
        int userInt;
        if (!userInput.equals("")) {
            if (userInput.matches("^[0-9]*$")) {
                userInt = Integer.parseInt(userInput);
                return userInt >= rangeMin && userInt <= rangeMax;
            }
        }
        System.out.println("Invalid input, please try again: ");
        return false;
    }
}