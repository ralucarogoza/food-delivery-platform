package repositories;

import config.DatabaseConfiguration;
import exceptions.OrderNotFoundException;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.*;

public class OrderRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    public OrderRepository(DatabaseConfiguration databaseConfiguration){
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        ClientRepository clientRepository = new ClientRepository(databaseConfiguration);
        AddressRepository addressRepository = new AddressRepository(databaseConfiguration);
        DeliveryDriverRepository deliveryDriverRepository = new DeliveryDriverRepository(databaseConfiguration);
        DishFromRestaurantRepository dishFromRestaurantRepository = new DishFromRestaurantRepository(databaseConfiguration);
        DrinkFromRestaurantRepository drinkFromRestaurantRepository = new DrinkFromRestaurantRepository(databaseConfiguration);
        try{
            Statement statement = databaseConfiguration.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_ORDERS);
            while(resultSet.next()){
                Order order = new Order(resultSet.getInt("id"),
                                        clientRepository.getClientById(resultSet.getInt("idClient")),
                                        addressRepository.getAddressById(resultSet.getInt("idAddress")),
                                        deliveryDriverRepository.getDeliveryDriverById(resultSet.getInt("idDeliveryDriver")),
                                        dishFromRestaurantRepository.getDishFromRestaurantById(resultSet.getInt("idDish")),
                                        drinkFromRestaurantRepository.getDrinkFromRestaurantById(resultSet.getInt("idDrink")),
                                        OrderStatus.valueOf(resultSet.getString("orderStatus")));
                orders.add(order);
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return orders;
    }

    public Order getOrderById(int id){
        Order order = null;
        ClientRepository clientRepository = new ClientRepository(databaseConfiguration);
        AddressRepository addressRepository = new AddressRepository(databaseConfiguration);
        DeliveryDriverRepository deliveryDriverRepository = new DeliveryDriverRepository(databaseConfiguration);
        DishFromRestaurantRepository dishFromRestaurantRepository = new DishFromRestaurantRepository(databaseConfiguration);
        DrinkFromRestaurantRepository drinkFromRestaurantRepository = new DrinkFromRestaurantRepository(databaseConfiguration);
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_GET_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                order = new Order(resultSet.getInt("id"),
                        clientRepository.getClientById(resultSet.getInt("idClient")),
                        addressRepository.getAddressById(resultSet.getInt("idAddress")),
                        deliveryDriverRepository.getDeliveryDriverById(resultSet.getInt("idDeliveryDriver")),
                        dishFromRestaurantRepository.getDishFromRestaurantById(resultSet.getInt("idDish")),
                        drinkFromRestaurantRepository.getDrinkFromRestaurantById(resultSet.getInt("idDrink")),
                        OrderStatus.valueOf(resultSet.getString("orderStatus")));
                System.out.println("Order found!");
            }
            else{
                throw new OrderNotFoundException("Order with id " + id + " doesn't exist!");
            }
        }
        catch(OrderNotFoundException orderNotFoundException){
            System.out.println(orderNotFoundException.getMessage());
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return order;
    }

    public void addOrder(Order order){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_ADD_ORDER);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getClient().getId());
            preparedStatement.setInt(3, order.getClientAddress().getId());
            preparedStatement.setInt(4, order.getDeliveryDriver().getId());
            preparedStatement.setInt(5, order.getOrderedDish().getId());
            preparedStatement.setInt(6, order.getOrderedDrink().getId());
            preparedStatement.setObject(7, order.getOrderDate());
            preparedStatement.setString(8, order.getOrderStatus().toString());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }


    public void deleteOrder(Order order){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_DELETE_ORDER);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void updateOrder(Order oldOrder, Order newOrder){
        try{
            PreparedStatement preparedStatement = databaseConfiguration.getConnection().prepareStatement(QUERY_UPDATE_ORDER);
            preparedStatement.setInt(1, newOrder.getId());
            preparedStatement.setInt(2, newOrder.getClient().getId());
            preparedStatement.setInt(3, newOrder.getClientAddress().getId());
            preparedStatement.setInt(4, newOrder.getDeliveryDriver().getId());
            preparedStatement.setInt(5, newOrder.getOrderedDish().getId());
            preparedStatement.setInt(6, newOrder.getOrderedDrink().getId());
            preparedStatement.setObject(7, newOrder.getOrderDate());
            preparedStatement.setString(8, newOrder.getOrderStatus().toString());
            preparedStatement.setInt(9, oldOrder.getId());
            preparedStatement.execute();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
