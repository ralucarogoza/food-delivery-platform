package exceptions;

public class DeliveryDriverNotFoundException extends RuntimeException{
    public DeliveryDriverNotFoundException(String message){
        super(message);
    }
}
