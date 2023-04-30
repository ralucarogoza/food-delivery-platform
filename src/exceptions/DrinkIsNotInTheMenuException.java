package exceptions;

public class DrinkIsNotInTheMenuException extends RuntimeException{
    public DrinkIsNotInTheMenuException(String message){
        super(message);
    }
}
