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
                scan.nextLine();
                choice = -1;
            }
        }else{
            System.out.println("ERROR: Invalid input.");
            scan.nextLine();
            choice = -1;
        }

       return choice;
    }

}