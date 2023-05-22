package exceptions;

import model.Restaurant;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(String message){
        super(message);
    }
}
