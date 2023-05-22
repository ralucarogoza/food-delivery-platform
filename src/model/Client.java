package model;

public class Client extends Person{
    private static int noClients = 1;
    private int id;
    private String email;
    //private String password;

    public Client(int id, String firstName, String lastName, String phoneNumber, String email) {
        super(firstName, lastName, phoneNumber);
        this.id = id;
        this.email = email;
    }
    public Client(String firstName, String lastName, String phoneNumber, String email) {
        super(firstName, lastName, phoneNumber);
        this.id = noClients;
        this.email = email;
        noClients++;
    }

    public Client() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nId: " + this.id + "\n" +  super.toString() +
                "\nEmail: " + email + "\n";
    }
}
