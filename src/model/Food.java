package model;

import java.util.List;

public class Food extends Product{
    private List<String> ingredients;

    public Food(String name, boolean isVegan, double weight, double price, Integer calories, List<String> ingredients) {
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
