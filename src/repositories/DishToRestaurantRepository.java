package repositories;

import model.Dish;
import model.DishFromRestaurant;
import model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DishToRestaurantRepository {
    private List<DishFromRestaurant> dishesFromRestaurants;

    public DishToRestaurantRepository() {
        this.dishesFromRestaurants = new ArrayList<>();
    }

    public List<DishFromRestaurant> getDishesFromRestaurants() {
        return dishesFromRestaurants;
    }

    public void addDishToRestaurant(DishFromRestaurant dishFromRestaurant){
        dishesFromRestaurants.add(dishFromRestaurant);
    }

    public void removeDishFromRestaurant(DishFromRestaurant dishFromRestaurant){
        dishesFromRestaurants.remove(dishFromRestaurant);
    }
}
