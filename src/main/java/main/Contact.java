package main;

import java.util.Objects;

public class Contact {
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }
    public Contact(String name) {
        this.name = name;
    }

//    public static Contact fromFileString(String fileString){
//        //file format is name,number
//        String [] pieces = fileString.split(","); //just like split in JS
//        Contact contact = new Contact(pieces[0]);
////        fighter.setHealth(Integer.parseInt(pieces[1]));
//        contact.setName(contact);
////        fighter.setStrength(Integer.parseInt(pieces[2]));
//        return contact;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Contact: " + name +
                " | " + number ;
    }

    // accessors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}