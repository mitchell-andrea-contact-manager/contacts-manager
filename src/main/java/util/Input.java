package util;

import java.util.Scanner;

public class Input {
    private Scanner scanner;

    //    get a scanner
    public Scanner getScanner() {
        return scanner;
    }

    //    get an input
    public Input() {
        scanner = new Scanner(System.in);
    }

    //    get a string
    public String getString(){
        return scanner.nextLine();
    }

    //    get a string with a prompt
    public String getString(String prompt){
        System.out.println(prompt);
        scanner.nextLine();
        return getString();
    }

    //    get a boolean value
    public boolean yesNo(){
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")){
            return true;
        }
        return false;
    }

    //    get a boolean value with a prompt
    public boolean yesNo(String prompt){
        System.out.println(prompt);
        return yesNo();
    }

    // get an integer between a range
    public int getInt(int min, int max){
        System.out.println("Enter a number between " + min + " and " + max + ":");
        int num = scanner.nextInt();
//        int anInt = getInt();
        if (num >= min && num <= max) {
            System.out.println(num + " is between " + min + " and " + max + "!");
        }else {
            return getInt(min, max);
        }
        return num;
    }

    //    get an integer and check if integer with try/catch
    public  int getInt(){
        int userInt = 0;
        try {
            String s = getString(); //can be outside the try
            userInt = Integer.valueOf(s); //not a checked exception
        }catch(NumberFormatException e){
            System.out.println("Enter a valid non-decimal number: ");
            userInt = getInt();
        }
        return userInt;
    }

    //    get an integer with a prompt
    public int getInt(String prompt){
        System.out.println(prompt);
        scanner.nextLine();
        return getInt();
    }

    //    get a double between a range
    public double getDouble(double min, double max){
        System.out.println("Enter a decimal between " + min + " and " + max + ":");
        double dec = scanner.nextDouble();
        //double aDouble = getDouble();
        if (dec >= min && dec <= max) {
            System.out.println(dec + " is between " + min + " and " + max + "!");
        }else {
            return getDouble(1, 10);
        }
        return dec;
    }

    //    get a double and check if double with try/catch
    public double getDouble(){
        double userDouble = 0;
        try {
            String s = getString();
            userDouble = Double.valueOf(s);
        }catch(NumberFormatException e){
            System.out.println("Enter a valid decimal number: ");
            userDouble = getDouble();
        }
        return userDouble;
    }

    //    get a double with a prompt
    public double getDouble(String prompt){
        System.out.println(prompt);
        scanner.nextLine();
        return getDouble();
    }

    public int getBinary(){
        int userBinary = 0;
        try{
            String s = getString();
            userBinary = Integer.parseInt(s,2);
        }catch(NumberFormatException e){
            System.out.println("Enter a valid binary number: ");
            userBinary = getBinary();
        }
        return userBinary;
    }

    public int getHex(){
        int userHex = 0;
        try{
            String s = getString();
            userHex = Integer.parseInt(s,16);
        }catch(NumberFormatException e){
            System.out.println("Enter a valid hex number: ");
            userHex = getHex();
        }
        return userHex;
    }
}
