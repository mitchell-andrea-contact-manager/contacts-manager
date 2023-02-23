package main;

import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private static final Input userInput = new Input();

    private static ArrayList<Contact> contacts;

    public static void main(String[] args) {

        getContactInfo();

        while(true) {

            printMenu();

            int userChoice = userInput.getInt();

            doChoice(userChoice);

            if(userChoice == 5){
                //add all new info to contacts.txt

                System.out.println("See ya!");
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
            System.out.println(contactList);

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
        }
    }


    private static void addNewContact() {
        //get contact info from user
        String contactName = userInput.getString("Enter the contacts name: ");
        String contactNumber = userInput.getString("Enter the contacts 10 digit phone number: ");
        //convert user input into string that can be plugged into file data

        contacts.add(new Contact(contactName,contactNumber));
        //add new string to list to add it at the end of the run
    }

    private static Contact searchContact() {
        System.out.println("Search: ");
        String userSearch = userInput.getString();

        for (Contact contact : contacts) {
            if(contact.getName().toLowerCase().startsWith(userSearch.toLowerCase())) {
                System.out.println(contact);
                return contact;
            }
        }
        return null;
    }
    private static void deleteContact() {
        System.out.print("What is the name of the contact you would like to delete? \n");
        //convert user input into string that can be plugged into file data
        Contact userContactRemoveSelect = searchContact();

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).equals(userContactRemoveSelect)) {
                boolean deleteConfirm = userInput.yesNo("Is \"" + contacts.get(i).getName() + "\" the contact you would like to delete?");
                if (deleteConfirm) {
                    contacts.remove(contacts.get(i));
                } else {
                    deleteContact();
                }
            }
        }
    }


}
