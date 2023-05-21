import config.DatabaseConfiguration;
import model.*;
import repositories.AddressRepository;
import repositories.ClientRepository;
import service.impl.FoodDeliveryServiceImpl;
import utils.ClientReaderWriter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
        ClientRepository clientRepository = new ClientRepository(databaseConfiguration);
        AddressRepository addressRepository = new AddressRepository(databaseConfiguration);
        FoodDeliveryServiceImpl service = new FoodDeliveryServiceImpl();
        service.setClientRepository(clientRepository);
        service.setAddressRepository(addressRepository);
        System.out.println(service.getAllClients());

/*        Client client1 = new Client("Raluca", "Rogoza", "0234567890", "ralucar@yahoo.com");
        Client client2 = new Client("Carina", "Nicola", "0254657698", "carinan@yahoo.com");
        Client client3 = new Client("Ioana", "Maria", "0234125476", "ioanam@yahoo.com");
        service.addClient(client1);
        service.addClient(client2);
        //service.addClient(client3);
        System.out.println(client1.getId());
        System.out.println(client2.getId());
        System.out.println(client3.getId());
        //System.out.println(client1.getId());
        service.removeClient(client1);
        System.out.println(service.getAllClients());
        System.out.println(service.getClients());

        service.updateClient(client1, client3);
        System.out.println(service.getAllClients());*/

        Address address1 = new Address("Bucharest", "Bd. Mihail Kogalniceanu", 14, true);
        Address address2 = new Address("Bucharest", "Nicolae Balcescu", 5, true);
        Address address3 = new Address("Bucharest", "Bd. Carol", 2, true);
        Address address4 = new Address("Bucharest", "Dimitrie Cantemir", 33, true);
        Address address5 = new Address("Bucharest", "Mihai Bravu", 4, true);
        Address address6 = new Address("Bucharest", "Obor", 12, true);

        service.addAddress(address1);
        service.addAddress(address2);
        service.addAddress(address3);


        /*service.deleteAddress(address1);
        service.deleteAddress(address2);*/

        System.out.println(address1.getId());
        System.out.println(address4.getId());
        service.updateAddress(address1, address4);
        System.out.println(service.getAddresses());





        /*ClientReaderWriter clientReaderWriter = ClientReaderWriter.getInstance();
        List<Client> c = new ArrayList<>(clientReaderWriter.read("client-data.csv"));
        System.out.println(c);

        clientReaderWriter.write("client-data2.csv", new Client("Raluca", "Rogoza", "0234567890", "ralucar@yahoo.com"));
        clientReaderWriter.write("client-data2.csv", new Client("Carina", "Nicola", "0254657698", "carinan@yahoo.com"));
*/


