package repositories;

import config.DatabaseConfiguration;
import model.Dish;
import model.Drink;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {
    private List<Drink> drinks;
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public DrinkRepository(DatabaseConfiguration databaseConfiguration){
        this.drinks = new ArrayList<>();
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<Drink> getDrinks() {
        List<Drink> dishes = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from drink");
            while(resultSet.next()){
                Drink drink = new Drink(resultSet.getString("name"),
                        resultSet.getBoolean("isVegan"),
                        resultSet.getDouble("weight"),
                        resultSet.getDouble("price"),
                        resultSet.getString("flavour"),
                        resultSet.getBoolean("isAlcoholic"));
                drinks.add(drink);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return dishes;
    }

    public void addDrink(Drink drink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("insert into drink values (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, drink.getId());
            preparedStatement.setString(2, drink.getName());
            preparedStatement.setDouble(3, drink.getWeight());
            preparedStatement.setDouble(4, drink.getPrice());
            preparedStatement.setString(5, drink.getFlavour());
            preparedStatement.setBoolean(6, drink.isAlcoholic());
            preparedStatement.execute();
            drinks.add(drink);
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void removeDrink(Drink drink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("delete from drink where id = ?");
            preparedStatement.setInt(1, drink.getId());
            preparedStatement.execute();
            drinks.remove(drink);
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDrink(Drink oldDrink, Drink newDrink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("update drink set id = ?, name = ?, weight = ?, price = ?, flavour = ?, isAlcoholic = ? where id = ?");
            preparedStatement.setInt(1, newDrink.getId());
            preparedStatement.setString(2, newDrink.getName());
            preparedStatement.setDouble(3, newDrink.getWeight());
            preparedStatement.setDouble(4, newDrink.getPrice());
            preparedStatement.setString(5, newDrink.getFlavour());
            preparedStatement.setBoolean(6, newDrink.isAlcoholic());
            preparedStatement.setInt(7, oldDrink.getId());
            preparedStatement.execute();
            drinks.remove(oldDrink);
            drinks.add(newDrink);
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
