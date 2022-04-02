import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private File file;

    Logger(){
        new File("./logs").mkdirs();
        file = new File("./logs/systemLogs.txt");

        //Checks if file exists, if it doesn't, create file
        if(!file.exists()){
            System.out.println("Logs file not found!");
            System.out.println("Creating new " + file.getName() + "...");
            try{
                if(file.createNewFile()) System.out.println(file.getName() + " created!");
            }catch(IOException e){
                System.out.println("ERROR: Logs file could not be created.");
                e.printStackTrace();
            }
        }
    }

    //Writes log into systemLogs file
    //pre-conditions: Logs file created
    //post-conditions: log inserted into systemLogs
    public boolean write(String log) {

        if (!file.exists()) {
            System.out.println("ERROR: Could not write in systemLogs.txt");
            return false;
        }

        //Prepares data string to write in log file
        Date now = new Date();
        SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd'_'HH-mm-ss-SS");

        String data = datePattern.format(now) + ": " + log + "\n";

        //Writes data string in log file
        try {
            FileWriter database = new FileWriter(file.getAbsolutePath(), true);
            database.write(data);
            database.close();
            return true;
        } catch (IOException e) {
            System.out.println("ERROR: Could not write in systemLogs.txt");
            e.printStackTrace();
            return false;
        }
    }
}
