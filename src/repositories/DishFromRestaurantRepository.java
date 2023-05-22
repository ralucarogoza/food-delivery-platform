package repositories;

import config.DatabaseConfiguration;
import exceptions.NoDishFromRestaurantFoundException;
import model.DishFromRestaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;

public class DishFromRestaurantRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public DishFromRestaurantRepository(DatabaseConfiguration databaseConfiguration){
        this.databaseConfiguration = databaseConfiguration;
    }

    public DishFromRestaurant getDishFromRestaurantById(int id){
        DishFromRestaurant dishFromRestaurant = null;
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_GET_DISH_FROM_RESTAURANT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                dishFromRestaurant = new DishFromRestaurant(resultSet.getInt("id"),
                        resultSet.getInt("idDish"),
                        resultSet.getInt("idRestaurant"));
                System.out.println("DishFromRestaurant found!");
            }
            else{
                throw new NoDishFromRestaurantFoundException("DishFromRestaurant with id " + id + " doesn't exist!");
            }
        }
        catch (NoDishFromRestaurantFoundException noDishFromRestaurantFoundException){
            System.out.println(noDishFromRestaurantFoundException.getMessage());
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return dishFromRestaurant;
    }

    public List<DishFromRestaurant> getDishesFromRestaurants() {
        List<DishFromRestaurant> dishes = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_DISHES_FROM_RESTAURANTS);
            while(resultSet.next()){
                DishFromRestaurant dish = new DishFromRestaurant(resultSet.getInt("id"),
                        resultSet.getInt("idDish"),
                        resultSet.getInt("idRestaurant"));
                dishes.add(dish);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return dishes;
    }

    public void addDishToRestaurant(DishFromRestaurant dish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_ADD_DISH_FROM_RESTAURANT);
            preparedStatement.setInt(1, dish.getId());
            preparedStatement.setInt(2, dish.getIdDish());
            preparedStatement.setInt(3, dish.getIdRestaurant());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deleteDishFromRestaurant(DishFromRestaurant dish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_DELETE_DISH_FROM_RESTAURANT);
            preparedStatement.setInt(1, dish.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDishFromRestaurant(DishFromRestaurant oldDish, DishFromRestaurant newDish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_UPDATE_DISH_FROM_RESTAURANT);
            preparedStatement.setInt(1, newDish.getId());
            preparedStatement.setInt(2, newDish.getIdDish());
            preparedStatement.setInt(3, newDish.getIdRestaurant());
            preparedStatement.setInt(4, oldDish.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
