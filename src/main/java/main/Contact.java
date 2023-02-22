package main;

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
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
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