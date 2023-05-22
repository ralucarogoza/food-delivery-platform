package service;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FoodDeliveryService {
    void addOrder(Order order);
    List<Order> getOrders();
    void deleteOrder(Order order);
    void updateOrder(Order oldOrder, Order newOrder);
    double priceOfOrder(Order order);
    void addClient(Client client) throws SQLException;
    void removeClient(Client client) throws SQLException;
    void updateClient(Client oldClient, Client newClient) throws SQLException;
    Map<String, Client> getClients();
    void addDeliveryDriver(DeliveryDriver deliveryDriver);
    List<DeliveryDriver> getDeliveryDrivers();
    void deleteDeliveryDriver(DeliveryDriver deliveryDriver);
    void updateDeliveryDriver(DeliveryDriver oldDeliveryDriver, DeliveryDriver newDeliveryDriver);
    void addRestaurant(Restaurant restaurant);
    List<Restaurant> getRestaurants();
    void deleteRestaurant(Restaurant restaurant);
    void updateRestaurant(Restaurant oldRestaurant, Restaurant newRestaurant);
    void addDrinkToRestaurant(DrinkFromRestaurant drinkFromRestaurant);
    List<DrinkFromRestaurant> getDrinksFromRestaurant();
    void addDishToRestaurant(DishFromRestaurant dishFromRestaurant);
    List<DishFromRestaurant> getDishesFromRestaurant();
    //void addDishToOrder(Dish dish, Order order);
    //void showFoodsFromOrder(Order order);
    //void addDrinkToOrder(Drink drink, Order order);
    //void showDrinksFromOrder(Order order);
    //void removeDrinkFromOrder(Order order, String drinkName);
    //void removeDishFromOrder(Order order, String dishName);
    Optional<Client> findClientByEmail(String email);
    List<DeliveryDriver> getAvailableDeliveryDrivers();

    void addAddress(Address address);

    List<Address> getAddresses();

    void addDish(Dish dish);

    List<Dish> getDishes();
    List<Drink> getDrinks();

    void deleteDish(Dish dish);

    void addDrink(Drink drink);

    void deleteDrink(Drink drink);
    void deleteAddress(Address address);
    void updateAddress(Address oldAddress, Address newAddress);
    void updateDish(Dish oldDish, Dish newDish);
    void updateDrink(Drink oldDrink, Drink newDrink);
    void addClientsFromCSVFile(String path) throws IOException, SQLException;

    void addDeliveryDriversFromCSVFile(String path) throws IOException;

    void addAddressesFromCSVFile(String path) throws IOException;

    void addDishesFromCSVFile(String path) throws IOException;

    void addDrinksFromCSVFile(String path) throws IOException;
}
