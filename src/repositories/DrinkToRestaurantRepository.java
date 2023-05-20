package repositories;

import model.Drink;
import model.DrinkFromRestaurant;
import model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DrinkToRestaurantRepository {
    private List<DrinkFromRestaurant> drinksFromRestaurants;

    public DrinkToRestaurantRepository(){
        this.drinksFromRestaurants = new ArrayList<>();
    }

    public List<DrinkFromRestaurant> getDrinksFromRestaurants() {
        return drinksFromRestaurants;
    }

    public void addDrinkToRestaurant(DrinkFromRestaurant drink){
        drinksFromRestaurants.add(drink);
    }

    public void removeDrinkFromRestaurant(DrinkFromRestaurant drink){
        drinksFromRestaurants.remove(drink);
    }
}
