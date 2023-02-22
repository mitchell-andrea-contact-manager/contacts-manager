package main;

import util.Input;

import java.util.Scanner;

public class ContactManager {

    private static Input userInput = new Input();

    public static void main(String[] args) {

        while(true) {

            showContacts();

            printMenu();

            int userChoice = userInput.getInt();

            doChoice(userChoice);

            if(userChoice == 5){
                System.out.println("See ya!");
                break;
            }
        }

    }

    //display contacts
    private static void showContacts() {
    }

    //print the menu
    private static void printMenu() {
        System.out.println("""
                
                1. View contacts
                2. Add a new contact
                3. Search a contact by name
                4. Delete an existing contact
                5. Exit
                
                Enter an option (1, 2, 3, 4, or 5):
                """);
    }

    //do users choice
    private static void doChoice(int choice) {
        if(choice == 1){
            //show contacts
        }else if(choice == 2){
            //add a new contact
        }
    }
}
