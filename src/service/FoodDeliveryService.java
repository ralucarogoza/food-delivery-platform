package service;
import model.*;
import java.util.List;

public interface FoodDeliveryService {
    void addOrder(Order order);
    void showOrders();
    void removeOrder(int indexOrder);
    void updateStatusForOrder(int indexOrder, OrderStatus orderStatus);
    double priceOfOrder(Order order);
    void addClient(Client client);
    void showClients();
    void addDeliveryDriver(DeliveryDriver deliveryDriver);
    void showDeliveryDrivers();
    void fireDeliveryDriver(int indexDeliveryDriver);
    void addRestaurant(Restaurant restaurant);
    void showRestaurants();
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
