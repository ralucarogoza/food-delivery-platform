package repositories;

import config.DatabaseConfiguration;
import exceptions.NoDrinkFromRestaurantFoundException;

import model.DrinkFromRestaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;

public class DrinkFromRestaurantRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public DrinkFromRestaurantRepository(DatabaseConfiguration databaseConfiguration){
        this.databaseConfiguration = databaseConfiguration;
    }


    public DrinkFromRestaurant getDrinkFromRestaurantById(int id){
        DrinkFromRestaurant drinkFromRestaurant = null;
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_GET_DRINK_FROM_RESTAURANT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                drinkFromRestaurant = new DrinkFromRestaurant(resultSet.getInt("id"),
                        resultSet.getInt("idDrink"),
                        resultSet.getInt("idRestaurant"));
                System.out.println("DrinkFromRestaurant found!");
            }
            else{
                throw new NoDrinkFromRestaurantFoundException("DrinkFromRestaurant with id " + id + " doesn't exist!");
            }
        }
        catch (NoDrinkFromRestaurantFoundException noDrinkFromRestaurantFoundException){
            System.out.println(noDrinkFromRestaurantFoundException.getMessage());
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return drinkFromRestaurant;
    }

    public List<DrinkFromRestaurant> getDrinksFromRestaurants() {
        List<DrinkFromRestaurant> drinks = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_DRINKS_FROM_RESTAURANTS);
            while(resultSet.next()){
                DrinkFromRestaurant drink = new DrinkFromRestaurant(resultSet.getInt("id"),
                        resultSet.getInt("idDrink"),
                        resultSet.getInt("idRestaurant"));
                drinks.add(drink);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return drinks;
    }

    public void addDrinkToRestaurant(DrinkFromRestaurant drink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_ADD_DRINK_FROM_RESTAURANT);
            preparedStatement.setInt(1, drink.getId());
            preparedStatement.setInt(2, drink.getIdDrink());
            preparedStatement.setInt(3, drink.getIdRestaurant());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deleteDrinkFromRestaurant(DrinkFromRestaurant drink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_DELETE_DRINK_FROM_RESTAURANT);
            preparedStatement.setInt(1, drink.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDrinkFromRestaurant(DrinkFromRestaurant oldDrink, DrinkFromRestaurant newDrink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_UPDATE_DRINK_FROM_RESTAURANT);
            preparedStatement.setInt(1, newDrink.getId());
            preparedStatement.setInt(2, newDrink.getIdDrink());
            preparedStatement.setInt(3, newDrink.getIdRestaurant());
            preparedStatement.setInt(4, oldDrink.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
