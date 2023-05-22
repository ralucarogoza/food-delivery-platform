import config.DatabaseConfiguration;
import model.*;
import repositories.*;
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
        DeliveryDriverRepository deliveryDriverRepository = new DeliveryDriverRepository(databaseConfiguration);
        DishRepository dishRepository = new DishRepository(databaseConfiguration);
        DrinkRepository drinkRepository = new DrinkRepository(databaseConfiguration);
        RestaurantRepository restaurantRepository = new RestaurantRepository(databaseConfiguration);
        DrinkFromRestaurantRepository drinkFromRestaurantRepository = new DrinkFromRestaurantRepository(databaseConfiguration);
        DishFromRestaurantRepository dishFromRestaurantRepository = new DishFromRestaurantRepository(databaseConfiguration);
        OrderRepository orderRepository = new OrderRepository(databaseConfiguration);


        FoodDeliveryServiceImpl service = new FoodDeliveryServiceImpl();
        service.setClientRepository(clientRepository);
        service.setAddressRepository(addressRepository);
        service.setDeliveryDriverRepository(deliveryDriverRepository);
        service.setDishRepository(dishRepository);
        service.setDrinkRepository(drinkRepository);
        service.setRestaurantRepository(restaurantRepository);
        service.setDishFromRestaurantRepository(dishFromRestaurantRepository);
        service.setDrinkFromRestaurantRepository(drinkFromRestaurantRepository);
        service.setOrderRepository(orderRepository);

        // CLIENT

        /*Client client1 = new Client(1,"Raluca", "Rogoza", "0234567890", "ralucar@yahoo.com");
        Client client2 = new Client(2, "Carina", "Nicola", "0254657698", "carinan@yahoo.com");
        Client client3 = new Client(3, "Ioana", "Maria", "0234125476", "ioanam@yahoo.com");
        service.addClient(client1);
        service.addClient(client2);
        //service.addClient(client3);
        System.out.println(service.getClients());

        service.removeClient(client1);
        System.out.println(service.getClients());


        service.updateClient(client2, client3);
        System.out.println(service.getClients());
        service.findClientByEmail("carinan@yahoo.com");
*/

        /* ADDRESS

        Address address1 = new Address(1,"Bucharest", "Bd. Mihail Kogalniceanu", 14);
        Address address2 = new Address(2,"Bucharest", "Nicolae Balcescu", 5);
        Address address3 = new Address(3,"Bucharest", "Bd. Carol", 2);
        Address address4 = new Address(4,"Bucharest", "Dimitrie Cantemir", 33);
        Address address5 = new Address(5, "Bucharest", "Mihai Bravu", 4);
        Address address6 = new Address(6,"Bucharest", "Obor", 12);

        System.out.println("Adding addresses: ");
        service.addAddress(address1);
        service.addAddress(address2);
        service.addAddress(address3);
        service.addAddress(address4);

        service.addAddress(address2);

        System.out.println("All addresses: ");
        System.out.println(service.getAddresses());

        service.deleteAddress(address3);
        service.deleteAddress(address2);
        service.updateAddress(address2, address4);

        System.out.println("All addresses: ");
        System.out.println(service.getAddresses());*/


        // DELIVERY DRIVER

        /*DeliveryDriver deliveryDriver1 = new DeliveryDriver(1, "Bogdan", "Mihai", "0789437295", DeliveryMethod.BICYCLE, DeliveryDriverStatus.AVAILABLE);
        DeliveryDriver deliveryDriver2 = new DeliveryDriver(2, "Andrei", "Popa", "0358659375", DeliveryMethod.ELECTRIC_SCOOTER, DeliveryDriverStatus.AVAILABLE);
        DeliveryDriver deliveryDriver3 = new DeliveryDriver(3, "Calin", "Ionescu", "0732749585", DeliveryMethod.CAR, DeliveryDriverStatus.BUSY);


        service.addDeliveryDriver(deliveryDriver1);
        service.addDeliveryDriver(deliveryDriver2);
        //service.addDeliveryDriver(deliveryDriver3);

        System.out.println(service.getDeliveryDrivers());

        service.updateDeliveryDriver(deliveryDriver1, deliveryDriver3);
        System.out.println(service.getDeliveryDrivers());

        service.deleteDeliveryDriver(deliveryDriver1);
        System.out.println(service.getDeliveryDrivers());
        System.out.println(service.getAvailableDeliveryDrivers());*/


        //  DISH


        /*Dish dish1 = new Dish(1,"Pizza Prosciutto", false, 450, 30, 780);
        Dish dish2 = new Dish(2,"Chicken Burger", false, 320, 40, 550);
        Dish dish3 = new Dish(3,"Pasta with vegetables", true, 330, 45, 420);


        service.addDish(dish1);
        service.addDish(dish2);
        //service.addDish(dish3);

        System.out.println(service.getDishes());

        service.updateDish(dish1, dish3);
        System.out.println(service.getDishes());

        service.deleteDish(dish2);
        service.deleteDish(dish1);
        System.out.println(service.getDishes());*/


        // DRINK

        /*Drink drink1 = new Drink(1,"Lemonade", true, 350, 18, "strawberries", false);
        Drink drink2 = new Drink(2,"Caffee latte", false, 320, 14, "vanilla", false);
        Drink drink3 = new Drink(3,"Cider", true, 500, 10, "pear", true);


        service.addDrink(drink1);
        service.addDrink(drink2);
        //service.addDrink(drink3);

        System.out.println(service.getDrinks());

        service.updateDrink(drink1, drink3);
        System.out.println(service.getDrinks());

        service.deleteDrink(drink2);
        service.deleteDrink(drink1);
        System.out.println(service.getDrinks());*/


        // RESTAURANT

        /*Address address1 = new Address(1,"Bucharest", "Bd. Mihail Kogalniceanu", 14);
        Address address2 = new Address(2,"Bucharest", "Nicolae Balcescu", 5);
        Address address3 = new Address(3,"Bucharest", "Bd. Carol", 2);

        service.addAddress(address1);
        service.addAddress(address2);
        service.addAddress(address3);

        Restaurant restaurant1 = new Restaurant(1,"McDonald's", address1);
        Restaurant restaurant2 = new Restaurant(2,"Michelle", address2);
        Restaurant restaurant3 = new Restaurant(3,"Movo", address3);

        service.addRestaurant(restaurant1);
        service.addRestaurant(restaurant2);
        //service.addRestaurant(restaurant3);

        System.out.println(service.getRestaurants());

        service.updateRestaurant(restaurant1, restaurant3);
        System.out.println(service.getRestaurants());

        service.deleteRestaurant(restaurant3);
        System.out.println(service.getRestaurants());*/






        // ORDER


        /*Address address1 = new Address(1, "Bucharest", "Bd. Mihail Kogalniceanu", 14);
        Address address2 = new Address(2, "Bucharest", "Nicolae Balcescu", 5);
        Address address3 = new Address(3, "Bucharest", "Bd. Carol", 2);


        Restaurant restaurant1 = new Restaurant(1, "McDonald's", address1);
        Restaurant restaurant2 = new Restaurant(2, "Michelle", address2);
        Restaurant restaurant3 = new Restaurant(3, "Movo", address3);


        Drink drink1 = new Drink(1, "Lemonade", true, 350, 18, "strawberries", false);
        Drink drink2 = new Drink(2, "Caffee latte", false, 320, 14, "vanilla", false);
        Drink drink3 = new Drink(3, "Cider", true, 500, 10, "pear", true);


        Dish dish1 = new Dish(1, "Pizza Prosciutto", false, 450, 30, 780);
        Dish dish2 = new Dish(2, "Chicken Burger", false, 320, 40, 550);
        Dish dish3 = new Dish(3, "Pasta with vegetables", true, 330, 45, 420);


        DishFromRestaurant dishFromRestaurant1 = new DishFromRestaurant(1, 1, 1);
        DishFromRestaurant dishFromRestaurant2 = new DishFromRestaurant(2, 2, 3);
        DishFromRestaurant dishFromRestaurant3 = new DishFromRestaurant(3, 1, 3);

        DrinkFromRestaurant drinkFromRestaurant1 = new DrinkFromRestaurant(1, 2, 3);
        DrinkFromRestaurant drinkFromRestaurant2 = new DrinkFromRestaurant(2, 3, 1);
        DrinkFromRestaurant drinkFromRestaurant3 = new DrinkFromRestaurant(3, 1, 3);

        Client client1 = new Client(1, "Raluca", "Rogoza", "0234567890", "ralucar@yahoo.com");
        Client client2 = new Client(2, "Carina", "Nicola", "0254657698", "carinan@yahoo.com");
        Client client3 = new Client(3, "Ioana", "Maria", "0234125476", "ioanam@yahoo.com");


        DeliveryDriver deliveryDriver1 = new DeliveryDriver(1, "Bogdan", "Mihai", "0789437295", DeliveryMethod.BICYCLE, DeliveryDriverStatus.AVAILABLE);
        DeliveryDriver deliveryDriver2 = new DeliveryDriver(2, "Andrei", "Popa", "0358659375", DeliveryMethod.ELECTRIC_SCOOTER, DeliveryDriverStatus.AVAILABLE);
        DeliveryDriver deliveryDriver3 = new DeliveryDriver(3, "Calin", "Ionescu", "0732749585", DeliveryMethod.CAR, DeliveryDriverStatus.BUSY);


        Order order1 = new Order(client1, address1, deliveryDriver1, dishFromRestaurant1, drinkFromRestaurant2);
        Order order2 = new Order(client2, address2, deliveryDriver2, dishFromRestaurant3, drinkFromRestaurant1);
        Order order3 = new Order(client3, address3, deliveryDriver3, dishFromRestaurant1, drinkFromRestaurant3);


        service.addAddress(address1);
        service.addAddress(address2);
        service.addAddress(address3);

        service.addRestaurant(restaurant1);
        service.addRestaurant(restaurant2);
        service.addRestaurant(restaurant3);

        service.addDrink(drink1);
        service.addDrink(drink2);
        service.addDrink(drink3);

        service.addDish(dish1);
        service.addDish(dish2);
        service.addDish(dish3);

        service.addDishToRestaurant(dishFromRestaurant1);
        service.addDishToRestaurant(dishFromRestaurant2);
        service.addDishToRestaurant(dishFromRestaurant3);

        service.addDrinkToRestaurant(drinkFromRestaurant1);
        service.addDrinkToRestaurant(drinkFromRestaurant2);
        service.addDrinkToRestaurant(drinkFromRestaurant3);

        service.addClient(client1);
        service.addClient(client2);
        service.addClient(client3);

        service.addDeliveryDriver(deliveryDriver1);
        service.addDeliveryDriver(deliveryDriver2);
        service.addDeliveryDriver(deliveryDriver3);


        service.addOrder(order1);
        service.addOrder(order2);
        service.addOrder(order3);

        System.out.println(service.getDishesFromRestaurant());

        System.out.println(service.getDrinksFromRestaurant());
        System.out.println(service.getOrders());


        service.deleteOrder(order2);
        service.updateOrder(order3, order2);

        System.out.println(service.priceOfOrder(order2));

        System.out.println(service.getClients());
        System.out.println(service.getDeliveryDrivers());
        System.out.println(service.getAddresses());
        System.out.println(service.getDrinks());
        System.out.println(service.getDishes());
        System.out.println(service.getRestaurants());
        System.out.println(service.getDrinksFromRestaurant());
        System.out.println(service.getDishesFromRestaurant());
        System.out.println(service.getOrders());

        List<Order> orders = service.getOrders();
        if (orders != null) {
            int i = 0;
            for (Order order : orders) {
                i++;
                System.out.print(Integer.toString(i));
                System.out.print(". ");
                System.out.println(order);
            }
        }*/








/*      CSV



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
    }
}
