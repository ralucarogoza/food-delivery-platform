package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
    private static int noOrder = 0;
    private Client client;
    private Address clientAddress;
    private Restaurant restaurant;
    private DeliveryDriver deliveryDriver;
    private List<Food> orderedFoods;
    private List<Drink> orderedDrinks;
    private LocalDateTime orderDate;

    public Order(Client client, Address clientAddress, Restaurant restaurant, DeliveryDriver deliveryDriver, List<Food> orderedFoods, List<Drink> orderedDrinks) {
        this.client = client;
        this.clientAddress = clientAddress;
        this.restaurant = restaurant;
        this.deliveryDriver = deliveryDriver;
        this.orderedFoods = orderedFoods;
        this.orderedDrinks = orderedDrinks;
        /*this.orderDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        this.orderDate = this.orderDate.format(calendar.getTime());*/

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

    public List<Food> getOrderedFoods() {
        return orderedFoods;
    }

    public void setOrderedFoods(List<Food> orderedFoods) {
        this.orderedFoods = orderedFoods;
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

    @Override
    public String toString() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = orderDate.format(myFormatObj);
        return "Order{" +
                ", client=" + client +
                ", clientAddress=" + clientAddress +
                ", restaurant=" + restaurant +
                ", deliveryDriver=" + deliveryDriver +
                ", orderedFoods=" + orderedFoods +
                ", orderedDrinks=" + orderedDrinks +
                ", orderDate=" + formattedDate +
                '}';
    }
}
