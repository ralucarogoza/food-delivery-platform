package model;

public abstract class Product {
    private String name;
    private boolean isVegan;
    private double weight;
    private double price;

    public Product(String name, boolean isVegan, double weight, double price) {
        this.name = name;
        this.isVegan = isVegan;
        this.weight = weight;
        this.price = price;
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
    @Override
    public String toString() {
        return "Name: " + name +
                "\nIs vegan: " + isVegan +
                "\nWeight: " + weight +
                "\nPrice: " + price + "$";
    }
}
