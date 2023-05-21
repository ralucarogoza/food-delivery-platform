package repositories;

import config.DatabaseConfiguration;
import model.Client;
import model.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DishRepository {
    private List<Dish> dishes;
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public DishRepository(DatabaseConfiguration databaseConfiguration){
        this.dishes = new ArrayList<>();
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<Dish> getDishes() {
        List<Dish> dishes = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dish");
            while(resultSet.next()){
                Dish dish = new Dish(resultSet.getString("name"),
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
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("insert into dish values (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, dish.getId());
            preparedStatement.setString(2, dish.getName());
            preparedStatement.setDouble(3, dish.getWeight());
            preparedStatement.setDouble(4, dish.getPrice());
            preparedStatement.setInt(5, dish.getCalories());
            preparedStatement.execute();
            dishes.add(dish);
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
            dishes.remove(dish);
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDish(Dish oldDish, Dish newDish){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("update dish set id = ?, name = ?, weight = ?, price = ?, calories = ? where id = ?");
            preparedStatement.setInt(1, newDish.getId());
            preparedStatement.setString(2, newDish.getName());
            preparedStatement.setDouble(3, newDish.getWeight());
            preparedStatement.setDouble(4, newDish.getPrice());
            preparedStatement.setInt(5, newDish.getCalories());
            preparedStatement.setInt(6, oldDish.getId());
            preparedStatement.execute();
            dishes.remove(oldDish);
            dishes.add(newDish);
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
