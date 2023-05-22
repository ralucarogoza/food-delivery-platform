package repositories;

import config.DatabaseConfiguration;
import exceptions.DishNotFoundException;

import model.Dish;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DishRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public DishRepository(DatabaseConfiguration databaseConfiguration){
        this.databaseConfiguration = databaseConfiguration;
    }

    public Optional<Dish> getDishById(int id){
        Dish dish = null;
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("select * from dish where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                dish = new Dish(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("isVegan"),
                        resultSet.getDouble("weight"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("calories"));
                System.out.println("Dish found!");
            }
            else{
                throw new DishNotFoundException("Dish with id " + id + " doesn't exist!");
            }
        }
        catch(DishNotFoundException dishNotFoundException){
            System.out.println(dishNotFoundException.getMessage());
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return Optional.ofNullable(dish);
    }


    public List<Dish> getDishes() {
        List<Dish> dishes = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dish");
            while(resultSet.next()){
                Dish dish = new Dish(resultSet.getInt("id"),
                                        resultSet.getString("name"),
                                        resultSet.getBoolean("isVegan"),
                                        resultSet.getDouble("weight"),
                                        resultSet.getDouble("price"),
                                        resultSet.getInt("calories"));
                dishes.add(dish);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return dishes;
    }

    public void addDish(Dish dish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("insert into dish values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, dish.getId());
            preparedStatement.setString(2, dish.getName());
            preparedStatement.setBoolean(3, dish.isVegan());
            preparedStatement.setDouble(4, dish.getWeight());
            preparedStatement.setDouble(5, dish.getPrice());
            preparedStatement.setInt(6, dish.getCalories());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void removeDish(Dish dish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("delete from dish where id = ?");
            preparedStatement.setInt(1, dish.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDish(Dish oldDish, Dish newDish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("update dish set id = ?, name = ?, isVegan = ?, weight = ?, price = ?, calories = ? where id = ?");
            preparedStatement.setInt(1, newDish.getId());
            preparedStatement.setString(2, newDish.getName());
            preparedStatement.setBoolean(3, newDish.isVegan());
            preparedStatement.setDouble(4, newDish.getWeight());
            preparedStatement.setDouble(5, newDish.getPrice());
            preparedStatement.setInt(6, newDish.getCalories());
            preparedStatement.setInt(7, oldDish.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
