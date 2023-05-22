package model;

public class Address {
    private static int noAddresses = 1;
    private int id = 0;
    private String city;
    private String street;
    private int number;

    public Address(String city, String street, int number) {
        this.id = noAddresses;
        this.city = city;
        this.street = street;
        this.number = number;
        noAddresses++;
    }

    public Address(int id, String city, String street, int number) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public static int getNoAddresses() {
        return noAddresses;
    }

    public int getId() {
        return id;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                ", City: " + city +
                ", Street: " + street +
                ", Number: " + number ;
    }
}
