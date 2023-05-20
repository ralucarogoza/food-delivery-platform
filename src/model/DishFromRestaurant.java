package model;

public class DishFromRestaurant {
    private static int noDishesFromRestaurant = 0;
    private int id;
    private int idRestaurant;
    private int idDish;

    public DishFromRestaurant(int idRestaurant, int idDish) {
        this.id = noDishesFromRestaurant;
        this.idRestaurant = idRestaurant;
        this.idDish = idDish;
        noDishesFromRestaurant++;
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

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }
}
