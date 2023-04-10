package model;

import java.util.List;

public class Dish extends Product{
    private List<String> ingredients;

    public Dish(String name, boolean isVegan, double weight, double price, Integer calories, List<String> ingredients) {
        super(name, isVegan, weight, price, calories);
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return //"Dish: \n" +
                super.toString() +
                "\nIngredients: " + ingredients + "\n";
    }
}
