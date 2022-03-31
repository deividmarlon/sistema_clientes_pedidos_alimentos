import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class ClientRepository {

    private File file;

    ClientRepository(){
       file = new File("databaseClient.txt");
    }

    public boolean deleteClient(int id){
        if(findClient(id) == null) return false;

        if(!file.exists()) return false;

        File tempFile = new File("databaseClient_temp.txt");


        //Checks if tempFile exists, if it doesn't, create tempFile
        if(!tempFile.exists()){
            try{
                tempFile.createNewFile();
            }catch(IOException e){
                System.out.println("ERROR: Database could not be edited.");
                e.printStackTrace();
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
            return false;
        }

        //Replace original file by temp file
        file.delete();
        file = new File("databaseClient.txt");
        tempFile.renameTo(file);

        return true;
    }

    public ClientEntity findClient(int id){
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
                    scan.close();
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
                return false;
            }
        }

        //Prepares data string to write in database
        String data = client.id +","+ client.name + "," + client.birthDate;

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
            return false;
        }

        //Replace original file by temp file

        file.delete();
        file = new File("databaseClient.txt");
        tempFile.renameTo(file);

        return true;
    }

}
