package repositories;

import model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {
    private List<Drink> drinks;
    public DrinkRepository(){
        this.drinks = new ArrayList<>();
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void addDrink(Drink drink){
        drinks.add(drink);
    }

    public void removeDrink(Drink drink){
        drinks.remove(drink);
    }
}
