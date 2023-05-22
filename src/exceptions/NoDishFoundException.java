package exceptions;

public class NoDishFoundException extends RuntimeException{
    public NoDishFoundException(String message){
        super(message);
    }
}
