package repositories;

import config.DatabaseConfiguration;
import model.Address;
import model.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;

public class RestaurantRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public RestaurantRepository(DatabaseConfiguration databaseConfiguration){
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        AddressRepository addressRepository = new AddressRepository(getDatabaseConfiguration());
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_RESTAURANTS);
            while(resultSet.next()){
                Restaurant restaurant = new Restaurant(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        addressRepository.getAddressById(resultSet.getInt("idAddress")));
                restaurants.add(restaurant);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return restaurants;
    }
    public void addRestaurant(Restaurant restaurant){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_ADD_RESTAURANT);
            preparedStatement.setInt(1, restaurant.getId());
            preparedStatement.setString(2, restaurant.getName());
            preparedStatement.setInt(3, restaurant.getAddress().getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void deleteRestaurant(Restaurant restaurant){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_DELETE_RESTAURANT);
            preparedStatement.setInt(1, restaurant.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateRestaurant(Restaurant oldRestaurant, Restaurant newRestaurant){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_UPDATE_RESTAURANT);
            preparedStatement.setInt(1, newRestaurant.getId());
            preparedStatement.setString(2, newRestaurant.getName());
            preparedStatement.setInt(3, newRestaurant.getAddress().getId());
            preparedStatement.setInt(4, oldRestaurant.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
