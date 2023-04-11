package exceptions;

public class NoClientFoundException extends RuntimeException{
    public NoClientFoundException(String message){
        super(message);
    }
}
