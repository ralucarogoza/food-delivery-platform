package repositories;

import config.DatabaseConfiguration;
import model.Address;
import model.Client;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AddressRepository {
    private List<Address> addresses;
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public AddressRepository(DatabaseConfiguration databaseConfiguration){
        this.addresses = new ArrayList<>();
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<Address> getAddresses() {
        List<Address> addresses2 = new ArrayList<>();
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            //List<Address> addresses2 = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select * from address");
            while(resultSet.next()){
                Address address = new Address(resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getInt("number"));
                System.out.println(address.getId());
                System.out.println(Address.getNoAddresses());
                addresses2.add(address);
            }
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }

        //this.addresses = addresses2;
        return addresses2;
    }


    public void addAddress(Address address){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("insert into address values (?, ?, ?, ?)");
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setInt(4, address.getNumber());
            preparedStatement.execute();
            addresses.add(address);
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        addresses.add(address);
    }


    public void deleteAddress(Address address){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("delete from address where id = ?");
            preparedStatement.setInt(1, address.getId());
            System.out.println("in delete");
            System.out.println(address);
            System.out.println("am term in delete");
            preparedStatement.execute();
            addresses.remove(address);
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        //addresses.remove(address);
    }

    public void updateAddress(Address oldAddress, Address newAddress){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("update address set id = ?, city = ?, street = ?, number = ? where id = ?");
            preparedStatement.setInt(1, newAddress.getId());
            preparedStatement.setString(2, newAddress.getCity());
            preparedStatement.setString(3, newAddress.getStreet());
            preparedStatement.setInt(4, newAddress.getNumber());
            preparedStatement.setInt(5, oldAddress.getId());
            preparedStatement.execute();
            System.out.println(oldAddress);
            System.out.println(preparedStatement);
            addresses.remove(oldAddress);
            addresses.add(newAddress);
            //int oldIndex = addresses.indexOf(oldAddress);
            //addresses.set(oldIndex, newAddress);
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
