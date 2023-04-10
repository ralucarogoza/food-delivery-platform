package model;

public abstract class Product {
    private String name;
    private boolean isVegan;
    private double weight;
    private double price;
    private Integer calories;

    public Product(String name, boolean isVegan, double weight, double price, Integer calories) {
        this.name = name;
        this.isVegan = isVegan;
        this.weight = weight;
        this.price = price;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nIs vegan: " + isVegan +
                "\nWeight: " + weight +
                "\nPrice: " + price + "$" +
                "\nCalories: " + calories;
    }
}
