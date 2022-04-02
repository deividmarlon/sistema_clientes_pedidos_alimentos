import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Report {

    private File file;

    private static final String SOFTWARE_NAME = "Food orders management system";

    private static final String AUTHORS_NAME = "Authors: Daniel Zonta Ojeda, Deivid Marlon";

    public boolean createReportFile(String fileName){
        boolean flag = false;

        Date now = new Date();
        SimpleDateFormat datePattern = new SimpleDateFormat ("yyyy-MM-dd'_'HH-mm-ss-SS");

        file = new File(fileName+datePattern.format(now)+".txt");

        //Checks if file exists, if it doesn't, create file
        if(!file.exists()){
            System.out.println("Creating report file: " + file.getName() + " ...");
            try{
                if(file.createNewFile()) System.out.println(file.getName() + " created!");
                flag = true;
            }catch(IOException e){
                System.out.println("ERROR: Report file could not be created.");
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    public void writeHeader(int option){

        String reportTitle = "";

        if(option==1){
            reportTitle = "CLIENTS REPORT";
        }else{
            reportTitle = "FOODS   REPORT";
        }

        try{
            FileWriter reportFile = new FileWriter(file.getName(), true);

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
            System.out.println("ERROR: Could not write in report file");
            e.printStackTrace();
        }
    }


    //List foods
    //pre-conditions: none
    //post-conditions: list all foods in database
    private void writeFoods(){
        try{
            FileWriter reportFile = new FileWriter(file.getName(), true);

            FoodRepository foodRepository = new FoodRepository();

            ArrayList<FoodEntity> foods = foodRepository.index();

            if(foods.isEmpty()){
                reportFile.write("Any food was found in database");
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
                    }
                });
            }

            reportFile.close();

        }catch(IOException e){
            System.out.println("ERROR: Could not write in report file");
            e.printStackTrace();
        }
    }

    public void generateReportFoods(){

            if(createReportFile("Foods Report ")){
                writeHeader(2);
                writeFoods();
            }

    }




}
