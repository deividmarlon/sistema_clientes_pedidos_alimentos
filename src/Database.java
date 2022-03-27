import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Database {

    private File file;

    Database(String fileName){
       file = new File(fileName);
    }

    public Client findClient(int id){

        Client client = new Client();
        Validation validate = new Validation();
        if(!file.exists()) return null;

        try{
            Scanner scan = new Scanner(file);
            //TODO find
            System.out.println(validate.checkDuplicatedID(id, scan));
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }

        return client;

    }

    //Inserts client values into database
    //pre-conditions: valid values in class variables
    //post-conditions: information inserted into database
    public void insertClient(int id, String name, LocalDate birthDate){

        //checks if file exists, if it doesn't, create file
        if(!file.exists()){
            System.out.println("Database not found. Creating new " + file.getName() + "...");
            try{
                if(file.createNewFile()) System.out.println(file.getName() + " created!");
            }catch(IOException e){
                System.out.println("ERROR: Database could not be created.");
                e.printStackTrace();
            }
        }

        //writes in database
        try{
            FileWriter database = new FileWriter(file.getName(), true);
            database.write(id + "," + name + "," + birthDate + "\n" );
            database.close();
        }catch(IOException e){
            System.out.println("ERROR: Could not write in database.txt");
            e.printStackTrace();
        }
    }

}
