import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    private int id;
    private String name;
    private LocalDate birthDate;

    //Inserts client values into database
    //pre-conditions: valid values in class variables
    //post-conditions: information inserted into database
    private void insertDatabase(){
        File file = new File("./databaseClient.txt");

        //checks if file exists, if it doesn't, create file
        if(!file.exists()){
            System.out.println("Database not found. Creating new databaseClient.txt...");
            try{
                if(file.createNewFile()) System.out.println("databaseClient.txt created!");
            }catch(IOException e){
                System.out.println("ERROR: Database could not be created.");
                e.printStackTrace();
            }
        }

        //writes in database
        try{
            FileWriter database = new FileWriter("databaseClient.txt", true);
            database.write(this.id + "," + this.name + "," + birthDate + "\n" );
            database.close();
        }catch(IOException e){
            System.out.println("ERROR: Could not write in database.txt");
            e.printStackTrace();
        }
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
        switch(userChoice){
            case 1: createClient();
                    insertDatabase();
                    break;
            default: break;
        }
    }

}
