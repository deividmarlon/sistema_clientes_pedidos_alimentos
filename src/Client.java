import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    private int id;
    private String name;
    private LocalDate birthDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }



    //Asks user for the client information and saves it to this instance
    //pre-conditions: none
    //post-conditions: information saved
    public void createClient(){
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
        Database database = new Database();
        switch(userChoice){
            case 1:database.insertClient();
                    break;
            default: break;
        }
    }

}
