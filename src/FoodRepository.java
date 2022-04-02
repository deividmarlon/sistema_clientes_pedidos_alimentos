import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodRepository {

    private File file;

    FoodRepository(){
        file = new File("databaseFood.txt");

        //Checks if file exists, if it doesn't, create file
        if(!file.exists()){
            System.out.println("Database not found\nCreating new " + file.getName() + "...");
            try{
                if(file.createNewFile()) System.out.println(file.getName() + " created!");
            }catch(IOException e){
                System.out.println("ERROR: Database could not be created.");
                e.printStackTrace();
            }
        }
    }

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
                foods.add(food);
            }
            fileText.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return foods;
    }

    public boolean save(FoodEntity food){

        if(!file.exists()){
            System.out.println("ERROR: Could not write in database.txt");
            return false;
        }

        //Prepares data string to write in database
        String data = food.id +","+ food.name;

        //Writes data string in database
        try{
            FileWriter database = new FileWriter(file.getName(), true);
            database.write(data + "\n" );
            database.close();
            return true;
        }catch(IOException e){
            System.out.println("ERROR: Could not write in database.txt");
            e.printStackTrace();
            return false;
        }
    }

    public FoodEntity findById(int id){

        FoodEntity food = new FoodEntity();

        if(!file.exists()) return null;

        try{
            Scanner fileText = new Scanner(file);
            //TODO find
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
                    fileText.close();
                    return food;
                }
            }
            fileText.close();
            return null;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }

    }

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
                return false;
            }
        }

        //Prepares data string to write in database
        String data = food.id +","+ food.name;

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
            return false;
        }

        //Replace original file by temp file

        file.delete();
        file = new File("databaseFood.txt");
        tempFile.renameTo(file);

        return true;
    }

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
            return false;
        }

        //Replace original file by temp file

        file.delete();
        file = new File("databaseFood.txt");
        tempFile.renameTo(file);

        return true;
    }
}
