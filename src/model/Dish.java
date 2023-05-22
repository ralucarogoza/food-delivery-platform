package model;

import java.util.List;

public class Dish extends Product{
    private static int noDishes = 1;
    private int id;
    private int calories;
    //private List<String> ingredients;

    public Dish(String name, boolean isVegan, double weight, double price, int calories) {
        super(name, isVegan, weight, price);
        this.id = noDishes;
        this.calories = calories;
        noDishes++;
    }

    public Dish(int id, String name, boolean isVegan, double weight, double price, int calories) {
        super(name, isVegan, weight, price);
        this.id = id;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    /*    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }*/

    @Override
    public String toString() {
        return //"Dish: \n" +
                super.toString() +
                "\nCalories: " + calories;
    }
}
