import java.util.Scanner;

public class Validation {

    //verifies if a number is even
    //pre-conditions: none
    //post-conditions: returns true if number is even and
    //returns false if number is odd
    public boolean isEven(int n) {
        if (n % 2 == 0) return true;
        return false;
    }

    //will try to get a valid choice from user and return -1 in case of invalid choice
    //pre-conditions: none
    //post-conditions: returns user choice or -1 in case of invalid choice
    public int getValidChoice(int numberChoices){
        int choice = -1;
        Scanner scan = new Scanner(System.in);

        if (scan.hasNextInt()) {
            choice = scan.nextInt();
            //checks if is a valid input
            if(!(choice>=1 && choice<=numberChoices)){
                System.out.println("ERROR: Invalid input.");
                System.out.println();
                scan.nextLine();
                choice = -1;
            }
        }else{
            System.out.println("ERROR: Invalid input.");
            System.out.println();
            scan.nextLine();
            choice = -1;
        }

       return choice;
    }

    //will try to get a valid id from user and return -1 in case of invalid id
    //pre-conditions: none
    //post-conditions: returns valid id or -1 in case of invalid id
    public int getValidId(){
        int id = -1;

        Scanner scan = new Scanner(System.in);

        if(scan.hasNextInt()) {
            id = scan.nextInt();
            if(id < 0){
                System.out.println("ERROR: Invalid input.");
                scan.nextLine();
                id = -1;
            }
            //TODO validate duplicate id
        }else{
            System.out.println("ERROR: Invalid input.");
            System.out.println();
            scan.nextLine();
            id = -1;
        }
        return id;
    }

    //checks for leap years
    //pre-conditions: none
    //post-conditions: returns true if its a leap year and false otherwise
    public boolean isLeapYear(int year){
        if(year >= 1582 && (year % 4 == 0)) return true;
        return false;
    }

    //validates a string array that contains a date in the format (dd-mm-yyyy)
    //pre-conditions: none
    //post-conditions: returns true if the array string its a valid date and
    //false if it isn't
    public boolean validateDate(String[] date){
        int day, month, year;
        int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

        try {
            day = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            year = Integer.parseInt(date[2]);
        }catch(NumberFormatException e){
            return false;
        }

        if(isLeapYear(year)) daysInMonth[1]++;

        if(month < 0 || month > 12) return false;

        if( (day > 0) && (day <= daysInMonth[month-1]))
            return true;
        return false;
    }
}