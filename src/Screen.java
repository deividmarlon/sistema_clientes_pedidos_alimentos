import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Screen {

    //CONSTANTS

    //software name to calculate size of some menus.
    private static final String SOFTWARE_NAME = "Food orders management system";
    //number of choices from each menu
    private final int NUMBER_CHOICES_MAIN_MENU = 4;
    private final int NUMBER_CHOICES_CLIENT_MENU = 7;
    private final int NUMBER_CHOICES_FOOD_MENU = 6;
    private final int NUMBER_CHOICES_EDIT_FOOD_MENU = 2;
    private final int NUMBER_CHOICES_REPORT_MENU = 3;
    private final int NUMBER_CHOICES_EDIT_CLIENT_MENU = 3;

    public int editClientMenu(){
        int choice = -1;

        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();

        //prints SOFTWARE_NAME - Food Menu
        this.printHeader("Client Menu");

        //will repeat until valid input
        do {
            System.out.println();
            System.out.println("1. Name.");
            System.out.println("2. Birth Date.");
            System.out.println("3. Exit to main menu.");

            choice = validate.getValidChoice(NUMBER_CHOICES_EDIT_CLIENT_MENU);

        }while(choice < 0);

        //returns 0 in case of 'exit to main menu'
        if(choice != NUMBER_CHOICES_FOOD_MENU) return choice;
        else return 0;

    }


    public void printClient(Client client){
        System.out.print(client.getId());
        System.out.print(" ");
        System.out.print(client.getName());
        System.out.print(" ");

        //format date to brazilian standard
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.print(client.getBirthDate().format(formatter));
    }

    //wait user to press enter to continue execution
    //pre-condition: none
    //post-condition: user needs to press enter for program to continue
    public void waitInput(){
        System.out.println();
        System.out.println("Press enter to continue");

        try{
            System.in.read();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //prints a centralized header with the software name and a message
    // in the style: 'SOFTWARE_NAME' - 'header'
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
        int choice = 0;

        Validation validate = new Validation();

        //prints SOFTWARE_NAME - Main Menu
        this.printHeader("Main Menu");

    do{
        System.out.println();
        System.out.println("1. Client Menu.");
        System.out.println("2. Food Menu.");
        System.out.println("3. Report Menu.");
        System.out.println("4. Exit program.");

        choice = validate.getValidChoice(NUMBER_CHOICES_MAIN_MENU);

    }while(choice < 0);

        //returns 0 in case of 'exit program'
        if(choice != NUMBER_CHOICES_MAIN_MENU) return choice;
        else return 0;
    }

    //prints client menu, reads an int and returns it if it's valid
    //pre-conditions: none
    //post-conditions: returns a valid user choice. will return 0 in the case
    //to go back to main menu
    public int clientMenu() {
        int choice = -1;

        Validation validate = new Validation();

        //prints SOFTWARE_NAME - Client Menu
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

            choice = validate.getValidChoice(NUMBER_CHOICES_CLIENT_MENU);

        }while(choice < 0);

        //returns 0 in case of 'exit to main menu'
        if(choice != NUMBER_CHOICES_CLIENT_MENU) return choice;
        else return 0;
    }

    //prints food menu, reads an int and returns it if it's valid
    //pre-conditions: none
    //post-conditions: returns a valid user choice. will return 0 in the case
    //to go back to main menu
    public int foodMenu(){
        int choice = -1;

        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();

        //prints SOFTWARE_NAME - Food Menu
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

            choice = validate.getValidChoice(NUMBER_CHOICES_FOOD_MENU);

        }while(choice < 0);

        //returns 0 in case of 'exit to main menu'
        if(choice != NUMBER_CHOICES_FOOD_MENU) return choice;
        else return 0;

    }

    public int editFoodMenu(){
        int choice = -1;

        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();

        //prints SOFTWARE_NAME - Food Menu
        this.printHeader("Food Menu");

        //will repeat until valid input
        do {
            System.out.println();
            System.out.println("1. Name.");
            System.out.println("2. Exit to main menu");
            choice = validate.getValidChoice(NUMBER_CHOICES_EDIT_FOOD_MENU);

        }while(choice < 0);

        //returns 0 in case of 'exit to main menu'
        if(choice != NUMBER_CHOICES_FOOD_MENU) return choice;
        else return 0;

    }

    //prints report menu, reads an int and returns it if it's a valid input
    //pre-conditions: none
    //post-conditions: returns a valid user choice. will return 0 in the case
    //to go back to main menu
    public int reportMenu(){
        int choice = -1;

        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();

        //prints SOFTWARE_NAME - Report Menu
        this.printHeader("Report Menu");

        //will repeat until valid input
        do {
            System.out.println();
            System.out.println("1. Generate client report");
            System.out.println("2. Generate food report");
            System.out.println("3. Exit to main menu");

            choice = validate.getValidChoice(NUMBER_CHOICES_REPORT_MENU);

        }while(choice < 0);

        //returns 0 in case of 'exit to main menu'
        if(choice != NUMBER_CHOICES_REPORT_MENU) return choice;
        else return 0;
    }

}
