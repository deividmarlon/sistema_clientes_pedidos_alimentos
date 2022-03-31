import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Database {

    private File file;

    Database(String fileName){
       file = new File(fileName);
    }

    public Client findClient(int id){
        int idFile;
        LocalDate dateFile;
        String[] line;

        Client client = new Client();
        Validation validate = new Validation();

        if(!file.exists()) return null;

        try{
            Scanner scan = new Scanner(file);
            scan.useDelimiter(",");
            while(scan.hasNextLine()){
                line = scan.nextLine().split(",");
                idFile = Integer.parseInt(line[0]);
                if(idFile == id) {
                    client.setId(idFile);
                    client.setName(line[1]);

                    String[] date = line[2].split("-");
                    int day = Integer.parseInt(date[2]);
                    int month = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[0]);
                    dateFile = LocalDate.of(year, month, day);

                    client.setBirthDate(dateFile);
                    scan.nextLine();
                    return client;
                }
            }
            return null;

        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }

    }

    public FoodInteractions findFood(int id){

        FoodInteractions foodInteractions = new FoodInteractions();
        Validation validate = new Validation();
        if(!file.exists()) return null;

        try{
            //TODO find
            Scanner fileText = new Scanner(file);
            fileText.useDelimiter(",");
            int readId;
            while(fileText.hasNextLine()){
                readId = fileText.nextInt();
                if(readId == id){
                    System.out.println(readId);
                    fileText.close();
                }
                fileText.nextLine();
            }
            fileText.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }

        return foodInteractions;

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
