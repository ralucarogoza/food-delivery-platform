package service.impl;

import exceptions.InvalidEmailException;
import model.*;
import service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static validation.ClientValidation.validateEmail;

public class OrderServiceImpl implements OrderService {
    private Map<String, Client> clients = new TreeMap<>();
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
        System.out.println("Order added with success!");
    }

    public void showOrders(){
        if(orders.isEmpty())
            System.out.println("There are no orders!");
        else{
            for(Order order: orders){
                System.out.println(order);
            }
        }
    }

    @Override
    public double priceOfOrder(Order order) {
        double price = 0;
        for(Food food: order.getOrderedFoods()){
            price += food.getPrice();
        }
        for(Drink drink: order.getOrderedDrinks()){
            price += drink.getPrice();
        }
        return price;
    }
    public void addClient(Client client){
        if(!validateEmail(client.getEmail()))
            throw new InvalidEmailException("Invalid format for email!");
        clients.put(client.getEmail(), client);
        System.out.println("Client added with success!");
    }

    public void showClients(){
        if(clients.isEmpty())
            System.out.println("There are no clients!\n");
        else{
            for(Map.Entry<String, Client> client: clients.entrySet()){
                System.out.println(client.getValue());
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
            for(Restaurant restaurant: restaurants){
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
    public void addDishToRestaurant(Food food, Restaurant restaurant){
        restaurant.getFoods().add(food);
        System.out.println("Dish added with success to restaurant menu!");
    }

    public void showFoodsFromRestaurant(Restaurant restaurant){
        if(restaurant.getFoods().isEmpty())
            System.out.println("Food menu is empty!\n");
        else{
            for(Food food: restaurant.getFoods()){
                System.out.println(food);
            }
        }
    }



    public void addDrinkToOrder(Drink drink, Order order){
        order.getOrderedDrinks().add(drink);
        System.out.println("Drink added with success to your order!");
    }
    public void addDishToOrder(Food food, Order order){
        order.getOrderedFoods().add(food);
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
            for(Food food: order.getOrderedFoods()){
                System.out.println(food);
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
        for(Food food: order.getOrderedFoods()){
            if(food.getName() == dishName){
                order.getOrderedFoods().remove(food);
                found = true;
                break;
            }
        }
        if(found)
            System.out.println("Dish removed from your order with success!");
        else
            System.out.println("This dish isn't in your order!");
    }
}