/*
        FoodDeliveryServiceImpl service = new FoodDeliveryServiceImpl();
        service.addClientsFromCSVFile("src/utils/CSVfiles/clients.csv");
        System.out.println(service.getClients());*/


        /*service.addDeliveryDriversFromCSVFile("src/utils/CSVfiles/deliverydrivers.csv");
        System.out.println(service.getDeliveryDrivers());


        service.addAddressesFromCSVFile("src/utils/CSVfiles/addresses.csv");
        System.out.println(service.getAddresses());


        service.addDrinksFromCSVFile("src/utils/CSVfiles/drinks.csv");
        System.out.println(service.getDrinks());


        service.addDishesFromCSVFile("src/utils/CSVfiles/dishes.csv");
        System.out.println(service.getDishes());*/


        /*Client client1 = new Client("Raluca", "Rogoza", "0234567890", "ralucar@yahoo.com");
        Client client2 = new Client("Carina", "Nicola", "0254657698", "carinan@yahoo.com");
        Client client3 = new Client("Ioana", "Maria", "023412547627", "ioanam@yahoo.com");

        service.addClient(client1);
        service.addClient(client2);
        service.addClient(client3);


        System.out.println(service.getClients());

        DeliveryDriver deliveryDriver1 = new DeliveryDriver("Bogdan", "Mihai", "0789437295", DeliveryMethod.BICYCLE, DeliveryDriverStatus.AVAILABLE);
        DeliveryDriver deliveryDriver2 = new DeliveryDriver("Andrei", "Popa", "0358659375", DeliveryMethod.ELECTRIC_SCOOTER, DeliveryDriverStatus.AVAILABLE);
        DeliveryDriver deliveryDriver3 = new DeliveryDriver("Eduard", "Ionescu", "0732749585", DeliveryMethod.CAR, DeliveryDriverStatus.BUSY);

        Dish dish1 = new Dish("Pizza Prosciutto", false, 450, 30, 780);
        Dish dish2 = new Dish("Chicken Burger", false, 320, 40, 550);
        Dish dish3 = new Dish("Pasta with vegetables", true, 330, 45, 420);


        Drink drink1 = new Drink("Lemonade", true, 350, 18, "strawberries", false);
        Drink drink2 = new Drink("Caffee latte", false, 320, 14, "vanilla", false);
        Drink drink3 = new Drink("Cider", true, 500, 10, "pear", true);


        Address address1 = new Address("Bucharest", "Bd. Mihail Kogalniceanu", 14);
        Address address2 = new Address("Bucharest", "Nicolae Balcescu", 5);
        Address address3 = new Address("Bucharest", "Bd. Carol", 2);
        Address address4 = new Address("Bucharest", "Dimitrie Cantemir", 33);
        Address address5 = new Address("Bucharest", "Mihai Bravu", 4);
        Address address6 = new Address("Bucharest", "Obor", 12);


        Restaurant restaurant1 = new Restaurant("McDonald's", address1);
        Restaurant restaurant2 = new Restaurant("Michelle", address2);
        Restaurant restaurant3 = new Restaurant("Movo", address3);


        Order order1 = new Order(client1, address4, restaurant1, deliveryDriver1, dish1, drink2);
        Order order2 = new Order(client2, address5, restaurant2, deliveryDriver2, dish3, drink1);
        Order order3 = new Order(client3, address6, restaurant3, deliveryDriver3, dish1, drink3);


        service.addClient(client1);
        service.addClient(client2);
        service.addClient(client3);

        service.addRestaurant(restaurant1);
        service.addRestaurant(restaurant2);
        service.addRestaurant(restaurant3);

        service.addDeliveryDriver(deliveryDriver1);
        service.addDeliveryDriver(deliveryDriver2);
        service.addDeliveryDriver(deliveryDriver3);

        service.addOrder(order1);
        service.addOrder(order2);
        service.addOrder(order3);

        service.addDish(dish1);
        service.addDish(dish2);
        service.addDish(dish3);

        service.addDrink(drink1);
        service.addDrink(drink2);
        service.addDrink(drink3);


        System.out.println(service.getDrinks());

        service.addDrinkToRestaurant(drink1, restaurant1);
        service.addDishToRestaurant(dish2, restaurant3);

        service.showDrinksFromRestaurant(restaurant1);

        service.showOrders();

        service.removeOrder(order2);
        service.removeAddress(address2);
        service.removeClient(client1);*/

        /*service.addDishToRestaurant(new Dish("Fillet bites", false, 220, 20, 500, new ArrayList<>(){{add("chicken"); add("paprika");}}), restaurant2);
        service.addDrinkToRestaurant(new Drink("Prigat", true, 330, 5, 100, "peaches", false), restaurant3);

        service.showClients();
        service.showFoodsFromOrder(order1);
        service.showDrinksFromOrder(order1);

        Client client4 = service.findClient("ralucar@yahoo.com");
        if(client4 != null)
            System.out.println(client4);
        else
            System.out.println("This client doesn't exist!");


        List<DeliveryDriver> availableDeliveryDrivers = service.getAvailableDeliveryDrivers();
        for(DeliveryDriver deliveryDriver: availableDeliveryDrivers){
            System.out.println(deliveryDriver);
        }

        service.removeDishFromOrder(order3, "Pizza Prosciutto");
        service.removeDrinkFromOrder(order3, "Lemonade");
        System.out.println(order3);

        System.out.println("Price of order 1: ");
        System.out.print(service.priceOfOrder(order1) + "$\n\n");

        service.fireDeliveryDriver(1);

        service.removeOrder(1);

        service.updateStatusForOrder(1, OrderStatus.COMPLETED);


        List<DeliveryDriver> deliveryDrivers = service.getDeliveryDrivers();
        if(deliveryDrivers != null){
            int i = 0;
            for(DeliveryDriver deliveryDriver: deliveryDrivers){
                i++;
                System.out.print(Integer.toString(i));
                System.out.print(". ");
                System.out.println(deliveryDriver);
            }
        }


        List<Order> orders = service.getOrders();
        if(orders != null){
            int i = 0;
            for(Order order: orders){
                i++;
                System.out.print(Integer.toString(i));
                System.out.print(". ");
                System.out.println(order);
            }
        }

        List<Restaurant> restaurants = service.getRestaurants();
        if(restaurants != null){
            int i = 0;
            for(Restaurant restaurant: restaurants){
                i++;
                System.out.print(Integer.toString(i) + ". ");
                System.out.println(restaurant);
            }
        }*/






        /*// Interactive Menu for some features

        System.out.println("1. Add new client");
        System.out.println("2. See all clients");
        System.out.println("3. Add new delivery driver");
        System.out.println("4. See all delivery drivers");
        System.out.println("5. See all restaurants");
        System.out.println("6. Show all orders");

        Scanner scanner = new Scanner(System.in);
        int option;
        do{
            option = scanner.nextInt();
            switch(option){
                case 1:
                    System.out.println("First name: ");
                    String firstName = scanner.next();
                    System.out.println("Last name: ");
                    String lastName = scanner.next();
                    System.out.println("Phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.println("Email: ");
                    String email = scanner.next();
                    service.addClient(new Client(firstName, lastName, phoneNumber, email));
                    break;
                case 2:
                    service.showClients();
                    break;
                case 3:
                    System.out.println("First name: ");
                    String firstNameD = scanner.next();
                    System.out.println("Last name: ");
                    String lastNameD = scanner.next();
                    System.out.println("Phone number: ");
                    String phoneNumberD = scanner.next();
                    System.out.println("Delivery method: ");
                    DeliveryMethod deliveryMethod = DeliveryMethod.valueOf(scanner.next());
                    System.out.println("Delivery driver status: ");
                    DeliveryDriverStatus deliveryDriverStatus = DeliveryDriverStatus.valueOf(scanner.next());
                    service.addDeliveryDriver(new DeliveryDriver(firstNameD, lastNameD, phoneNumberD, deliveryMethod, deliveryDriverStatus));
                    break;
                case 4:
                    service.showDeliveryDrivers();
                    break;
                case 5:
                    service.showRestaurants();
                    break;
                case 6:
                    service.showOrders();
                    break;
            }
        }while(option != 0);*/
    }
}
