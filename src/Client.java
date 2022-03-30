import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    private int id;
    private String name;
    private LocalDate birthDate;

    //asks user for client id and tries to find it in database
    //pre-conditions: none
    //post-conditions: creates a class instance if client id exists
    private void findClient(){

        boolean valid = false;

        Database database = new Database("databaseClient.txt");
        Scanner scan = new Scanner(System.in);
        Screen screen = new Screen();

        do {
            System.out.println("Insert client id");
            if (scan.hasNextInt()) {
                id = scan.nextInt();
                Client client = database.findClient(id);
                if (client != null){
                    this.id = client.id;
                    this.name = client.name;
                    this.birthDate = client.birthDate;
                }
                else System.out.println("Client doesn't exist.");
                valid = true;
            } else {
                System.out.println("Invalid id.");
                System.out.println();
                scan.nextLine();
            }
        }while(valid == false);

    }

    //Asks user for the client information and saves it to this instance
    //pre-conditions: none
    //post-conditions: information saved
    private void createClient(){
        String name;
        String[] date;
        int id, day, month, year;

        Validation validate = new Validation();
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("ID: (optional - input a 0)");
            id = validate.getValidId("databaseClient.txt");
        }while(id < 0 );

        System.out.println("Name:");
        name = scan.nextLine();

        do {
            System.out.println("Birth date: (dd-mm-aaaa)");
            String temp = scan.nextLine();
            date = temp.split("-");
            if(!validate.validateDate(date)){
                System.out.println("ERROR: Invalid date");
                System.out.println();
            }

        }while(!validate.validateDate(date));

        day = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);
        LocalDate birthDate = LocalDate.of(year, month, day);

        this.id = id;
        this.name = name;
        this.birthDate = birthDate;

        //TODO viajens??

    }

    //manages the user choice from the client sub menu
    //pre-conditions: none
    //post-conditions:
    public void configClient(int userChoice){
        Database database = new Database("databaseClient.txt");
        Screen screen = new Screen();
        switch(userChoice){
            case 1: createClient();
                    database.insertClient(id, name, birthDate);
                    break;
            case 4: findClient();
//                    screen.showClient(id);
            default: break;
        }
    }

}
