package service;
import model.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface FoodDeliveryService {
    void addOrder(Order order);
    void showOrders();
    List<Order> getOrders();
    void removeOrder(int indexOrder);
    void updateStatusForOrder(int indexOrder, OrderStatus orderStatus);
    double priceOfOrder(Order order);
    void addClient(Client client);
    void showClients();
    Map<String, Client> getClients();
    void addDeliveryDriver(DeliveryDriver deliveryDriver);
    void showDeliveryDrivers();
    List<DeliveryDriver> getDeliveryDrivers();
    void fireDeliveryDriver(int indexDeliveryDriver);
    void addRestaurant(Restaurant restaurant);
    void showRestaurants();
    List<Restaurant> getRestaurants();
    void addDrinkToRestaurant(Drink drink, Restaurant restaurant);
    void showDrinksFromRestaurant(Restaurant restaurant);
    void addDishToRestaurant(Dish dish, Restaurant restaurant);
    void showDishesFromRestaurant(Restaurant restaurant);
    void addDishToOrder(Dish dish, Order order);
    void showFoodsFromOrder(Order order);
    void addDrinkToOrder(Drink drink, Order order);
    void showDrinksFromOrder(Order order);
    void removeDrinkFromOrder(Order order, String drinkName);
    void removeDishFromOrder(Order order, String dishName);
    Client findClient(String email);
    List<DeliveryDriver> getAvailableDeliveryDrivers();
}
