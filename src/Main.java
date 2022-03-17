public class Main {
    public static void main(String[] args) {

        int choiceMainMenu, choiceSubMenu;

        Interface screen = new Interface();

        //prints main menu on screen and gets user choice
        //repeats until user chooses to exit program
        do {
            //prints header on screen
            screen.startScreen();

            //prints main menu
            choiceMainMenu = screen.mainMenu();

            switch (choiceMainMenu) {
                case 1:
                    do {
                        choiceSubMenu = screen.clientMenu();
                        if (choiceSubMenu != 0) {
                            Client client = new Client();
                            client.configClient(choiceSubMenu);
                            //TODO report.printClient()
                        }
                    }while(choiceSubMenu != 0);
                    break;
                case 2:
                    do {
                        choiceSubMenu = screen.foodMenu();
                        if (choiceSubMenu != 0) {
                            //TODO create food class
                            //Food food = new Food();
                            //food.configFood(choiceSubMenu);
                        }
                    }while(choiceSubMenu != 0);
                    break;
                case 3:
                    do {
                        choiceSubMenu = screen.reportMenu();
                        if (choiceSubMenu != 0) {
                            //TODO create report class
                            //Report report = new Report
                            //report.configReport(choiceSubMenu);
                        }
                    }while(choiceSubMenu != 0);
                    break;
            }
        }while(choiceMainMenu != 0);
        System.out.println("Thank you for using our software!");
    }
}
