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
    private static Input userInput = new Input();

    private static ArrayList<Contact> contacts;

    public static void main(String[] args) {

        contactInfo();

        while(true) {

            printMenu();

            int userChoice = userInput.getInt();

            doChoice(userChoice);

            if(userChoice == 5){
                //add all new info to contacts.txt

                System.out.println("See ya!");
                break;
            }
        }

    }

    //display contacts
    private static void showContacts() {
        System.out.printf("""
                Name | Phone number
                %s
                """,contacts);
//        System.out.println(contacts);
    }

    private static void contactInfo() {
//        ArrayList <Contact> contacts = new ArrayList<>();
//
//        Contact contact1 = new Contact("Andrea", "1234567890");
//        Contact contact2 = new Contact("Emi", "1234567890");
//        Contact contact3 = new Contact("Micah", "1234567890");
//        Contact contact4 = new Contact("Jacob", "1234567890");
//
//        contacts.add(contact1);
//        contacts.add(contact2);
//        contacts.add(contact3);
//        contacts.add(contact4);
//
//        return contacts;

        Path groceriesPath = Paths.get("src/main/java/data/contacts.txt");
        try {
            List<String> contactList = Files.readAllLines(groceriesPath);
            System.out.println(contactList);

            for (String s : contactList) {
                contacts.add(Contact.createFromFileString(s));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
//        System.out.println(contacts.toString());
//        contactList.add(contact.toString());
    }

    private static Contact searchContact() {
        System.out.println("Search: ");
        String userSearch = userInput.getString();

        for (Contact contact : contacts) {
            if(contact.getName().toLowerCase().startsWith(userSearch.toLowerCase())) {
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
            if(contacts.get(i).equals(userContactRemoveSelect)){
                boolean deleteConfirm = userInput.yesNo("Is \"" + contacts.get(i).getName() + "\" the contact you would like to delete?");
                if(deleteConfirm){
                    contacts.remove(contacts.get(i));
                } else {
                    deleteContact();
                }
            }
        }
    }

}
