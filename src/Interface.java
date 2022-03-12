import java.util.Scanner;

public class Interface {

    //constant with the software name to calculate size of some menus. needs to be even sized.
    private static final String softwareName = "Food orders management system - Main Menu:";

    //prints on screen a header with the software name and authors' name
    //pre-conditions: none
    //post-conditions: header printed on screen
    public void startScreen(){
        int i;
        int messageSize = softwareName.length();

        for(i = 0; i < 100;i++) System.out.print("*");
        System.out.println();

        for(i = 0; i < 10; i++) System.out.print("*");
        for(i = 0; i < ((80-messageSize)/2); i++) System.out.print(" ");
        System.out.print("Food orders management system.");
        for(i = 0; i < ((80-messageSize)/2); i++) System.out.print(" ");
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
        int i;
        int messageSize = softwareName.length();
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        boolean valid = false;

        for(i = 0; i < ((100-messageSize)/2); i++) System.out.print(" ");
        System.out.print(softwareName);
        for(i = 0; i < ((100-messageSize)/2); i++) System.out.print(" ");
        System.out.println();

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
            if(choice>=1 && choice<=4 )valid = true;
            else{
                System.out.println("ERROR: Invalid input.");
                scan.nextLine();
            }
        }else{
            System.out.println("ERROR: Invalid input.");
            scan.nextLine();
        }
    }while(!valid);

        if(choice != 4) return choice;
        else return 0;
    }

    //prints client menu, reads an int and returns it if it's valid
    //pre-conditions: none
    //post-conditions: returns a valid user choice. will return 0 in the case
    //to go back to main menu
    public int clientMenu() {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        boolean valid = false;

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
                if(choice>=1 && choice<=7 )valid = true;
                else{
                    System.out.println("ERROR: Invalid input.");
                    scan.nextLine();
                }
            }else{
                System.out.println("ERROR: Invalid input.");
                scan.nextLine();
            }
        }while(!valid);

        if(choice != 7) return choice;
        else return 0;
    }
    //prints food menu, reads an int and returns it if it's valid
    //pre-conditions: none
    //post-conditions: returns a valid user choice. will return 0 in the case
    //to go back to main menu
    public int foodMenu(){
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        boolean valid = false;

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
                if(choice>=1 && choice<=6 )valid = true;
                else{
                    System.out.println("ERROR: Invalid input.");
                    scan.nextLine();
                }
            }else{
                System.out.println("ERROR: Invalid input.");
                scan.nextLine();
            }
        }while(!valid);

        if(choice != 6) return choice;
        else return 0;

    }
}
