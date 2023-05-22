package exceptions;

public class NoDishFromRestaurantFoundException extends RuntimeException{
    public NoDishFromRestaurantFoundException(String message){
        super(message);
    }
}
