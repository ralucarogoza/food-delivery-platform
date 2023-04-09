package model;

public class Drink extends Product{
    private String flavour;
    private boolean isAlcoholic;


    public Drink(String name, boolean isVegan, double weight, double price, Integer calories, String flavour, boolean isAlcoholic) {
        super(name, isVegan, weight, price, calories);
        this.flavour = flavour;
        this.isAlcoholic = isAlcoholic;
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
        return "Drink{" +
                super.toString() +
                "flavour='" + flavour + '\'' +
                ", isAlcoholic=" + isAlcoholic +
                '}';
    }
}
