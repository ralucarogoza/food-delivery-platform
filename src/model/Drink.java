package model;

public class Drink extends Product{
    private static int noDrinks = 1;
    private int id;
    private String flavour;
    private boolean isAlcoholic;

    public Drink(String name, boolean isVegan, double weight, double price, String flavour, boolean isAlcoholic) {
        super(name, isVegan, weight, price);
        this.id = noDrinks;
        this.flavour = flavour;
        this.isAlcoholic = isAlcoholic;
        noDrinks++;
    }

    public Drink(int id, String name, boolean isVegan, double weight, double price, String flavour, boolean isAlcoholic) {
        super(name, isVegan, weight, price);
        this.id = id;
        this.flavour = flavour;
        this.isAlcoholic = isAlcoholic;
    }

    public int getId() {
        return id;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    @Override
    public String toString() {
        return //"Drink: \n" +
                super.toString() +
                "\nFlavour: " + flavour +
                "\nIs alcoholic: " + isAlcoholic + "\n";
    }
}
