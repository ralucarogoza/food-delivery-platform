package model;

public class Client extends Person{
    private String email;
    //private String password;

    public Client(String firstName, String lastName, String phoneNumber, String email) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
    }

    public Client() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nEmail: " + email + "\n";
    }
}
