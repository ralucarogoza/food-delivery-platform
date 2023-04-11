package exceptions;

public class NoDeliveryDriverFoundException extends RuntimeException{
    public NoDeliveryDriverFoundException(String message){
        super(message);
    }
}
