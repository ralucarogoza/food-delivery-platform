package exceptions;

public class NoDrinkFromRestaurantFoundException extends RuntimeException{
    public NoDrinkFromRestaurantFoundException(String message){
        super(message);
    }
}
