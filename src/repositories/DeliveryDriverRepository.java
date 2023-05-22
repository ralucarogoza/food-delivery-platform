package repositories;

import config.DatabaseConfiguration;
import exceptions.ClientNotFoundException;
import exceptions.DeliveryDriverNotFoundException;
import model.Client;
import model.DeliveryDriver;
import model.DeliveryDriverStatus;
import model.DeliveryMethod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DeliveryDriverRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public DeliveryDriverRepository(DatabaseConfiguration databaseConfiguration){
        this.databaseConfiguration = databaseConfiguration;
    }

    public DeliveryDriver getDeliveryDriverById(int id){
        DeliveryDriver deliveryDriver = null;
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("select * from delivery_driver where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                deliveryDriver = new DeliveryDriver(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber"),
                        DeliveryMethod.valueOf(resultSet.getString("deliveryMethod")),
                        DeliveryDriverStatus.valueOf(resultSet.getString("deliveryDriverStatus")));
                System.out.println("DeliveryDriver found!");
            }
            else{
                throw new DeliveryDriverNotFoundException("DeliveryDriver with id " + id + " doesn't exist!");
            }
        }
        catch (DeliveryDriverNotFoundException deliveryDriverNotFoundException){
            System.out.println(deliveryDriverNotFoundException.getMessage());
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return deliveryDriver;
    }

    public List<DeliveryDriver> getDeliveryDrivers() {
        List<DeliveryDriver> deliveryDrivers = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from delivery_driver");
            while(resultSet.next()){
                DeliveryDriver deliveryDriver = new DeliveryDriver(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber"),
                        DeliveryMethod.valueOf(resultSet.getString("deliveryMethod")),
                        DeliveryDriverStatus.valueOf(resultSet.getString("deliveryDriverStatus")));
                deliveryDrivers.add(deliveryDriver);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return deliveryDrivers;
    }

    public List<DeliveryDriver> getAvailableDeliveryDrivers() {
        List<DeliveryDriver> deliveryDrivers = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from delivery_driver where deliveryDriverStatus = AVAILABLE");
            while(resultSet.next()){
                DeliveryDriver deliveryDriver = new DeliveryDriver(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber"),
                        DeliveryMethod.valueOf(resultSet.getString("deliveryMethod")),
                        DeliveryDriverStatus.valueOf(resultSet.getString("deliveryDriverStatus")));
                deliveryDrivers.add(deliveryDriver);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return deliveryDrivers;
    }

    public void addDeliveryDriver(DeliveryDriver deliveryDriver){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("insert into delivery_driver values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, deliveryDriver.getId());
            preparedStatement.setString(2, deliveryDriver.getFirstName());
            preparedStatement.setString(3, deliveryDriver.getLastName());
            preparedStatement.setString(4, deliveryDriver.getPhoneNumber());
            preparedStatement.setString(5, deliveryDriver.getDeliveryMethod().toString());
            preparedStatement.setString(6, deliveryDriver.getDeliveryDriverStatus().toString());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deleteDeliveryDriver(DeliveryDriver deliveryDriver){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("delete from delivery_driver where id = ?");
            preparedStatement.setInt(1, deliveryDriver.getId());
            preparedStatement.execute();
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateDeliveryDriver(DeliveryDriver oldDeliveryDriver, DeliveryDriver newDeliveryDriver){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("update delivery_driver set id = ?, firstName = ?, lastName = ?, phoneNumber = ?, deliveryMethod = ?, deliveryDriverStatus = ? where id = ?");
            preparedStatement.setInt(1, newDeliveryDriver.getId());
            preparedStatement.setString(2, newDeliveryDriver.getFirstName());
            preparedStatement.setString(3, newDeliveryDriver.getLastName());
            preparedStatement.setString(4, newDeliveryDriver.getPhoneNumber());
            preparedStatement.setString(5, newDeliveryDriver.getDeliveryMethod().toString());
            preparedStatement.setString(6, newDeliveryDriver.getDeliveryDriverStatus().toString());
            preparedStatement.setInt(7, oldDeliveryDriver.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
