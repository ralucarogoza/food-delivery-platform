package exceptions;

public class NoDrinkFoundException extends RuntimeException{
    public NoDrinkFoundException(String message){
        super(message);
    }
}
