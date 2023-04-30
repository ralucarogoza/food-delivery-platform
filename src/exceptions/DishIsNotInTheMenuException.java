package exceptions;

public class DishIsNotInTheMenuException extends RuntimeException{
    public DishIsNotInTheMenuException(String message){
        super(message);
    }
}
