package repositories;

import model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {
    private List<Restaurant> restaurants;
    public RestaurantRepository(){
        restaurants = new ArrayList<>();
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    public void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }
    public void removeRestaurant(Restaurant restaurant){
        restaurants.remove(restaurant);
    }
}
