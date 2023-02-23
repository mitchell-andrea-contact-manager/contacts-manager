package main;

import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static final Input userInput = new Input();

    private static ArrayList<Contact> contacts;

    public static void main(String[] args) {

        getContactInfo();

        while (true) {

            printMenu();

            int userChoice = userInput.getInt();

            doChoice(userChoice);

            if (userChoice == 5) {

                System.out.println(ANSI_PURPLE + "See ya!" + ANSI_RESET);
                writeContactInfo();

                break;
            }
        }

    }

    private static void getContactInfo() {

        Path contactsPath = Paths.get("src/main/java/data/contacts.txt");

        try {
            List<String> contactList = Files.readAllLines(contactsPath);
            ArrayList<Contact> contactArrayList = new ArrayList<>();

            for (String s : contactList) {
                contactArrayList.add(Contact.createFromFileString(s));
            }
            contacts = contactArrayList;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

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

    private static void doChoice(int choice) {
        if (choice == 1) {
            viewContacts();
        } else if (choice == 2) {
            addNewContact();
        } else if (choice == 3) {
            searchContact();
        } else if (choice == 4) {
            deleteContact();
        }
    }

    private static void viewContacts() {
        System.out.println("""
                Name | Phone number
                ------------------- """);
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private static void addNewContact() {
        String contactName = userInput.getString(ANSI_CYAN + "Enter the contacts name: ");
        System.out.println();
        String contactNumber = userInput.getString(ANSI_CYAN + "Enter the contacts 10 digit phone number: ");
        System.out.println();

        contacts.add(new Contact(contactName, contactNumber));

        System.out.printf(ANSI_PURPLE + "%s has been added to your contacts.", contactName);
        System.out.println();
    }

    private static Contact searchContact() {
        System.out.println(ANSI_PURPLE + "Search: " + ANSI_RESET);
        String userSearch = userInput.getString();
        System.out.println();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().startsWith(userSearch.toLowerCase())) {
                System.out.println(contact);
                System.out.println();
                boolean searchAgain = userInput.yesNo(ANSI_CYAN + "Is " + contact.getName() + " the correct contact?" + ANSI_RESET);
                System.out.println();
                if (!searchAgain) {
                    Contact temp = searchContact();
                    if (temp != null) {
                        contact = temp;
                    }
                }
                return contact;
            }
        }
        return null;
    }

    private static void deleteContact() {

        System.out.print(ANSI_CYAN + "What is the name of the contact you would like to delete? \n" + ANSI_RESET);
        Contact userContactRemoveSelect = searchContact();

        System.out.printf(ANSI_RED + "%s has been deleted!" + ANSI_RESET, userContactRemoveSelect);
        contacts.remove(userContactRemoveSelect);
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

}
