package model;

public class Client extends Person{
    private String email;
    private String password;

    public Client(String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  "Client{" +
                super.toString() +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
