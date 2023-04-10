package model;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Restaurant {
    private String name;
    private Address address;
    private List<Food> foods;
    private List<Drink> drinks;

    public Restaurant(String name, Address address, List<Food> foods, List<Drink> drinks) {
        this.name = name;
        this.address = address;
        this.foods = foods;
        this.drinks = drinks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @Override
    public String toString() {
        String output = "";
        output += "\nRestaurant:" +
                "\nName: " + name +
                "\nAddress: " + address +
                "\nDishes: \n";
        int i = 0;
        for(Food food: foods){
            i++;
            output += Integer.toString(i);
            output += ". ";
            output += food;
            output += '\n';
        }
        i = 0;
        output += "\nDrinks: \n";
        for(Drink drink: drinks){
            i++;
            output += Integer.toString(i);
            output += ". ";
            output += drink;
            output += '\n';
        }
        return output;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(!(object instanceof Restaurant))
            return false;
        Restaurant restaurant = (Restaurant) object;
        return this.getName().equals(restaurant.getName()) &&
                this.getAddress().equals(restaurant.getAddress()) &&
                this.getFoods().equals(restaurant.getFoods()) &&
                this.getDrinks().equals(restaurant.getDrinks());
    }

}
