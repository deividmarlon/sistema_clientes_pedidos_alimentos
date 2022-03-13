import java.util.Scanner;

public class Interface {

    //constant with the software name to calculate size of some menus.
    private static final String SOFTWARE_NAME = "Food orders management system";

    //prints a centralized header with the software name and a message
    //pre-conditions: none
    //post-conditions: header printed
    private void printHeader(String header){
        String message = SOFTWARE_NAME + " - " + header;
        int messageSize = message.length();
        int i;

        for(i = 0; i < ((100-messageSize)/2); i++) System.out.print(" ");
        System.out.print(message);
        for(i = 0; i < ((100-messageSize)/2); i++) System.out.print(" ");
        System.out.println();
    }

    //prints on screen a header with the software name and authors' name
    //pre-conditions: none
    //post-conditions: header printed on screen
    public void startScreen(){
        int i;
        int messageSize = SOFTWARE_NAME.length();

        Validation validate = new Validation();

        for(i = 0; i < 100;i++) System.out.print("*");
        System.out.println();

        for(i = 0; i < 10; i++) System.out.print("*");
        for(i = 0; i < ((80-messageSize)/2); i++) System.out.print(" ");
        System.out.print(SOFTWARE_NAME);
        //prints an even number of spaces if message size is even
        //prints odd number of spaces if message size is odd
        if(validate.isEven(messageSize)) {
            for (i = 0; i < ((80 - messageSize) / 2); i++) System.out.print(" ");
        }
        else {
            for (i = 0; i < ((80 + 1 - messageSize) / 2); i++) System.out.print(" ");
        }
        for(i = 0; i < 10; i++) System.out.print("*");
        System.out.println();

        for(int j = 0; j < 2; j++) {
            for (i = 0; i < 10; i++) System.out.print("*");
            for (i = 0; i < 80; i++) System.out.print(" ");
            for (i = 0; i < 10; i++) System.out.print("*");
            System.out.println();
        }
        for (i = 0; i < 10; i++) System.out.print("*");
        for (i = 0; i < 19; i++) System.out.print(" ");
        System.out.print("Authors: Daniel Zonta Ojeda, Deivid Marlon");
        for (i = 0; i < 19; i++) System.out.print(" ");
        for (i = 0; i < 10; i++) System.out.print("*");
        System.out.println();
        for(i = 0; i < 100;i++) System.out.print("*");
        System.out.println();
    }

    //prints main menu on screen and returns user choice
    //pre-conditions: none
    //post-conditions: main menu printed on screen until a valid user choice
    //then returns user choice
    public int mainMenu(){

        //number of choices from menu
        final int NUMBER_CHOICES = 4;

        int choice = 0;
        boolean valid = false;

        Scanner scan = new Scanner(System.in);

        //prints header
        this.printHeader("Main Menu");

    do{
        System.out.println();
        System.out.println("1. Client Menu.");
        System.out.println("2. Food Menu.");
        System.out.println("3. Report Menu.");
        System.out.println("4. Exit program.");

        //checks if it's int
        if (scan.hasNextInt()) {
            choice = scan.nextInt();
            //checks if is a valid input
            if(choice>=1 && choice<=NUMBER_CHOICES )valid = true;
            else{
                System.out.println("ERROR: Invalid input.");
                scan.nextLine();
            }
        }else{
            System.out.println("ERROR: Invalid input.");
            scan.nextLine();
        }
    }while(!valid);

        //returns 0 in case of 'exit program'
        if(choice != NUMBER_CHOICES) return choice;
        else return 0;
    }

    //prints client menu, reads an int and returns it if it's valid
    //pre-conditions: none
    //post-conditions: returns a valid user choice. will return 0 in the case
    //to go back to main menu
    public int clientMenu() {
        int choice = 0;
        boolean valid = false;

        //number of choices from menu
        final int NUMBER_CHOICES = 7;

        Scanner scan = new Scanner(System.in);

        //prints header
        this.printHeader("Client Menu");

        //will repeat until valid input
        do {
            System.out.println();
            System.out.println("1. Register new client.");
            System.out.println("2. Edit existing client.");
            System.out.println("3. Remove existing client.");
            System.out.println("4. Find client.");
            System.out.println("5. View history.");
            System.out.println("6. View full history.");
            System.out.println("7. Exit to main menu");

            //checks if it's int
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                //checks if is a valid input
                if(choice>=1 && choice<=NUMBER_CHOICES )valid = true;
                else{
                    System.out.println("ERROR: Invalid input.");
                    scan.nextLine();
                }
            }else{
                System.out.println("ERROR: Invalid input.");
                scan.nextLine();
            }
        }while(!valid);

        //returns 0 in case of 'exit to main menu'
        if(choice != NUMBER_CHOICES) return choice;
        else return 0;
    }

    //prints food menu, reads an int and returns it if it's valid
    //pre-conditions: none
    //post-conditions: returns a valid user choice. will return 0 in the case
    //to go back to main menu
    public int foodMenu(){
        int choice = 0;
        boolean valid = false;

        //number of choices from menu
        final int NUMBER_CHOICES = 6;

        Scanner scan = new Scanner(System.in);

        //prints header
        this.printHeader("Food Menu");

        //will repeat until valid input
        do {
            System.out.println();
            System.out.println("1. Register new food.");
            System.out.println("2. Edit existing food.");
            System.out.println("3. Remove existing food.");
            System.out.println("4. Find food.");
            System.out.println("5. List food");
            System.out.println("6. Exit to main menu");

            //checks if it's int
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                //checks if is a valid input
                if(choice>=1 && choice<=NUMBER_CHOICES )valid = true;
                else{
                    System.out.println("ERROR: Invalid input.");
                    scan.nextLine();
                }
            }else{
                System.out.println("ERROR: Invalid input.");
                scan.nextLine();
            }
        }while(!valid);

        //returns 0 in case of 'exit to main menu'
        if(choice != NUMBER_CHOICES) return choice;
        else return 0;

    }

    public int reportMenu(){
        int choice = 0;
        boolean valid = false;

        //number of choices from menu
        final int NUMBER_CHOICES = 3;

        Scanner scan = new Scanner(System.in);

        //prints header
        this.printHeader("Report Menu");

        //will repeat until valid input
        do {
            System.out.println();
            System.out.println("1. Generate client report");
            System.out.println("2. Generate food report");
            System.out.println("3. Exit to main menu");

            //checks if it's int
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                //checks if is a valid input
                if(choice>=1 && choice<=NUMBER_CHOICES ) valid = true;
                else{
                    System.out.println("ERROR: Invalid input.");
                    scan.nextLine();
                }
            }else{
                System.out.println("ERROR: Invalid input.");
                scan.nextLine();
            }
        }while(!valid);

        //returns 0 in case of 'exit to main menu'
        if(choice != NUMBER_CHOICES) return choice;
        else return 0;
    }

}
