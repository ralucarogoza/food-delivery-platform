package exceptions;

public class NoAvailableDeliveryDriversException extends RuntimeException{
    public NoAvailableDeliveryDriversException(String message){
        super(message);
    }
}
