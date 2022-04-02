//****************************************************************************************************
//**********                         Food orders management system                          **********
//**********                                                                                **********
//**********                                                                                **********
//**********                   Authors: Daniel Zonta Ojeda, Deivid Marlon                   **********
//****************************************************************************************************

public class Main {
    public static void main(String[] args) {

        int choiceMainMenu, choiceSubMenu;

        Screen screen = new Screen();

        //prints main menu on screen and gets user choice
        //repeats until user chooses to exit program
        do {
            //prints header on screen
            screen.startScreen();

            //prints main menu and gets user input
            choiceMainMenu = screen.mainMenu();

            switch (choiceMainMenu) {
                case 1:
                    do {
                        //prints client menu and gets user choice
                        //repeats until user chooses to exit to main menu
                        choiceSubMenu = screen.clientMenu();
                        if (choiceSubMenu != 0) {
                            ClientInteractions clientInteractions = new ClientInteractions();
                            clientInteractions.interact(choiceSubMenu);
                            //TODO report.printClient()
                        }
                    }while(choiceSubMenu != 0);
                    break;
                case 2:
                    do {
                        choiceSubMenu = screen.foodMenu();
                        if (choiceSubMenu != 0) {
                            //TODO create food class
                            FoodInteractions foodInteractions = new FoodInteractions();
                            foodInteractions.interact(choiceSubMenu);
                        }
                    }while(choiceSubMenu != 0);
                    break;
                case 3:
                    do {
                        choiceSubMenu = screen.reportMenu();
                        if (choiceSubMenu != 0) {
                            //TODO create report class
                            ReportInteractions reportInteractions = new ReportInteractions();
                            reportInteractions.interact(choiceSubMenu);
                        }
                    }while(choiceSubMenu != 0);
                    break;
            }
        }while(choiceMainMenu != 0);

        System.out.println("Thank you for using our software!");
    }
}
