package service;

import model.*;

public interface OrderService {
    void addOrder(Order order);
    void showOrders();
    double priceOfOrder(Order order);
    void addClient(Client client);
    void showClients();

    void addRestaurant(Restaurant restaurant);
    void showRestaurants();

    void addDrinkToRestaurant(Drink drink, Restaurant restaurant);

    void showDrinksFromRestaurant(Restaurant restaurant);

    void addDishToRestaurant(Food food, Restaurant restaurant);
    void showFoodsFromRestaurant(Restaurant restaurant);

    void addDishToOrder(Food food, Order order);

    void showFoodsFromOrder(Order order);

    void addDrinkToOrder(Drink drink, Order order);

    void showDrinksFromOrder(Order order);

    void removeDrinkFromOrder(Order order, String drinkName);
    void removeDishFromOrder(Order order, String dishName);
}
