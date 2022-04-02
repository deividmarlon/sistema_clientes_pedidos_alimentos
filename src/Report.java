import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Report {

    private File file;

    private Logger logger = new Logger();

    private static final String SOFTWARE_NAME = "Foods and clients management system";

    private static final String AUTHORS_NAME = "Authors: Daniel Zonta Ojeda, Deivid Marlon";

    //List foods
    //pre-conditions: none
    //post-conditions: list all foods in database
    private void writeClients(){
        try{
            FileWriter reportFile = new FileWriter(file.getAbsolutePath(), true);
            ClientRepository clientRepository = new ClientRepository();

            //gets client list
            ArrayList<ClientEntity> clients = clientRepository.index();

            if(clients.isEmpty()){
                reportFile.write("No client was found in database");
            }else{
                //sorts by id
                clients.sort(Comparator.comparing(client -> client.id));
                //writes clients
                clients.forEach((client) -> {
                    try{
                        reportFile.write("Id: " + client.id + "\n");
                        reportFile.write("Name: " + client.name + "\n");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        reportFile.write("Birth Day: " + client.birthDate.format(formatter) + "\n");
                        reportFile.write("Travels: " + client.travels + "\n");

                        String foodHistory = "[";
                        for(Integer i : client.foodsHistory){
                            foodHistory = foodHistory.concat(i.toString() + ".");
                        }
                        foodHistory = foodHistory.concat("]");
                        reportFile.write("Active foods: " + foodHistory + "\n");

                        String foodFullHistory = "[";
                        for(Integer i : client.foodsFullHistory){
                            foodFullHistory = foodFullHistory.concat(i.toString() + ".");
                        }
                        foodFullHistory = foodFullHistory.concat("]");
                        reportFile.write("Full food history: " + foodFullHistory + "\n");

                        reportFile.write("\n");
                    }catch(IOException e){
                        System.out.println("ERROR: Could not write in report file.");
                        e.printStackTrace();
                        logger.write("ERROR: Could not write in report file.");
                        logger.write(e.getMessage());
                    }
                });
            }

            reportFile.close();

        }catch(IOException e){
            System.out.println("ERROR: Could not write in report file.");
            e.printStackTrace();
            logger.write("ERROR: Could not write in report file.");
            logger.write(e.getMessage());
        }
    }

    //creates file to store a report
    //pre-conditions: none
    //post-conditions: report file created
    public boolean createReportFile(String fileName){
        boolean flag = false;

        Date now = new Date();
        SimpleDateFormat datePattern = new SimpleDateFormat ("yyyy-MM-dd'_'HH-mm-ss-SS");

        new File("./reports").mkdirs();
        file = new File("./reports/"+fileName+datePattern.format(now)+".txt");

        //Checks if file exists, if it doesn't, create file
        if(!file.exists()){
            System.out.println("Creating report file: " + file.getName() + " ...");
            try{
                if(file.createNewFile()) System.out.println(file.getName() + " created in reports directory!");
                flag = true;
            }catch(IOException e){
                System.out.println("ERROR: Report file could not be created.");
                e.printStackTrace();
                logger.write("ERROR: Report file could not be created.");
                logger.write(e.getMessage());
                flag = false;
            }
        }
        return flag;
    }

    //writes header in report file
    //pre-conditions: none
    //post-conditions: header written in report file
    public void writeHeader(int option){

        String reportTitle = "";

        if(option==1){
            reportTitle = "CLIENTS REPORT";
        }else{
            reportTitle = "FOODS   REPORT";
        }

        try{
            FileWriter reportFile = new FileWriter(file.getAbsolutePath(), true);

            int i;
            int messageSize = SOFTWARE_NAME.length();

            Validation validate = new Validation();

            for(i = 0; i < 100;i++) reportFile.write("*");
            reportFile.write("\n");

            for(i = 0; i < 10; i++) reportFile.write("*");
            for(i = 0; i < ((80-messageSize)/2); i++) reportFile.write(" ");
            reportFile.write(SOFTWARE_NAME);
            //prints an even number of spaces if message size is even
            //prints odd number of spaces if message size is odd
            if(validate.isEven(messageSize)) {
                for (i = 0; i < ((80 - messageSize) / 2); i++) reportFile.write(" ");
            }
            else {
                for (i = 0; i < ((80 + 1 - messageSize) / 2); i++) reportFile.write(" ");
            }
            for(i = 0; i < 10; i++) reportFile.write("*");
            reportFile.write("\n");

            for (i = 0; i < 10; i++) reportFile.write("*");
            for (i = 0; i < 19; i++) reportFile.write(" ");
            reportFile.write(AUTHORS_NAME);
            for (i = 0; i < 19; i++) reportFile.write(" ");
            for (i = 0; i < 10; i++) reportFile.write("*");
            reportFile.write("\n");

            for(int j = 0; j < 3; j++) {
                if(j==1){
                    for (i = 0; i < 10; i++) reportFile.write("*");
                    for (i = 0; i < 33; i++) reportFile.write(" ");
                    reportFile.write(reportTitle);
                    for (i = 0; i < 33; i++) reportFile.write(" ");
                    for (i = 0; i < 10; i++) reportFile.write("*");
                    reportFile.write("\n");
                }else{
                    for (i = 0; i < 10; i++) reportFile.write("*");
                    for (i = 0; i < 80; i++) reportFile.write(" ");
                    for (i = 0; i < 10; i++) reportFile.write("*");
                    reportFile.write("\n");
                }
            }

            for(i = 0; i < 100;i++) reportFile.write("*");
            reportFile.write("\n\n");
            reportFile.close();

        }catch(IOException e){
            System.out.println("ERROR: Could not write in report file.");
            e.printStackTrace();
            logger.write("ERROR: Could not write in report file.");
            logger.write(e.getMessage());
        }
    }


    //List foods
    //pre-conditions: none
    //post-conditions: list all foods in database
    private void writeFoods(){
        try{
            FileWriter reportFile = new FileWriter(file.getAbsolutePath(), true);
            FoodRepository foodRepository = new FoodRepository();

            //gets food list
            ArrayList<FoodEntity> foods = foodRepository.index();

            if(foods.isEmpty()){
                reportFile.write("No food was found in database");
            }else{

                foods.sort(Comparator.comparing(food -> food.id));

                foods.forEach((food) -> {
                    try{
                        reportFile.write("Id: " + food.id + "\n");
                        reportFile.write("Name: " + food.name + "\n");
                        reportFile.write("Calories: " + food.calories + "\n");
                        reportFile.write("Price: " + food.price + "\n");
                        reportFile.write("\n");
                    }catch(IOException e){
                        System.out.println("ERROR: Could not write in report file");
                        e.printStackTrace();
                        logger.write("ERROR: Could not write in report file.");
                        logger.write(e.getMessage());
                    }
                });
            }

            reportFile.close();

        }catch(IOException e){
            System.out.println("ERROR: Could not write in report file");
            e.printStackTrace();
            logger.write("ERROR: Could not write in report file.");
            logger.write(e.getMessage());
        }
    }

    //call functions necessary to make client report file
    //pre-conditions: none
    //post-conditions: client report written
    public void generateReportClients(){

        if(createReportFile("Clients Report ")){
            writeHeader(1);
            writeClients();
        }

    }

    //call functions necessary to make food report file
    //pre-conditions: none
    //post-conditions: food report written
    public void generateReportFoods(){

            if(createReportFile("Foods Report ")){
                writeHeader(2);
                writeFoods();
            }

    }

}
