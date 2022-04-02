import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FoodInteractions {

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

        FoodRepository foodRepository = new FoodRepository();

        foodRepository.save(newFood);


    }

    //Asks user for food id and tries to update it database
    //pre-conditions: food with target id exists
    //post-conditions: food deleted
    private void editFood(){

        boolean valid = false;
        int id;

        FoodEntity food;
        FoodRepository foodRepository = new FoodRepository();

        int choiceEdit;
        Screen screen = new Screen();

        Scanner scan = new Scanner(System.in);

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
                            foodRepository.update(food); //TODO here
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

        do {
            System.out.println("Insert food id");
            if (scan.hasNextInt()) {
                foundFood.id = scan.nextInt();
                foundFood = foodRepository.findById(foundFood.id);
                if (foundFood != null){
                    System.out.println("Food found:");
                    System.out.println("ID: " + foundFood.id);
                    System.out.println("Name: " + foundFood.name);
                }
                else System.out.println("Food doesn't exist.");
                valid = true;
            } else {
                System.out.println("Invalid id.");
                System.out.println();
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
                System.out.println("Id: " + food.id);
                System.out.println("Name: " + food.name);
                System.out.println("Calories: " + food.calories);
                System.out.println("Price: " + food.price);
                System.out.println("");
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
