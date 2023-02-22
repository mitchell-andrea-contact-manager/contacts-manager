package main;

import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ContactManager {
    private static Input userInput = new Input();

    private static Path contactsFile;

    private static List<String> contactStrings;

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
        contactsFile = Paths.get("src/main/java/data/contacts.txt");
        System.out.println(Files.exists(contactsFile));
        //create array list of contact objects
        try {
            contactStrings = Files.readAllLines(contactsFile);
            System.out.println(contactStrings);
        } catch (IOException e) {
            System.out.println("file read exception: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static void getAllFileContacts(){
        try {
            contactStrings = Files.readAllLines(contactsFile);
            System.out.println(contactStrings);
        } catch (IOException e) {
            System.out.println("file read exception: " + e.getMessage());
        }
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
            showContacts();
        }else if(choice == 2){
            addNewContact();
        }else if(choice == 3){
            searchContact();
        }else if(choice == 4){
            deleteContact();
        }else if(choice == 5){
            //add all new info to contacts.txt

            //exit
        }
    }


    private static void addNewContact() {
        //get contact info from user
        String contactName = userInput.getString("Enter the contacts name: ");
        Integer contactNumber = userInput.getInt("Enter the contacts 10 digit phone number: ");
        //convert user input into string that can be plugged into file data

        //add new string to list to add it at the end of the run
    }

    private static void searchContact() {
    }

    private static void deleteContact() {
    }



}
