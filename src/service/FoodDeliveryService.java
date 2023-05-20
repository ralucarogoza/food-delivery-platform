package service;
import model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface FoodDeliveryService {
    void addOrder(Order order);
    void showOrders();
    List<Order> getOrders();
    void removeOrder(Order order);
    void updateStatusForOrder(int indexOrder, OrderStatus orderStatus);
    double priceOfOrder(Order order);
    void addClient(Client client);
    void showClients();
    void removeClient(Client client);
    Map<String, Client> getClients();
    void addDeliveryDriver(DeliveryDriver deliveryDriver);
    void showDeliveryDrivers();
    List<DeliveryDriver> getDeliveryDrivers();
    void fireDeliveryDriver(DeliveryDriver deliveryDriver);
    void addRestaurant(Restaurant restaurant);
    void showRestaurants();
    List<Restaurant> getRestaurants();
    void removeRestaurant(Restaurant restaurant);
    void addDrinkToRestaurant(Drink drink, Restaurant restaurant);
    void showDrinksFromRestaurant(Restaurant restaurant);
    void addDishToRestaurant(Dish dish, Restaurant restaurant);
    void showDishesFromRestaurant(Restaurant restaurant);
    //void addDishToOrder(Dish dish, Order order);
    //void showFoodsFromOrder(Order order);
    //void addDrinkToOrder(Drink drink, Order order);
    //void showDrinksFromOrder(Order order);
    //void removeDrinkFromOrder(Order order, String drinkName);
    //void removeDishFromOrder(Order order, String dishName);
    Client findClient(String email);
    List<DeliveryDriver> getAvailableDeliveryDrivers();

    List<Address> getAddresses();
    List<Dish> getDishes();
    List<Drink> getDrinks();

    void removeDish(Dish dish);
    void removeDrink(Drink drink);
    void removeAddress(Address address);
    void addClientsFromCSVFile(String path) throws IOException;

    void addDeliveryDriversFromCSVFile(String path) throws IOException;

    void addAddressesFromCSVFile(String path) throws IOException;

    void addDishesFromCSVFile(String path) throws IOException;

    void addDrinksFromCSVFile(String path) throws IOException;
}
