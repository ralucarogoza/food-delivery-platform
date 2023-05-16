package service.impl;

import exceptions.*;
import model.*;
import service.FoodDeliveryService;
import utils.*;

import java.io.IOException;
import java.util.*;

import static java.util.Collections.sort;
import static validation.ClientValidation.validateEmail;
import static validation.PersonValidation.validatePhoneNumber;

public class FoodDeliveryServiceImpl implements FoodDeliveryService {
    private Map<String, Client> clients;
    private List<DeliveryDriver> deliveryDrivers;
    private List<Restaurant> restaurants;
    private List<Order> orders;
    private List<Address> addresses;
    private List<Drink> drinks;
    private List<Dish> dishes;


    public void addOrder(Order order){
        if(orders == null){
            orders = new ArrayList<>();
        }
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

    public List<Order> getOrders(){
        try{
            if(orders == null)
                throw new NoOrderFoundException("There are no orders!");
        }
        catch(NoOrderFoundException orderFoundException){
            System.out.println(orderFoundException.getMessage());
        }
        return orders;
    }

    public void removeOrder(int indexOrder){
        if(indexOrder - 1 < orders.size()){
            orders.remove(indexOrder - 1);
            System.out.println("Order " + indexOrder + " removed with success!");
        }
        else{
            System.out.println("Doesn't exist an order with this index!");
        }
    }

    public void updateStatusForOrder(int indexOrder, OrderStatus orderStatus){
        if(indexOrder - 1 > orders.size()){
            System.out.println("Doesn't exist an order with this index!");
        }
        else{
            OrderStatus oldStatus = orders.get(indexOrder - 1).getOrderStatus();
            orders.get(indexOrder - 1).setOrderStatus(orderStatus);
            System.out.println("Order status for order number " + indexOrder + " was modified from " + oldStatus + " to " + orderStatus);
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
        try{
            if(!validatePhoneNumber(client.getPhoneNumber()))
                throw new InvalidPhoneNumberException("Invalid format for phone number!");
        }
        catch (InvalidPhoneNumberException phoneNumberException){
            valid_client = false;
            System.out.println(phoneNumberException.getMessage());
        }
        if(valid_client){
            if(clients == null)
                clients = new TreeMap<>();
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


    public Map<String, Client> getClients(){
        try {
            if (clients == null)
                throw new NoClientFoundException("There are no clients!");
        }
        catch(NoClientFoundException clientFoundException){
            System.out.println(clientFoundException.getMessage());
        }
        return clients;
    }

    public void addDeliveryDriver(DeliveryDriver deliveryDriver){
        boolean validDeliveryDriver = true;
        try{
            if(!validatePhoneNumber(deliveryDriver.getPhoneNumber()))
                throw new InvalidPhoneNumberException("Invalid format for phone number!");
        }
        catch (InvalidPhoneNumberException phoneNumberException){
            validDeliveryDriver = false;
            System.out.println(phoneNumberException.getMessage());
        }
        if(validDeliveryDriver){
            if(deliveryDrivers == null)
                deliveryDrivers = new ArrayList<>();
            deliveryDrivers.add(deliveryDriver);
            sort(deliveryDrivers);
            System.out.println("Delivery driver added with success!");
        }
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

    public List<DeliveryDriver> getDeliveryDrivers(){
        try {
            if (deliveryDrivers == null)
                throw new NoClientFoundException("There are no delivery drivers!");
        }
        catch(NoDeliveryDriverFoundException deliveryDriverFoundException){
            System.out.println(deliveryDriverFoundException.getMessage());
        }
        return deliveryDrivers;
    }

    public void fireDeliveryDriver(int indexDeliveryDriver){
        if(indexDeliveryDriver - 1 < deliveryDrivers.size()){
            deliveryDrivers.remove(indexDeliveryDriver - 1);
            System.out.println("Delivery driver with index" + indexDeliveryDriver + " was fired with success!");
        }
        else
            System.out.println("Doesn't exist a delivery driver with this index!");
    }

    public void addRestaurant(Restaurant restaurant){
        if(restaurants == null)
            restaurants = new ArrayList<>();
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

    public List<Restaurant> getRestaurants(){
        try {
            if(restaurants == null)
                throw new NoRestaurantFoundException("There are no restaurants!");
        }
        catch(NoRestaurantFoundException restaurantFoundException){
            System.out.println(restaurantFoundException.getMessage());
        }
        return restaurants;
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

    public void showDishesFromRestaurant(Restaurant restaurant){
        if(restaurant.getFoods().isEmpty())
            System.out.println("Food menu is empty!\n");
        else{
            for(Dish dish : restaurant.getFoods()){
                System.out.println(dish);
            }
        }
    }

    public void addDrinkToOrder(Drink drink, Order order){
        List <Drink> drinksFromRestaurant = order.getRestaurant().getDrinks();
        try {
            if(!drinksFromRestaurant.contains(drink))
                throw new DrinkIsNotInTheMenuException("This drink is not in the restaurant's menu.");
            else{
                order.getOrderedDrinks().add(drink);
                System.out.println("Drink added with success to your order!");
            }
        }
        catch(DrinkIsNotInTheMenuException drinkIsNotInTheMenuException){
            System.out.println(drinkIsNotInTheMenuException.getMessage());
        }
    }

    public void addDishToOrder(Dish dish, Order order){
        List <Dish> dishesFromRestaurant = order.getRestaurant().getFoods();
        try {
            if(!dishesFromRestaurant.contains(dish))
                throw new DishIsNotInTheMenuException("This dish is not in the restaurant's menu.");
            else{
                order.getOrderedFoods().add(dish);
                System.out.println("Dish added with success to your order!");
            }
        }
        catch(DishIsNotInTheMenuException dishIsNotInTheMenuException){
            System.out.println(dishIsNotInTheMenuException.getMessage());
        }
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
        Client clientWanted = null;
        for(Map.Entry<String, Client> client: clients.entrySet()){
            if(client.getKey() == email){
                clientWanted = client.getValue();
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





    public void addAddress(Address address){
        if(addresses == null){
            addresses = new ArrayList<>();
        }
        addresses.add(address);
        System.out.println("Address added with success!");
    }

    public void addDish(Dish dish){
        if(dishes == null){
            dishes = new ArrayList<>();
        }
        dishes.add(dish);
        System.out.println("Dish added with success!");
    }


    public void addDrink(Drink drink){
        if(drinks == null){
            drinks = new ArrayList<>();
        }
        drinks.add(drink);
        System.out.println("Drink added with success!");
    }


    public List<Address> getAddresses(){
        try{
            if(addresses == null)
                throw new Exception("There are no addresses!");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return addresses;
    }

    public List<Dish> getDishes(){
        try{
            if(dishes == null)
                throw new Exception("There are no dishes!");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return dishes;
    }

    public List<Drink> getDrinks(){
        try{
            if(drinks == null)
                throw new Exception("There are no drinks!");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return drinks;
    }

    @Override
    public void addClientsFromCSVFile(String path) throws IOException {
        ClientReaderWriter clientReaderWriter = ClientReaderWriter.getInstance();
        List<Client> clientsFromCSVFile = new ArrayList<>(clientReaderWriter.read(path));
        for(Client client: clientsFromCSVFile){
            addClient(client);
        }
    }

    @Override
    public void addDeliveryDriversFromCSVFile(String path) throws IOException {
        DeliveryDriverReaderWriter deliveryDriverReaderWriter = DeliveryDriverReaderWriter.getInstance();
        List<DeliveryDriver> deliveryDriversFromCSVFile = new ArrayList<>(deliveryDriverReaderWriter.read(path));
        for(DeliveryDriver deliveryDriver: deliveryDriversFromCSVFile){
            addDeliveryDriver(deliveryDriver);
        }
    }

    @Override
    public void addAddressesFromCSVFile(String path) throws IOException {
        AddressReaderWriter addressReaderWriter = AddressReaderWriter.getInstance();
        List<Address> addressesFromCSVFile = new ArrayList<>(addressReaderWriter.read(path));
        for(Address address: addressesFromCSVFile){
            addAddress(address);
        }
    }

    @Override
    public void addDishesFromCSVFile(String path) throws IOException {
        DishReaderWriter dishReaderWriter = DishReaderWriter.getInstance();
        List<Dish> dishesFromCSVFile = new ArrayList<>(dishReaderWriter.read(path));
        for(Dish dish: dishesFromCSVFile){
            addDish(dish);
        }
    }

    @Override
    public void addDrinksFromCSVFile(String path) throws IOException {
        DrinkReaderWriter drinkReaderWriter = DrinkReaderWriter.getInstance();
        List<Drink> drinksFromCSVFile = new ArrayList<>(drinkReaderWriter.read(path));
        for(Drink drink: drinksFromCSVFile){
            addDrink(drink);
        }
    }
}
