package repositories;

import config.DatabaseConfiguration;
import exceptions.ClientNotFoundException;
import exceptions.DrinkNotFoundException;
import model.Client;
import model.Drink;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constants.Constants.*;

public class DrinkRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public DrinkRepository(DatabaseConfiguration databaseConfiguration){
        this.databaseConfiguration = databaseConfiguration;
    }


    public Optional<Drink> getDrinkById(int id){
        Drink drink = null;
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_GET_DRINK_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                drink = new Drink(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("isVegan"),
                        resultSet.getDouble("weight"),
                        resultSet.getDouble("price"),
                        resultSet.getString("flavour"),
                        resultSet.getBoolean("isAlcoholic"));
                System.out.println("Drink found!");
            }
            else{
                throw new DrinkNotFoundException("Drink with id " + id + " doesn't exist!");
            }
        }
        catch(DrinkNotFoundException drinkNotFoundException){
            System.out.println(drinkNotFoundException.getMessage());
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return Optional.ofNullable(drink);
    }


    public List<Drink> getDrinks() {
        List<Drink> drinks = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_DRINKS);
            while(resultSet.next()){
                Drink drink = new Drink(resultSet.getInt("id"),
                        resultSet.getString("name"),
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
        return drinks;
    }

    public void addDrink(Drink drink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_ADD_DRINK);
            preparedStatement.setInt(1, drink.getId());
            preparedStatement.setString(2, drink.getName());
            preparedStatement.setBoolean(3, drink.isVegan());
            preparedStatement.setDouble(4, drink.getWeight());
            preparedStatement.setDouble(5, drink.getPrice());
            preparedStatement.setString(6, drink.getFlavour());
            preparedStatement.setBoolean(7, drink.isAlcoholic());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deleteDrink(Drink drink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_DELETE_DRINK);
            preparedStatement.setInt(1, drink.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDrink(Drink oldDrink, Drink newDrink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_UPDATE_DRINK);
            preparedStatement.setInt(1, newDrink.getId());
            preparedStatement.setString(2, newDrink.getName());
            preparedStatement.setBoolean(3, newDrink.isVegan());
            preparedStatement.setDouble(4, newDrink.getWeight());
            preparedStatement.setDouble(5, newDrink.getPrice());
            preparedStatement.setString(6, newDrink.getFlavour());
            preparedStatement.setBoolean(7, newDrink.isAlcoholic());
            preparedStatement.setInt(8, oldDrink.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
