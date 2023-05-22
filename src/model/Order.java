package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private static int noOrders = 1;
    private int id;
    private Client client;
    private Address clientAddress;
    //private Restaurant restaurant;
    private DeliveryDriver deliveryDriver;
    private DishFromRestaurant orderedDish;
    private DrinkFromRestaurant orderedDrink;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    public Order(Client client, Address clientAddress, DeliveryDriver deliveryDriver, DishFromRestaurant orderedDish, DrinkFromRestaurant orderedDrink, OrderStatus orderStatus) {
        this.id = noOrders;
        this.client = client;
        this.clientAddress = clientAddress;
        //this.restaurant = restaurant;
        this.deliveryDriver = deliveryDriver;
        this.orderedDish = orderedDish;
        this.orderedDrink = orderedDrink;
        /*this.orderDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        this.orderDate = this.orderDate.format(calendar.getTime());*/
        this.orderStatus = orderStatus;
        this.orderDate = LocalDateTime.now();
        noOrders++;
    }

    public Order(int id, Client client, Address clientAddress, DeliveryDriver deliveryDriver, DishFromRestaurant orderedDish, DrinkFromRestaurant orderedDrink, OrderStatus orderStatus) {
        this.id = id;
        this.client = client;
        this.clientAddress = clientAddress;
        //this.restaurant = restaurant;
        this.deliveryDriver = deliveryDriver;
        this.orderedDish = orderedDish;
        this.orderedDrink = orderedDrink;
        /*this.orderDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        this.orderDate = this.orderDate.format(calendar.getTime());*/
        this.orderStatus = orderStatus;
        this.orderDate = LocalDateTime.now();
    }

    public Order(Client client, Address clientAddress, DeliveryDriver deliveryDriver, DishFromRestaurant orderedDish, DrinkFromRestaurant orderedDrink) {
        this.id = noOrders;
        this.client = client;
        this.clientAddress = clientAddress;
        //this.restaurant = restaurant;
        this.deliveryDriver = deliveryDriver;
        this.orderedDish = orderedDish;
        this.orderedDrink = orderedDrink;
        /*this.orderDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        this.orderDate = this.orderDate.format(calendar.getTime());*/
        this.orderStatus = OrderStatus.IN_PROCESS;
        this.orderDate = LocalDateTime.now();
        noOrders++;
    }

    public int getId() {
        return id;
    }

    public int getNoOrders() {
        return noOrders;
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

/*    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }*/

    public DeliveryDriver getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(DeliveryDriver deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public DishFromRestaurant getOrderedDish() {
        return orderedDish;
    }

    public void setOrderedDish(DishFromRestaurant orderedDish) {
        this.orderedDish = orderedDish;
    }

    public DrinkFromRestaurant getOrderedDrink() {
        return orderedDrink;
    }

    public void setOrderedDrink(DrinkFromRestaurant orderedDrink) {
        this.orderedDrink = orderedDrink;
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
        output += "\n\nOrder: " +
                "\nId: " + id +
                "\nStatus: " + orderStatus +
                "\nClient: \n" + client +
                "\nClient's address: " + clientAddress +
                //"\nRestaurant: " + restaurant.getName() + "\nRestaurant's address: " + restaurant.getAddress() +
                "\nOrdered Dish: " + orderedDish +
                "\nOrdered Drink: " + orderedDrink + '\n';
        /*int i = 0;
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
        }*/
        output += "Order date: " + formattedDate;
        return output;
    }
}
