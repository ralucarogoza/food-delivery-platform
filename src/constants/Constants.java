package constants;

public class Constants {

    // QUERIES CLIENT
    public static final String QUERY_GET_ALL_CLIENTS = "select * from client";
    public static final String QUERY_GET_CLIENT_BY_EMAIL = "select * from client where email = ?";
    public static final String QUERY_GET_CLIENT_BY_ID = "select * from client where id = ?";
    public static final String QUERY_INSERT_CLIENT = "insert into client values (?, ?, ?, ?, ?)";
    public static final String QUERY_DELETE_CLIENT = "delete from client where id = ?";
    public static final String QUERY_UPDATE_CLIENT = "update client set id = ?, firstName = ?, lastName = ?, phoneNumber = ?, email = ? where id = ?";


    // QUERIES ADDRESS
    public static final String QUERY_GET_ALL_ADDRESSES = "select * from address";
    public static final String QUERY_GET_ADDRESS_BY_ID = "select * from address where id = ?";
    public static final String QUERY_INSERT_ADDRESS = "insert into address values (?, ?, ?, ?)";
    public static final String QUERY_DELETE_ADDRESS = "delete from address where id = ?";
    public static final String QUERY_UPDATE_ADDRESS = "update address set id = ?, city = ?, street = ?, number = ? where id = ?";


    // QUERIES DELIVERY DRIVER
    public static final String QUERY_GET_ALL_DELIVERY_DRIVERS = "select * from delivery_driver";
    public static final String QUERY_GET_AVAILABLE_DELIVERY_DRIVERS = "select * from delivery_driver where deliveryDriverStatus = AVAILABLE";
    public static final String QUERY_GET_DELIVERY_DRIVER_BY_ID = "select * from delivery_driver where id = ?";
    public static final String QUERY_INSERT_DELIVERY_DRIVER = "insert into delivery_driver values (?, ?, ?, ?, ?, ?)";
    public static final String QUERY_DELETE_DELIVERY_DRIVER = "delete from delivery_driver where id = ?";
    public static final String QUERY_UPDATE_DELIVERY_DRIVER = "update delivery_driver set id = ?, firstName = ?, lastName = ?, phoneNumber = ?, deliveryMethod = ?, deliveryDriverStatus = ? where id = ?";


    // QUERIES DISH FROM RESTAURANT

    public static final String QUERY_GET_DISHES_FROM_RESTAURANTS = "select * from dish_from_restaurant";
    public static final String QUERY_GET_DISH_FROM_RESTAURANT_BY_ID = "select * from dish_from_restaurant where id = ?";
    public static final String QUERY_ADD_DISH_FROM_RESTAURANT = "insert into dish_from_restaurant values (?, ?, ?)";
    public static final String QUERY_DELETE_DISH_FROM_RESTAURANT = "delete from dish_from_restaurant where id = ?";
    public static final String QUERY_UPDATE_DISH_FROM_RESTAURANT = "update dish_from_restaurant set id = ?, idDish = ?, idRestaurant = ? where id = ?";



    // QUERIES DRINK FROM RESTAURANT

    public static final String QUERY_GET_DRINKS_FROM_RESTAURANTS = "select * from drink_from_restaurant";
    public static final String QUERY_GET_DRINK_FROM_RESTAURANT_BY_ID = "select * from drink_from_restaurant where id = ?";
    public static final String QUERY_ADD_DRINK_FROM_RESTAURANT = "insert into drink_from_restaurant values (?, ?, ?)";
    public static final String QUERY_DELETE_DRINK_FROM_RESTAURANT = "delete from drink_from_restaurant where id = ?";
    public static final String QUERY_UPDATE_DRINK_FROM_RESTAURANT = "update drink_from_restaurant set id = ?, idDrink = ?, idRestaurant = ? where id = ?";


    // QUERIES DISH

    public static final String QUERY_GET_ALL_DISHES = "select * from dish";
    public static final String QUERY_GET_DISH_BY_ID = "select * from dish where id = ?";
    public static final String QUERY_ADD_DISH = "insert into dish values (?, ?, ?, ?, ?, ?)";
    public static final String QUERY_DELETE_DISH = "delete from dish where id = ?";
    public static final String QUERY_UPDATE_DISH = "update dish set id = ?, name = ?, isVegan = ?, weight = ?, price = ?, calories = ? where id = ?";




    // QUERIES DRINK

    public static final String QUERY_GET_ALL_DRINKS = "select * from drink";
    public static final String QUERY_GET_DRINK_BY_ID = "select * from drink where id = ?";
    public static final String QUERY_ADD_DRINK = "insert into drink values (?, ?, ?, ?, ?, ?, ?)";
    public static final String QUERY_DELETE_DRINK = "delete from drink where id = ?";
    public static final String QUERY_UPDATE_DRINK = "update drink set id = ?, name = ?, isVegan = ?, weight = ?, price = ?, flavour = ?, isAlcoholic = ? where id = ?";



    // QUERIES RESTAURANT

    public static final String QUERY_GET_ALL_RESTAURANTS = "select * from restaurant";
    public static final String QUERY_ADD_RESTAURANT = "insert into restaurant values (?, ?, ?)";
    public static final String QUERY_DELETE_RESTAURANT = "delete from restaurant where id = ?";
    public static final String QUERY_UPDATE_RESTAURANT = "update restaurant set id = ?, name = ?, idAddress = ? where id = ?";


    // QUERIES ORDER

    public static final String QUERY_GET_ALL_ORDERS = "select * from orders";
    public static final String QUERY_GET_ORDER_BY_ID = "select * from orders where id = ?";
    public static final String QUERY_ADD_ORDER = "insert into orders values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String QUERY_DELETE_ORDER = "delete from orders where id = ?";
    public static final String QUERY_UPDATE_ORDER = "update orders set id = ?, idClient = ?, idAddress = ?, idDeliveryDriver = ?, idDish = ?, idDrink = ?, orderDate = ?, orderStatus = ? where id = ?";

}
