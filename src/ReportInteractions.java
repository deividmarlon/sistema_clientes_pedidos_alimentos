import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ReportInteractions {

    //Generate a report file with clients data
    //pre-conditions: none
    //post-conditions: list all foods in database
    private void reportClients(){

        Report report = new Report();

        report.generateReportClients();

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
