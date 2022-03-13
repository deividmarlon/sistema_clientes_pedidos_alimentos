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
                    choiceSubMenu = screen.clientMenu();
                    if (choiceSubMenu != 0) {
                        //TODO create client class
                        //Client client = new Client();
                        //client.configClient(choiceSubMenu);
                    }
                    break;
                case 2:
                    choiceSubMenu = screen.foodMenu();
                    if (choiceSubMenu != 0) {
                        //TODO create food class
                        //Food food = new Food();
                        //food.configFood(choiceSubMenu);
                    }
                    break;
                case 3:
                    choiceSubMenu = screen.reportMenu();
                    if(choiceSubMenu != 0){
                        //TODO create report class
                        //Report report = new Report
                        //report.configReport(choiceSubMenu);
                    }
                    break;
            }
        }while(choiceMainMenu != 0);
        System.out.println("Thank you for using our software!");
    }
}
