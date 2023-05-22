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
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("select * from dish_from_restaurant where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                dishFromRestaurant = new DishFromRestaurant(resultSet.getInt("id"),
                        resultSet.getInt("idRestaurant"),
                        resultSet.getInt("idDish"));
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
            ResultSet resultSet = statement.executeQuery("select * from dish_from_restaurant");
            while(resultSet.next()){
                DishFromRestaurant dish = new DishFromRestaurant(resultSet.getInt("id"),
                        resultSet.getInt("idRestaurant"),
                        resultSet.getInt("idDish"));
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
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("insert into dish_from_restaurant values (?, ?, ?)");
            preparedStatement.setInt(1, dish.getId());
            preparedStatement.setInt(2, dish.getIdRestaurant());
            preparedStatement.setInt(3, dish.getIdDish());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deleteDishFromRestaurant(DishFromRestaurant dish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("delete from dish_from_restaurant where id = ?");
            preparedStatement.setInt(1, dish.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDishFromRestaurant(DishFromRestaurant oldDish, DishFromRestaurant newDish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("update dish_from_restaurant set id = ?, idRestaurant = ?, idDish = ? where id = ?");
            preparedStatement.setInt(1, newDish.getId());
            preparedStatement.setInt(2, newDish.getIdRestaurant());
            preparedStatement.setInt(3, newDish.getIdDish());
            preparedStatement.setInt(4, oldDish.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
