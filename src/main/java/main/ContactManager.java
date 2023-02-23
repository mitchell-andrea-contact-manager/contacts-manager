package main;

import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class ContactManager {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static Input userInput = new Input();

    private static ArrayList<Contact> contacts;

    public static void main(String[] args) {

        getContactInfo();

        while(true) {

            printMenu();

//            System.out.println(ANSI_PURPLE);
            int userChoice = userInput.getInt();

            doChoice(userChoice);

            if(userChoice == 5){
                //add all new info to contacts.txt

                System.out.println(ANSI_PURPLE + "See ya!" + ANSI_RESET);
                writeContactInfo();
                break;
            }
        }

    }

    //display contacts
    private static void showContacts() {
//        System.out.println(contacts);
        System.out.printf("""
        Name | Phone number
        %s
        """,contacts);
    }

    private static void getContactInfo() {
        Path contactsPath = Paths.get("src/main/java/data/contacts.txt");
        try {
            List<String> contactList = Files.readAllLines(contactsPath);
            ArrayList<Contact> contactArrayList = new ArrayList<>();
//            System.out.println(contactList);

            for (String s : contactList) {
                contactArrayList.add(Contact.createFromFileString(s));
            }
            contacts = contactArrayList;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void writeContactInfo() {
        Path contactsPath = Paths.get("src/main/java/data/contacts.txt");

        try {
            List<String> contactList = new ArrayList<>();
            for (Contact contact : contacts) {
                contactList.add(contact.toString());
            }
            Files.write(contactsPath, contactList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //print the menu
    private static void printMenu() {
        System.out.println(ANSI_CYAN + """
                
                1. View contacts
                2. Add a new contact
                3. Search a contact by name
                4. Delete an existing contact
                5. Exit
                
                Enter an option (1, 2, 3, 4, or 5):
                """ + ANSI_RESET);
    }

    //display contacts
    private static void viewContacts() {
        System.out.println( """
                Name | Phone number
                ------------------- """ );
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    //do users choice
    private static void doChoice(int choice) {
        if(choice == 1){
            viewContacts();
        }else if(choice == 2){
            addNewContact();
        }else if(choice == 3){
            searchContact();
        }else if(choice == 4){
            deleteContact();
        }
    }


    private static void addNewContact() {
        //get contact info from user
        String contactName = userInput.getString(ANSI_CYAN + "Enter the contacts name: ");
        System.out.println();
        String contactNumber = userInput.getString(ANSI_CYAN + "Enter the contacts 10 digit phone number: ");
        System.out.println();
        //convert user input into string that can be plugged into file data

        contacts.add(new Contact(contactName,contactNumber));

        System.out.printf(ANSI_PURPLE+ "%s has been added to your contacts.",contactName);
        System.out.println();
        //add new string to list to add it at the end of the run
//        System.out.println(contacts.toString());
//        contactList.add(contact.toString());
    }

    private static Contact searchContact() {
        System.out.println(ANSI_PURPLE+"Search: " + ANSI_RESET);
        String userSearch = userInput.getString();
        System.out.println();
        for (Contact contact : contacts) {
            if(contact.getName().toLowerCase().startsWith(userSearch.toLowerCase())) {
                System.out.println(contact);
                System.out.println();
                boolean searchAgain = userInput.yesNo(ANSI_CYAN+"Would you like to search again?" +ANSI_RESET);
                System.out.println();
                if(searchAgain){
                    searchContact();
                }
                return contact;
            }
        }
        return null;
    }

    private static void deleteContact() {
        System.out.print(ANSI_CYAN+"What is the name of the contact you would like to delete? \n" + ANSI_RESET);
        //convert user input into string that can be plugged into file data
        Contact userContactRemoveSelect = searchContact();

        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).equals(userContactRemoveSelect)){
                boolean deleteConfirm = userInput.yesNo(ANSI_RED + "Is \"" + contacts.get(i).getName() + "\" the contact you would like to delete?" +ANSI_RESET);
                System.out.println();
                if(deleteConfirm){
                    System.out.printf( ANSI_PURPLE+ "%s has been removed from your contacts.", contacts.get(i).getName());
                    contacts.remove(contacts.get(i));
                    System.out.println();
                } else {
                    deleteContact();
                }
            }
        }
    }

}
