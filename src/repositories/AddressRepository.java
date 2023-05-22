package repositories;

import config.DatabaseConfiguration;
import exceptions.AddressNotFoundException;
import exceptions.DrinkNotFoundException;
import model.Address;
import model.Client;
import model.Drink;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static constants.Constants.*;

public class AddressRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public AddressRepository(DatabaseConfiguration databaseConfiguration){
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<Address> getAddresses() {
        List<Address> addresses2 = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_ADDRESSES);
            while(resultSet.next()){
                Address address = new Address(resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getInt("number"));
                addresses2.add(address);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return addresses2;
    }

    public Address getAddressById(int id){
        Address address = null;
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_GET_ADDRESS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                address = new Address(resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getInt("number"));
                System.out.println("Address found!");
            }
            else{
                throw new AddressNotFoundException("Address with id " + id + " doesn't exist!");
            }
        }
        catch(AddressNotFoundException addressNotFoundException){
            System.out.println(addressNotFoundException.getMessage());
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return address;
    }

    public void addAddress(Address address){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_INSERT_ADDRESS);
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setInt(4, address.getNumber());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }


    public void deleteAddress(Address address){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_DELETE_ADDRESS);
            preparedStatement.setInt(1, address.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateAddress(Address oldAddress, Address newAddress){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_UPDATE_ADDRESS);
            preparedStatement.setInt(1, newAddress.getId());
            preparedStatement.setString(2, newAddress.getCity());
            preparedStatement.setString(3, newAddress.getStreet());
            preparedStatement.setInt(4, newAddress.getNumber());
            preparedStatement.setInt(5, oldAddress.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
