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
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("select * from drink_from_restaurant where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                drinkFromRestaurant = new DrinkFromRestaurant(resultSet.getInt("id"),
                        resultSet.getInt("idRestaurant"),
                        resultSet.getInt("idDrink"));
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
            ResultSet resultSet = statement.executeQuery("select * from drink_from_restaurant");
            while(resultSet.next()){
                DrinkFromRestaurant drink = new DrinkFromRestaurant(resultSet.getInt("id"),
                        resultSet.getInt("idRestaurant"),
                        resultSet.getInt("idDrink"));
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
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("insert into drink_from_restaurant values (?, ?, ?)");
            preparedStatement.setInt(1, drink.getId());
            preparedStatement.setInt(2, drink.getIdRestaurant());
            preparedStatement.setInt(3, drink.getIdDrink());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deleteDrinkFromRestaurant(DrinkFromRestaurant drink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("delete from drink_from_restaurant where id = ?");
            preparedStatement.setInt(1, drink.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDrinkFromRestaurant(DrinkFromRestaurant oldDrink, DrinkFromRestaurant newDrink){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("update drink_from_restaurant set id = ?, idRestaurant = ?, idDrink = ? where id = ?");
            preparedStatement.setInt(1, newDrink.getId());
            preparedStatement.setInt(2, newDrink.getIdRestaurant());
            preparedStatement.setInt(3, newDrink.getIdDrink());
            preparedStatement.setInt(4, oldDrink.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
