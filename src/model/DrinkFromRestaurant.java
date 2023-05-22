package model;

public class DrinkFromRestaurant {
    private static int noDrinksFromRestaurant = 1;
    private int id;
    private int idRestaurant;
    private int idDrink;

    public DrinkFromRestaurant(int idRestaurant, int idDrink) {
        this.id = noDrinksFromRestaurant;
        this.idRestaurant = idRestaurant;
        this.idDrink = idDrink;
        noDrinksFromRestaurant++;
    }

    public DrinkFromRestaurant(int id, int idRestaurant, int idDrink) {
        this.id = id;
        this.idRestaurant = idRestaurant;
        this.idDrink = idDrink;
    }

    public int getId() {
        return id;
    }
    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public int getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }
}
