import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ReportInteractions {

    //Generate a report file with clients data
    //pre-conditions: none
    //post-conditions: list all foods in database
    private void reportClients(){

        FoodRepository foodRepository = new FoodRepository();

        ArrayList<FoodEntity> foods = foodRepository.index();

        if(foods.isEmpty()){
            System.out.println("Any food was found in database");
        }else{
            System.out.println("FOOD LIST:\n");

            foods.sort(Comparator.comparing(food -> food.id));

            foods.forEach((food) -> {
                System.out.println("Id: " + food.id);
                System.out.println("Name: " + food.name);
                System.out.println("");
            });
        }

    }

    //Generate a report file with foods data
    //pre-conditions: none
    //post-conditions: list all foods in database
    private void reportFoods(){

        Report report = new Report();

        report.generateReportFoods();

    }

    //manages the user choice from the client sub menu
    //pre-conditions: none
    //post-conditions:
    public void interact(int userChoice){
        switch(userChoice){
            case 1: reportClients();
                break;
            case 2: reportFoods();
                break;
            default: break;
        }
    }

}
