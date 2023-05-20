package repositories;

import model.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishRepository {
    private List<Dish> dishes;
    public DishRepository(){
        this.dishes = new ArrayList<>();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish){
        dishes.add(dish);
    }

    public void removeDish(Dish dish){
        dishes.remove(dish);
    }
}
