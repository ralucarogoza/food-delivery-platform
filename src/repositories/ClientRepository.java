package repositories;

import config.DatabaseConfiguration;
import model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ClientRepository {
    private Map<String, Client> clients;
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public ClientRepository(DatabaseConfiguration databaseConfiguration) {
        this.clients = new TreeMap<>();
        this.databaseConfiguration = databaseConfiguration;
    }



    public Map<String, Client> getClients() {
        return this.clients;
    }
    public void removeClient(Client client){
        clients.remove(client.getEmail());
    }



    public Map<String, Client> getAllClients() throws SQLException {
        Statement statement = databaseConfiguration.getConnection().createStatement();
        Map<String, Client> clients = new TreeMap<>();
        ResultSet resultSet = statement.executeQuery("select * from schema.client");
        while(resultSet.next()){
            Client client = new Client(resultSet.getString("firstName"),
                                        resultSet.getString("lastName"),
                                        resultSet.getString("phoneNumber"),
                                        resultSet.getString("email"));
            clients.put(client.getEmail(), client);
        }
        return clients;
    }

    public void addClient(Client client) throws SQLException {
        PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("insert into client values (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, client.getId());
        preparedStatement.setString(2, client.getFirstName());
        preparedStatement.setString(3, client.getLastName());
        preparedStatement.setString(4, client.getPhoneNumber());
        preparedStatement.setString(5, client.getEmail());
        preparedStatement.execute();
        clients.put(client.getEmail(), client);
    }

    public void deleteClient(Client client) throws SQLException {
        PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("delete from client where id = ?");
        preparedStatement.setInt(1, client.getId());
        preparedStatement.execute();
        clients.remove(client.getEmail());
    }

    public void updateClient(Client oldClient, Client newClient) throws SQLException {
        PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement("update client set id = ?, firstName = ?, lastName = ?, phoneNumber = ?, email = ? where id = ?");
        preparedStatement.setInt(1, newClient.getId());
        preparedStatement.setString(2, newClient.getFirstName());
        preparedStatement.setString(3, newClient.getLastName());
        preparedStatement.setString(4, newClient.getPhoneNumber());
        preparedStatement.setString(5, newClient.getEmail());
        preparedStatement.setInt(6, oldClient.getId());
        preparedStatement.execute();
        clients.remove(oldClient.getEmail());
        clients.put(newClient.getEmail(), newClient);
    }
}
