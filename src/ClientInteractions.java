import java.time.LocalDate;
import java.util.Scanner;

public class ClientInteractions {

    private void editClient(){

        boolean valid = false;
        int id, choiceEdit;

        ClientRepository clientRepository = new ClientRepository();
        Screen screen = new Screen();
        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();

        do{
            System.out.println("Insert client id.");
            if(scan.hasNextInt()){
                id = scan.nextInt();
                scan.nextLine();
                ClientEntity client = clientRepository.findClient(id);
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
                                }

                            }while(!validate.validateDate(date));

                            day = Integer.parseInt(date[0]);
                            month = Integer.parseInt(date[1]);
                            year = Integer.parseInt(date[2]);
                            LocalDate birthDate = LocalDate.of(year, month, day);
                            client.birthDate = birthDate;
                            clientRepository.update(client);
                        }
                    }
                }else System.out.println("Client doesn't exist.");
                valid = true;
            }else{
                System.out.println("Invalid id.");
                System.out.println();
                scan.nextLine();
            }
        }while(valid == false);

    }

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
                if(clientRepository.deleteClient(id))
                    System.out.println("Client deleted!");
                else
                    System.out.println("Client doesn't exist.");
                valid = true;

            }else{
                System.out.println("Invalid id.");
                System.out.println();
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
                clientFound = clientRepository.findClient(clientFound.id);
                if (clientFound != null){
                    screen.printClient(clientFound);
                }
                else System.out.println("Client doesn't exist.");
                valid = true;
            } else {
                System.out.println("Invalid id.");
                System.out.println();
                scan.nextLine();
            }
        }while(valid == false);

        screen.waitInput();
    }

    //Asks user for the client information and saves it to this instance
    //pre-conditions: none
    //post-conditions: information saved
    private void createClient(){
        String name;
        String[] date;
        int id, day, month, year;

        Validation validate = new Validation();
        Scanner scan = new Scanner(System.in);
        ClientRepository clientRepository = new ClientRepository();

        do {
            System.out.println("ID: (optional - input a 0)");
            id = validate.getValidId("databaseClient.txt");
        }while(id < 0 );

        System.out.println("Name:");
        name = scan.nextLine();

        do {
            System.out.println("Birth date: (dd-mm-aaaa)");
            String temp = scan.nextLine();
            date = temp.split("-");
            //TODO create bool date
            if(!validate.validateDate(date)){
                System.out.println("ERROR: Invalid date");
                System.out.println();
            }

        }while(!validate.validateDate(date));

        day = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);
        LocalDate birthDate = LocalDate.of(year, month, day);

//        ClientEntity client = new ClientEntity();

//        client.id = id;
//        client.name = name;
//        client.birthDate = birthDate;

        clientRepository.insertClient(id, name, birthDate);
        //TODO viajens??

    }

    //manages the user choice from the client sub menu
    //pre-conditions: none
    //post-conditions:
    public void configClient(int userChoice){
        switch(userChoice){
            case 1: createClient();
                    break;
            case 2: editClient();
                    break;
            case 3: deleteClient();
                    break;
            case 4: showClient();
            default: break;
        }
    }

}
