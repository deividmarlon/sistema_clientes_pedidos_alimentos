import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientRepository {

    private File file;

    private Logger logger = new Logger();

    ClientRepository(){
       new File("./databases/").mkdirs();
       file = new File("./databases/databaseClient.txt");

        //Checks if file exists, if it doesn't, create file
        if(!file.exists()){
            System.out.println("Database not found!");
            System.out.println("Creating new " + file.getName() + "...");
            try{
                if(file.createNewFile()) System.out.println(file.getName() + " created!");
            }catch(IOException e){
                System.out.println("ERROR: Database could not be created.");
                e.printStackTrace();
                logger.write("ERROR: Database could not be created.");
                logger.write(e.getMessage());
            }
        }
    }

    //returns an arraylist with all clients from database
    //pre-conditions: none
    //post-conditions: arraylist returned
    public ArrayList<ClientEntity> index(){

        LocalDate dateFile;
        ArrayList<ClientEntity> clients = new ArrayList<ClientEntity>(0);
        ClientEntity client;

        if(!file.exists()){
            return clients;
        }

        try{
            Scanner fileText = new Scanner(file);
            String line;
            String[] splittedLine;
            while(fileText.hasNextLine()){
                line=fileText.nextLine();
                splittedLine = line.split(",");
                client = new ClientEntity();
                client.id = Integer.parseInt(splittedLine[0]);
                client.name = splittedLine[1];

                String[] date = splittedLine[2].split("-");
                int day = Integer.parseInt(date[2]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[0]);
                dateFile = LocalDate.of(year, month, day);
                client.birthDate = dateFile;
                client.travels = Integer.parseInt(splittedLine[3]);

                if(splittedLine.length > 4) {
                    ClientInteractions interactions = new ClientInteractions();
                    client.foodsHistory = interactions.getFoodHistory(splittedLine[4]);
                    client.foodsFullHistory = interactions.getFoodHistory(splittedLine[5]);
                }

                clients.add(client);
            }
            fileText.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            logger.write(e.getMessage());
        }
        return clients;
    }

    //Inserts client values into database
    //pre-conditions: valid values in class variables
    //post-conditions: information inserted into database
    public boolean save(ClientEntity client){

        //checks if file exists, if it doesn't, create file
        if(!file.exists()){
            System.out.println("ERROR: Could not write in database.txt");
            logger.write("ERROR: Could not write in database.txt");
            return false;
        }

        //Prepares data string to write in database
        String data = client.id +","+ client.name +","+ client.birthDate +","+ client.travels + "\n";

        //writes in database
        try{
            FileWriter database = new FileWriter(file.getAbsolutePath(), true);
            database.write(data);
            database.close();
            return true;
        }catch(IOException e){
            System.out.println("ERROR: Could not write in database.txt");
            e.printStackTrace();
            logger.write("ERROR: Could not write in database.txt");
            logger.write(e.getMessage());
            return false;
        }
    }

    //will try to find a client with same id as parameter and return
    //a ClientEntity object with its information. returns null if it doesn't find
    //the id
    //pre-conditions: none
    //post-conditions: returns ClientEntity or null
    public ClientEntity findById(int id){
        int idFile;
        LocalDate dateFile;
        String[] line;

        ClientEntity client = new ClientEntity();

        if(!file.exists()) return null;

        try{
            Scanner scan = new Scanner(file);
            scan.useDelimiter(",");
            while(scan.hasNextLine()){
                line = scan.nextLine().split(",");
                idFile = Integer.parseInt(line[0]);
                if(idFile == id) {
                    client.id = idFile;
                    client.name = line[1];

                    String[] date = line[2].split("-");
                    int day = Integer.parseInt(date[2]);
                    int month = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[0]);
                    dateFile = LocalDate.of(year, month, day);

                    client.birthDate = dateFile;
                    client.travels = Integer.parseInt(line[3]);

                    if(line.length > 4) {
                        ClientInteractions interactions = new ClientInteractions();
                        client.foodsHistory = interactions.getFoodHistory(line[4]);
                        client.foodsFullHistory = interactions.getFoodHistory(line[4]);
                    }
                }
            }
            scan.close();
            return client;

        }catch (FileNotFoundException e){
            e.printStackTrace();
            logger.write(e.getMessage());
            return null;
        }

    }

    //will create a temp database file, and replace the line with client.id
    //with the client information passed as parameter
    //pre-conditions: none
    //post-conditions: client edited
    public boolean update(ClientEntity client){
        if(!file.exists()) return false;

        File tempFile = new File("databaseClient_temp.txt");

        //Checks if tempFile exists, if it doesn't, create tempFile
        if(!tempFile.exists()){
            try{
                tempFile.createNewFile();
            }catch(IOException e){
                System.out.println("ERROR: Database could not be edited.");
                e.printStackTrace();
                logger.write("ERROR: Database could not be edited.");
                logger.write(e.getMessage());
                return false;
            }
        }

        String foodHistory = "[";
        for(Integer i : client.foodsHistory){
            foodHistory = foodHistory.concat(i.toString() + ".");
        }
        foodHistory = foodHistory.concat("]");

        String foodFullHistory = "[";
        for(Integer i : client.foodsFullHistory){
            foodFullHistory = foodFullHistory.concat(i.toString() + ".");
        }
        foodFullHistory = foodFullHistory.concat("]");

        //Prepares data string to write in database
        String data = client.id +","+ client.name + "," + client.birthDate +
                "," + client.travels + "," + foodHistory + "," + foodFullHistory;

        //Writes tempFile with original file content but
        //replacing the corresponding food parameter line
        try{
            FileWriter fw = new FileWriter(tempFile.getName(), true);

            Scanner fileText = new Scanner(file);

            int idFile;
            String line;
            String [] splittedLine;

            while(fileText.hasNextLine()){
                line=fileText.nextLine();
                splittedLine = line.split(",");
                idFile = Integer.parseInt(splittedLine[0]);
                if(idFile == client.id){
                    fw.write(data+"\n");
                }else{
                    fw.write(line+"\n");
                }
            }
            fileText.close();
            fw.close();
        }catch(IOException e){
            System.out.println("ERROR: Could not write in database.txt");
            e.printStackTrace();
            logger.write("ERROR: Could not write in database.txt");
            logger.write(e.getMessage());
            return false;
        }

        //Replace original file by temp file

        file.delete();
        file = new File("./databases/databaseClient.txt");
        tempFile.renameTo(file);

        return true;
    }

    //will try to delete client with same id as parameter. returns true if succeeded
    //and false if it fails
    //pre-conditions: none
    //post-conditions: returns a boolean and tries to delete client from database
    public boolean delete(int id){
        if(findById(id) == null) return false;

        if(!file.exists()) return false;

        File tempFile = new File("databaseClient_temp.txt");

        //Checks if tempFile exists, if it doesn't, create tempFile
        if(!tempFile.exists()){
            try{
                tempFile.createNewFile();
            }catch(IOException e){
                System.out.println("ERROR: Database could not be edited.");
                e.printStackTrace();
                logger.write("ERROR: Database could not be edited.");
                logger.write(e.getMessage());
                return false;
            }
        }

        //TODO maybe different method

        //Writes tempFile with original file content but
        //without line that contains the id of parameter
        try{
            FileWriter fw = new FileWriter(tempFile.getName(), true);

            Scanner fileText = new Scanner(file);

            int idFile;
            String line;
            String [] splittedLine;

            while(fileText.hasNextLine()){
                line = fileText.nextLine();
                splittedLine = line.split(",");
                idFile = Integer.parseInt(splittedLine[0]);
                if(idFile != id){
                    fw.write(line+"\n");
                }
            }
            fileText.close();
            fw.close();
        }catch(IOException e){
            System.out.println("ERROR: Could not write in database.txt");
            e.printStackTrace();
            logger.write("ERROR: Could not write in database.txt");
            logger.write(e.getMessage());
            return false;
        }

        //Replace original file by temp file
        file.delete();
        file = new File("./databases/databaseClient.txt");
        tempFile.renameTo(file);

        return true;
    }

}
