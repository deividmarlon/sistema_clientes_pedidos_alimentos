import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Database {

    //Inserts client values into database
    //pre-conditions: valid values in class variables
    //post-conditions: information inserted into database
    public void insertClient(){
        File file = new File("./databaseClient.txt");
        Client client = new Client();
        client.createClient();

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
            database.write(client.getId() + "," + client.getName() + "," + client.getBirthDate() + "\n" );
            database.close();
        }catch(IOException e){
            System.out.println("ERROR: Could not write in database.txt");
            e.printStackTrace();
        }
    }

}
