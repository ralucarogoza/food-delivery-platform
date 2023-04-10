package service.impl;

import exceptions.InvalidEmailException;
import model.*;
import service.FoodDeliveryService;

import java.util.*;

import static java.util.Collections.sort;
import static validation.ClientValidation.validateEmail;

public class FoodDeliveryServiceImpl implements FoodDeliveryService {
    private Map<String, Client> clients;

    private List<DeliveryDriver> deliveryDrivers;
    private List<Restaurant> restaurants;
    private List<Order> orders;

    public FoodDeliveryServiceImpl() {
        clients = new TreeMap<>();
        deliveryDrivers = new ArrayList<>();
        restaurants = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public Map<String, Client> getClients() {
        return clients;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addOrder(Order order){
        orders.add(order);
        System.out.println("Order added with success!");
    }

    public void showOrders(){
        if(orders.isEmpty())
            System.out.println("There are no orders!");
        else{
            int i = 0;
            for(Order order: orders){
                i++;
                System.out.print(Integer.toString(i));
                System.out.print(". ");
                System.out.println(order);
            }
        }
    }

    @Override
    public double priceOfOrder(Order order) {
        double price = 0;
        for(Dish dish : order.getOrderedFoods()){
            price += dish.getPrice();
        }
        for(Drink drink: order.getOrderedDrinks()){
            price += drink.getPrice();
        }
        return price;
    }
    public void addClient(Client client){
        boolean valid_client = true;
        try{
            if(!validateEmail(client.getEmail()))
                throw new InvalidEmailException("Invalid format for email!");
        }
        catch(InvalidEmailException emailException){
            valid_client = false;
            System.out.println(emailException.getMessage());
        }
        if(valid_client){
            clients.put(client.getEmail(), client);
            System.out.println("Client added with success!");
        }
    }

    public void showClients(){
        if(clients.isEmpty())
            System.out.println("There are no clients!\n");
        else{
            int i = 0;
            for(Map.Entry<String, Client> client: clients.entrySet()){
                i ++;
                System.out.print(Integer.toString(i) + ". ");
                System.out.println(client.getValue());
            }
        }
    }

    public void addDeliveryDriver(DeliveryDriver deliveryDriver){
        deliveryDrivers.add(deliveryDriver);
        sort(deliveryDrivers);
        System.out.println("Delivery driver added with success!");
    }

    public void showDeliveryDrivers(){
        if(deliveryDrivers.isEmpty())
            System.out.println("There are no delivery drivers!\n");
        else{
            int i = 0;
            for(DeliveryDriver deliveryDriver: deliveryDrivers){
                i++;
                System.out.print(Integer.toString(i) + ". ");
                System.out.println(deliveryDriver);
            }
        }
    }

    public void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
        System.out.println("Restaurant added with success!");
    }

    public void showRestaurants(){
        if(restaurants.isEmpty())
            System.out.println("There are no restaurants!\n");
        else{
            int i = 0;
            for(Restaurant restaurant: restaurants){
                i++;
                System.out.print(Integer.toString(i) + ". ");
                System.out.println(restaurant);
            }
        }
    }

    public void addDrinkToRestaurant(Drink drink, Restaurant restaurant){
        restaurant.getDrinks().add(drink);
        System.out.println("Drink added with success to restaurant menu!");
    }

    public void showDrinksFromRestaurant(Restaurant restaurant){
        if(restaurant.getDrinks().isEmpty())
            System.out.println("Drink menu is empty!\n");
        else{
            for(Drink drink: restaurant.getDrinks()){
                System.out.println(drink);
            }
        }
    }
    public void addDishToRestaurant(Dish dish, Restaurant restaurant){
        restaurant.getFoods().add(dish);
        System.out.println("Dish added with success to restaurant menu!");
    }

    public void showFoodsFromRestaurant(Restaurant restaurant){
        if(restaurant.getFoods().isEmpty())
            System.out.println("Food menu is empty!\n");
        else{
            for(Dish dish : restaurant.getFoods()){
                System.out.println(dish);
            }
        }
    }



    public void addDrinkToOrder(Drink drink, Order order){
        order.getOrderedDrinks().add(drink);
        System.out.println("Drink added with success to your order!");
    }
    public void addDishToOrder(Dish dish, Order order){
        order.getOrderedFoods().add(dish);
        System.out.println("Dish added with success to your order!");
    }

    public void showDrinksFromOrder(Order order){
        if(order.getOrderedDrinks().isEmpty())
            System.out.println("There are no drinks in your order!\n");
        else{
            for(Drink drink: order.getOrderedDrinks()){
                System.out.println(drink);
            }
        }
    }

    public void showFoodsFromOrder(Order order){
        if(order.getOrderedFoods().isEmpty())
            System.out.println("There are no dishes in your order!\n");
        else{
            for(Dish dish : order.getOrderedFoods()){
                System.out.println(dish);
            }
        }
    }

    public void removeDrinkFromOrder(Order order, String drinkName){
        boolean found = false;
        for(Drink drink: order.getOrderedDrinks()){
            if(drink.getName() == drinkName){
                order.getOrderedDrinks().remove(drink);
                found = true;
                break;
            }
        }
        if(found)
            System.out.println("Drink removed from your order with success!");
        else
            System.out.println("This drink isn't in your order!");
    }
    public void removeDishFromOrder(Order order, String dishName){
        boolean found = false;
        for(Dish dish : order.getOrderedFoods()){
            if(dish.getName() == dishName){
                order.getOrderedFoods().remove(dish);
                found = true;
                break;
            }
        }
        if(found)
            System.out.println("Dish removed from your order with success!");
        else
            System.out.println("This dish isn't in your order!");
    }

    public Client findClient(String email){
        boolean found = false;
        Client clientWanted = new Client();
        for(Map.Entry<String, Client> client: clients.entrySet()){
            if(client.getKey() == email){
                clientWanted = client.getValue();
                found = true;
            }
        }
        return clientWanted;
    }

    public List<DeliveryDriver> getAvailableDeliveryDrivers(){
        List<DeliveryDriver> availableDeliveryDrivers = new ArrayList<>();
        for(DeliveryDriver deliveryDriver: this.deliveryDrivers){
            if(deliveryDriver.getDeliveryDriverStatus() == DeliveryDriverStatus.AVAILABLE)
                availableDeliveryDrivers.add(deliveryDriver);
        }
        return availableDeliveryDrivers;
    }
}
