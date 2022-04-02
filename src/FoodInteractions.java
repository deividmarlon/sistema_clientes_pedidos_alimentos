import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FoodInteractions {

    private Logger logger = new Logger();

    //Asks user for the food information and saves it to this instance
    //pre-conditions: none
    //post-conditions: food saved
    private void createFood(){

        FoodEntity newFood = new FoodEntity();

        Validation validate = new Validation();
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("ID: (optional - input a 0)");
            newFood.id = validate.getValidId("databaseFood.txt");
        }while(newFood.id < 0 );

        System.out.println("Name:");
        newFood.name = scan.nextLine();

        System.out.println("Brand:");
        newFood.brand = scan.nextLine();

        do{
            System.out.println("Number of calories:");
            newFood.calories = validate.getValidDouble();
        }while(newFood.calories < 0);

        do{
            System.out.println("Price:");
            newFood.price = validate.getValidDouble();
        }while(newFood.price < 0);

        FoodRepository foodRepository = new FoodRepository();

        foodRepository.save(newFood);

    }
    //Asks user for food id and tries to update it database
    //pre-conditions: food with target id exists
    //post-conditions: food deleted
    private void editFood(){

        boolean valid = false;
        int id;
        int choiceEdit;

        FoodEntity food;
        FoodRepository foodRepository = new FoodRepository();
        Screen screen = new Screen();
        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();

        do {
            System.out.println("Insert food id");
            if (scan.hasNextInt()) {
                id = scan.nextInt();
                scan.nextLine();
                food = foodRepository.findById(id);
                if (food!=null){
                    choiceEdit = screen.editFoodMenu();
                    switch(choiceEdit){
                        case 1: {
                            System.out.println("Insert a new name:");
                            food.name = scan.nextLine();
                            foodRepository.update(food);
                            break;
                        }
                        case 2: {
                            System.out.println("Insert a new brand:");
                            food.name = scan.nextLine();
                            foodRepository.update(food);
                            break;
                        }
                        case 3: {
                            System.out.println("Insert a new number of calories:");
                            food.calories = validate.getValidDouble();
                            foodRepository.update(food);
                            break;
                        }
                        case 4: {
                            System.out.println("Insert a new price:");
                            food.price = validate.getValidDouble();
                            foodRepository.update(food);
                            break;
                        }
                        default: break;
                    }
                    System.out.println("Food edited!");
                } else{
                    System.out.println("Food doesn't exist.");
                }
                valid = true;
            } else {
                System.out.println("Invalid id.");
                System.out.println();
                logger.write("Invalid id.");
                scan.nextLine();
            }
        }while(valid == false);

    }

    //Asks user for food id and tries to find it in database
    //pre-conditions: food with target id exists
    //post-conditions: show food information if food id exists
    private void showFood(){

        boolean valid = false;

        FoodEntity foundFood = new FoodEntity();
        FoodRepository foodRepository = new FoodRepository();
        Scanner scan = new Scanner(System.in);
        Screen screen = new Screen();

        do {
            System.out.println("Insert food id");
            if (scan.hasNextInt()) {
                foundFood.id = scan.nextInt();
                foundFood = foodRepository.findById(foundFood.id);
                if (foundFood != null && foundFood.id != 0 ){
                    screen.printFoodHeader();
                    screen.printFood(foundFood);
                }
                else System.out.println("Food doesn't exist.");
                valid = true;
            } else {
                System.out.println("Invalid id.");
                System.out.println();
                logger.write("Invalid id.");
                scan.nextLine();
            }
        }while(valid == false);

    }

    //Asks user for food id and tries to delete it from database
    //pre-conditions: food with target id exists
    //post-conditions: food deleted
    private void deleteFood(){

        boolean valid = false;

        int id;

        FoodRepository foodRepository = new FoodRepository();

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Insert food id");
            if (scan.hasNextInt()) {
                id = scan.nextInt();
                if (foodRepository.delete(id)){
                    System.out.println("Food deleted!");
                } else{
                    System.out.println("Food doesn't exist.");
                }
                valid = true;
            } else {
                System.out.println("Invalid id.");
                System.out.println();
                logger.write("Invalid id.");
                scan.nextLine();
            }
        }while(valid == false);

    }

    //List foods
    //pre-conditions: none
    //post-conditions: list all foods in database
    private void listFoods(){

        FoodRepository foodRepository = new FoodRepository();
        Screen screen = new Screen();

        ArrayList<FoodEntity> foods = foodRepository.index();

        if(foods.isEmpty()){
            System.out.println("Any food was found in database");
        }else{
            System.out.println("FOOD LIST:\n");

            foods.sort(Comparator.comparing(food -> food.id));

            foods.forEach((food) -> {
                screen.printFood(food);
            });
        }
    }

    //manages the user choice from the client sub menu
    //pre-conditions: none
    //post-conditions:
    public void interact(int userChoice){
        Screen screen = new Screen();
        switch(userChoice){
            case 1: createFood();
                    listFoods();
                    screen.waitInput();
                break;
            case 2: editFood();
                    listFoods();
                    screen.waitInput();
                break;
            case 3: deleteFood();
                    listFoods();
                    screen.waitInput();
                break;
            case 4: showFood();
                    screen.waitInput();
                break;
            case 5: listFoods();
                break;
            default: break;
        }
    }

}
