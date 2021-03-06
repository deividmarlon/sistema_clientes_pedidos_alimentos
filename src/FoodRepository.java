import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodRepository {

    private File file;

    private Logger logger = new Logger();

    FoodRepository(){
        new File("./databases/").mkdirs();
        file = new File("./databases/databaseFood.txt");

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

    //returns an arraylist with all foods from database
    //pre-conditions: none
    //post-conditions: arraylist returned
    public ArrayList<FoodEntity> index(){

        ArrayList<FoodEntity> foods = new ArrayList<FoodEntity>(0);
        FoodEntity food;

        if(!file.exists()){
            return foods;
        }

        try{
            Scanner fileText = new Scanner(file);
            String line;
            String [] splittedLine;
            while(fileText.hasNextLine()){
                line=fileText.nextLine();
                splittedLine = line.split(",");
                food = new FoodEntity();
                food.id = Integer.parseInt(splittedLine[0]);
                food.name = splittedLine[1];
                food.brand = splittedLine[2];
                food.calories = Double.parseDouble(splittedLine[3]);
                food.price = Double.parseDouble(splittedLine[4]);
                foods.add(food);
            }
            fileText.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            logger.write(e.getMessage());
        }
        return foods;
    }

    //Inserts food values into database
    //pre-conditions: valid values in class variables
    //post-conditions: information inserted into database
    public boolean save(FoodEntity food){

        if(!file.exists()){
            System.out.println("ERROR: Could not write in database.txt");
            logger.write("ERROR: Could not write in database.txt");
            return false;
        }

        //Prepares data string to write in database
        String data = food.id +","+ food.name +","+ food.brand +","+ food.calories +","+ food.price + "\n";

        //Writes data string in database
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

    //will try to find food with same id as parameter and return
    //a FoodEntity object with its information. returns null if it doesn't find
    //the id
    //pre-conditions: none
    //post-conditions: returns FoodEntity or null
    public FoodEntity findById(int id){

        FoodEntity food = new FoodEntity();

        if(!file.exists()) return null;

        try{
            Scanner fileText = new Scanner(file);
            int idFile;
            String line;
            String [] splittedLine;
            //fileText.useDelimiter(",");
            while(fileText.hasNextLine()){
                line=fileText.nextLine();
                splittedLine = line.split(",");
                idFile = Integer.parseInt(splittedLine[0]);
                if(idFile == id) {
                    food.id = idFile;
                    food.name = splittedLine[1];
                    food.brand = splittedLine[2];
                    food.calories = Double.parseDouble(splittedLine[3]);
                    food.price = Double.parseDouble(splittedLine[4]);
                    fileText.close();
                    return food;
                }
            }
            fileText.close();
            return null;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            logger.write(e.getMessage());
            return null;
        }

    }

    //will create a temp database file, and replace the line with food.id
    //with the food information passed as parameter
    //pre-conditions: none
    //post-conditions: food edited
    public boolean update(FoodEntity food){
        if(!file.exists()) return false;

        File tempFile = new File("databaseFood_temp.txt");

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

        //Prepares data string to write in database
        String data = food.id +","+ food.name +","+ food.brand +","+ food.calories +","+ food.price + "\n";

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
                if(idFile == food.id){
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
        file = new File("./databases/databaseFood.txt");
        tempFile.renameTo(file);

        return true;
    }

    //will try to delete food with same id as parameter. returns true if succeeded
    //and false if it fails
    //pre-conditions: none
    //post-conditions: returns a boolean and tries to delete food from database
    public boolean delete(int id){
        if(findById(id) == null) return false;

        if(!file.exists()) return false;

        File tempFile = new File("databaseFood_temp.txt");

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

        //Writes tempFile with original file content but
        //without line that contains the id of parameter
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
                if(idFile != id){
                    fw.write(line+"\n");
                }else{
                    ClientInteractions client = new ClientInteractions();
                    client.removeFoods(id);
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
        file = new File("./databases/databaseFood.txt");
        tempFile.renameTo(file);

        return true;
    }

}