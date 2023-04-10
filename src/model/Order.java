package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private static int noOrder = 0;
    private Client client;
    private Address clientAddress;
    private Restaurant restaurant;
    private DeliveryDriver deliveryDriver;
    private List<Dish> orderedDishes;
    private List<Drink> orderedDrinks;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    public Order(Client client, Address clientAddress, Restaurant restaurant, DeliveryDriver deliveryDriver, List<Dish> orderedDishes, List<Drink> orderedDrinks, OrderStatus orderStatus) {
        this.client = client;
        this.clientAddress = clientAddress;
        this.restaurant = restaurant;
        this.deliveryDriver = deliveryDriver;
        this.orderedDishes = orderedDishes;
        this.orderedDrinks = orderedDrinks;
        /*this.orderDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        this.orderDate = this.orderDate.format(calendar.getTime());*/
        this.orderStatus = orderStatus;
        this.orderDate = LocalDateTime.now();

        noOrder++;
    }

    public Order(Client client, Address clientAddress, Restaurant restaurant, DeliveryDriver deliveryDriver, List<Dish> orderedDishes, List<Drink> orderedDrinks) {
        this.client = client;
        this.clientAddress = clientAddress;
        this.restaurant = restaurant;
        this.deliveryDriver = deliveryDriver;
        this.orderedDishes = orderedDishes;
        this.orderedDrinks = orderedDrinks;
        /*this.orderDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        this.orderDate = this.orderDate.format(calendar.getTime());*/
        this.orderStatus = OrderStatus.IN_PROCESS;
        this.orderDate = LocalDateTime.now();
        noOrder++;
    }


    public int getNoOrders() {
        return noOrder;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public DeliveryDriver getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(DeliveryDriver deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public List<Dish> getOrderedFoods() {
        return orderedDishes;
    }

    public void setOrderedFoods(List<Dish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public List<Drink> getOrderedDrinks() {
        return orderedDrinks;
    }

    public void setOrderedDrinks(List<Drink> orderedDrinks) {
        this.orderedDrinks = orderedDrinks;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = orderDate.format(myFormatObj);
        String output ="";
        output += "Order: " +
                "\nStatus: " + orderStatus +
                "\nClient: \n" + client +
                "\nClient's address: " + clientAddress +
                "\nRestaurant: " + restaurant.getName() + "\nRestaurant's address: " + restaurant.getAddress() +
                "\nOrdered Foods: \n";
        int i = 0;
        for(Dish dish : orderedDishes){
            i ++;
            output += Integer.toString(i);
            output += ". ";
            output += dish;
            output += '\n';
        }
        output += "\nOrdered drinks: \n";
        i = 0;
        for(Drink drink: orderedDrinks){
            i ++;
            output += Integer.toString(i);
            output += ". ";
            output += drink;
            output += '\n';
        }
        output += "Order date: " + formattedDate;
        return output;
    }
}
