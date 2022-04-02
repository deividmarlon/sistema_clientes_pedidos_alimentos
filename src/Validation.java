import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Validation {

    private Logger logger = new Logger();

    //reads input and returns it if its a valid positive int
    //returns -1 if it's invalid
    //pre-conditions: none
    //post-conditions: returns a valid int or -1
    public int getValidInt(){
        int n;

        Scanner scan = new Scanner(System.in);

        //checks if id is an int
        if(scan.hasNextInt()) {
            n = scan.nextInt();
        }else{
            System.out.println("ERROR: Invalid input. Travels needs to be a positive integer.");
            System.out.println();
            logger.write("ERROR: Invalid input. Travels needs to be a positive integer.");
            scan.nextLine();
            return -1;
        }

        //checks if id is less than 0
        if(n < 0){
            System.out.println("ERROR: Invalid input. Travels needs to be a positive integer.");
            logger.write("ERROR: Invalid input. Travels needs to be a positive integer.");
            scan.nextLine();
            return -1;
        }

       return n;
    }

    public double getValidDouble(){
        double n;

        Scanner scan = new Scanner(System.in);

        //checks if id is an int
        if(scan.hasNextDouble()) {
            n = scan.nextDouble();
        }else{
            System.out.println("ERROR: Invalid input. Value needs to be a positive double.");
            System.out.println();
            logger.write("ERROR: Invalid input. Value needs to be a positive double.");
            scan.nextLine();
            return -1;
        }

        //checks if id is less than 0
        if(n < 0){
            System.out.println("ERROR: Invalid input. Value needs to be a positive double.");
            logger.write("ERROR: Invalid input. Value needs to be a positive double.");
            scan.nextLine();
            return -1;
        }

        return n;
    }

    //verifies if a number is even
    //pre-conditions: none
    //post-conditions: returns true if number is even and
    //returns false if number is odd
    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    //will try to get a valid choice from user and return -1 in case of invalid choice
    //pre-conditions: none
    //post-conditions: returns user choice or -1 in case of invalid choice
    public int getValidChoice(int numberChoices){
        int choice;
        Scanner scan = new Scanner(System.in);

        if (scan.hasNextInt()) {
            choice = scan.nextInt();
            //checks if is a valid input
            if(!(choice>=1 && choice<=numberChoices)){
                System.out.println("ERROR: Invalid input.");
                System.out.println();
                logger.write("ERROR: Invalid input.");
                scan.nextLine();
                choice = -1;
            }
        }else{
            System.out.println("ERROR: Invalid input.");
            System.out.println();
            logger.write("ERROR: Invalid input.");
            scan.nextLine();
            choice = -1;
        }

        return choice;
    }

    //will try to get a valid id from user and return -1 in case of invalid id
    //pre-conditions: none
    //post-conditions: returns valid id or -1 in case of invalid id
    public int getValidId(String filename){
        int id;

        File file = new File("./databases/"+filename);
        Scanner scan = new Scanner(System.in);

        //checks if id is an int
        if(scan.hasNextInt()) {
            id = scan.nextInt();
        }else{
            System.out.println("ERROR: Invalid input. ID needs to be a positive integer.");
            System.out.println();
            logger.write("ERROR: Invalid input. ID needs to be a positive integer.");
            scan.nextLine();
            return -1;
        }

        //if id==0 generates valid id
        if(id==0){
            if(file.exists() == false) return 1;
            try{
                int i = 1;
                while(true){
                    Scanner fileScan = new Scanner(file);
                    if(checkDuplicatedID(i, fileScan) == false){
                        fileScan.close();
                        return i;
                    }
                    fileScan.close();
                    i++;
                }
            }catch(IOException e){
                e.printStackTrace();
                logger.write(e.getMessage());
            }
        }

        //checks if id is less than 0
        if(id < 0){
            System.out.println("ERROR: Invalid input. ID needs to be a positive integer.");
            logger.write("ERROR: Invalid input. ID needs to be a positive integer.");
            scan.nextLine();
            return -1;
        }

        //calls checkDuplicatedID to check if id already exists in database
        if(file.exists()){
            try{
                Scanner fileScan = new Scanner(file);
                if(this.checkDuplicatedID(id, fileScan)) {
                    System.out.println("ERROR: ID already exists.");
                    logger.write("ERROR: ID already exists.");
                    System.out.println();
                    fileScan.close();
                    return -1;
                }
                fileScan.close();
            }catch (FileNotFoundException e){
                id = -1;
                e.printStackTrace();
            }
        }

        return id;
    }

    //will run the database "file" and return true if the id already exists. will
    //return false otherwise
    //pre-conditions: none
    //post-conditions: returns true if id exists and false if it doesn't
    public boolean checkDuplicatedID(int id, Scanner file){
        int idFile;
        file.useDelimiter(",");
        while(file.hasNextLine()){
            idFile = file.nextInt();
            if(idFile == id) return true;
            file.nextLine();
        }
        return false;
    }

    //checks for leap years
    //pre-conditions: none
    //post-conditions: returns true if it's a leap year and false otherwise
    public boolean isLeapYear(int year){
        return ((year >= 1582) && (year % 4 == 0));
    }

    //validates a string array that contains a date in the format (dd-mm-yyyy)
    //pre-conditions: none
    //post-conditions: returns true if the array string it's a valid date and
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

        //if it's a leap year adds a day to february
        if(isLeapYear(year)) daysInMonth[1]++;

        //checks if month is between 1 and 12
        if(month < 0 || month > 12) return false;

        //checks if day is bigger than 0 and smaller than the
        //number of days in month
        if( (day > 0) && (day <= daysInMonth[month-1]))
            return true;
        return false;
    }

}