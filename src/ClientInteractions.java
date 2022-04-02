import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ClientInteractions {

    private Logger logger = new Logger();

    //Asks user for the client information and saves it to this instance
    //pre-conditions: none
    //post-conditions: information saved
    private void createClient(){

        String[] date;
        int day, month, year;

        ClientEntity newClient = new ClientEntity();

        Validation validate = new Validation();
        Scanner scan = new Scanner(System.in);
        ClientRepository clientRepository = new ClientRepository();

        do {
            System.out.println("ID: (optional - input a 0)");
            newClient.id = validate.getValidId("databaseClient.txt");
        }while(newClient.id < 0 );

        System.out.println("Name:");
        newClient.name = scan.nextLine();

        do {
            System.out.println("Birth date: (dd-mm-aaaa)");
            String temp = scan.nextLine();
            date = temp.split("-");
            if(!validate.validateDate(date)){
                System.out.println("ERROR: Invalid date");
                System.out.println();
                logger.write("ERROR: Invalid date");
            }
        }while(!validate.validateDate(date));

        day = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);
        newClient.birthDate = LocalDate.of(year, month, day);

        do{
            System.out.println("Number of client travels:");
            newClient.travels = validate.getValidInt();
        }while(newClient.travels < 0);

        clientRepository.save(newClient);

    }

    //asks user for client id, if it finds this client will
    //ask user which field to edit
    //pre-conditions: none
    //post-conditions: desired field edited or a error message
    private void editClient(){

        boolean valid = false;
        int id, choiceEdit, travels;

        ClientRepository clientRepository = new ClientRepository();
        Screen screen = new Screen();
        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();

        do{
            System.out.println("Insert client id.");
            if(scan.hasNextInt()){
                id = scan.nextInt();
                scan.nextLine();
                ClientEntity client = clientRepository.findById(id);
                if(client != null){
                    choiceEdit = screen.editClientMenu();
                    switch (choiceEdit){
                        case 1:{
                            System.out.println("Insert a new name:");
                            client.name = scan.nextLine();
                            clientRepository.update(client);
                            break;
                        }
                        case 2:{
                            String[] date;
                            int day, month, year;
                            do {
                                System.out.println("Insert new birth date: (dd-mm-aaaa)");
                                String temp = scan.nextLine();
                                date = temp.split("-");
                                //TODO create bool date
                                if(!validate.validateDate(date)){
                                    System.out.println("ERROR: Invalid date");
                                    System.out.println();
                                    logger.write("ERROR: Invalid date");
                                }

                            }while(!validate.validateDate(date));

                            day = Integer.parseInt(date[0]);
                            month = Integer.parseInt(date[1]);
                            year = Integer.parseInt(date[2]);
                            LocalDate birthDate = LocalDate.of(year, month, day);
                            client.birthDate = birthDate;
                            clientRepository.update(client);
                            break;
                        }
                        case 3:{
                            System.out.println("Insert new number of travels:");
                            travels = validate.getValidInt();
                            client.travels = travels;
                            clientRepository.update(client);
                            break;
                        }
                        case 4:{
                            FoodInteractions foods = new FoodInteractions();
                            FoodEntity foundFood = new FoodEntity();
                            FoodRepository foodRepository = new FoodRepository();

                            //prints food list
                            foods.interact(5);

                            do {
                                System.out.println("Insert food id - 0 to stop");
                                if (scan.hasNextInt()) {
                                    foundFood.id = scan.nextInt();
                                    id = foundFood.id;
                                    foundFood = foodRepository.findById(foundFood.id);
                                    if (foundFood != null ) {
                                        client.foodsFullHistory.add(foundFood.id);
                                        client.foodsHistory.add(foundFood.id);
                                    }
                                }
                            }while(id != 0);
                            clientRepository.update(client);
                            break;
                        }
                    }
                }else {
                    System.out.println("Client doesn't exist.");
                    logger.write("ERROR: Invalid date");
                }
                valid = true;
            }else{
                System.out.println("Invalid id.");
                System.out.println();
                logger.write("Invalid id.");
                scan.nextLine();
            }
        }while(valid == false);

    }

    //asks user for client id and tries to find it in database
    //pre-conditions: none
    //post-conditions: creates a class instance if client id exists
    private void showClient(){

        boolean valid = false;

        ClientRepository clientRepository = new ClientRepository();
        ClientEntity clientFound = new ClientEntity();
        Scanner scan = new Scanner(System.in);
        Screen screen = new Screen();

        do {
            System.out.println("Insert client id");
            if (scan.hasNextInt()) {
                clientFound.id = scan.nextInt();
                clientFound = clientRepository.findById(clientFound.id);
                if (clientFound != null && clientFound.id != 0){
                    screen.clientHeader();
                    screen.printClient(clientFound);
                }
                else {
                    System.out.println("Client doesn't exist.");
                    logger.write("Client doesn't exist.");
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

    //asks user for client id and tries to delete it from database
    //pre-conditions: none
    //post-conditions: client deleted from database or error message
    private void deleteClient(){

        boolean valid = false;
        int id;

        Scanner scan = new Scanner(System.in);
        ClientRepository clientRepository = new ClientRepository();

        //TODO list clients
        do{
            System.out.println("Insert client id");
            if(scan.hasNextInt()){
                id = scan.nextInt();
                if(clientRepository.delete(id))
                    System.out.println("Client deleted!");
                else{
                    System.out.println("Client doesn't exist.");
                    logger.write("Client doesn't exist.");
                }
                valid = true;

            }else{
                System.out.println("Invalid id.");
                System.out.println();
                logger.write("Invalid id.");
                scan.nextLine();
            }
        }while(valid == false);
    }

    //List clients
    //pre-conditions: none
    //post-conditions: list all clients in database
    private void listClients(){

        ClientRepository clientRepository = new ClientRepository();
        Screen screen = new Screen();

        ArrayList<ClientEntity> clients = clientRepository.index();

        if(clients.isEmpty()){
            System.out.println("No client was found in database");
        }else{
            System.out.println("CLIENT LIST:\n");

            screen.clientHeader();

            clients.sort(Comparator.comparing(client -> client.id));

            clients.forEach((client) -> {
                screen.printClient(client);
            });
            System.out.println();
        }
    }

    //removes food from the foodHistory arraylist
    //pre-conditions: none
    //post-conditions: foods with id from parameter removed from all client's foodHistory
    public void removeFoods(int id){
        ClientRepository database = new ClientRepository();
        ArrayList<ClientEntity> clientList = new ArrayList<ClientEntity>(0);

        clientList = database.index();


        clientList.forEach((client) -> {
            for(int i=0;i < client.foodsHistory.size(); i++)
                if(client.foodsHistory.get(i)==id) {
                    client.foodsHistory.remove(i);
                    i--;
                }
            database.update(client);
        });
    }

    //gets a string with numbers and returns an arraylist\
    //pre-conditions: string formatted correctly [n.n.n...]
    //post-conditions: arraylist returned
    public ArrayList<Integer> getFoodHistory(String s){
        s = s.substring(s.indexOf("[") + 1);
        s = s.substring(0, s.indexOf("]"));
        String[] foodHistory = s.split("\\.");
        ArrayList<Integer> list = new ArrayList<Integer>(0);

        if(foodHistory[0] != "") {
            for(String i : foodHistory) {
                list.add(Integer.parseInt(i));
            }
        }
        return list;
    }

    //manages the user choice from the client sub menu
    //pre-conditions: none
    //post-conditions:
    public void interact(int userChoice){
        Screen screen = new Screen();
        switch(userChoice){
            case 1: createClient();
                    listClients();
                    screen.waitInput();
                    break;
            case 2: listClients();
                    editClient();
                    listClients();
                    screen.waitInput();
                break;
            case 3: deleteClient();
                    listClients();
                    screen.waitInput();
                break;
            case 4: showClient();
                    screen.waitInput();
                break;
            case 5: listClients();
                    screen.waitInput();
                break;
            default: break;
        }
    }


}
