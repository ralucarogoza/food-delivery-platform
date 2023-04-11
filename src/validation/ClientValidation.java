package validation;

public class ClientValidation extends PersonValidation {
    public static boolean validateEmail(String email){
        if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            return true;
        return false;
    }
}
