package service.impl;

import config.DatabaseConfiguration;
import exceptions.*;
import model.*;
import repositories.*;
import service.AuditService;
import service.FoodDeliveryService;
import utils.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static java.util.Collections.sort;
import static validation.ClientValidation.validateEmail;
import static validation.PersonValidation.validatePhoneNumber;

public class FoodDeliveryServiceImpl implements FoodDeliveryService {
    private ClientRepository clientRepository;
    private DeliveryDriverRepository deliveryDriverRepository;
    private RestaurantRepository restaurantRepository = new RestaurantRepository();
    private AddressRepository addressRepository;
    private DrinkRepository drinkRepository;
    private DishRepository dishRepository;

    private DrinkToRestaurantRepository drinkToRestaurantRepository = new DrinkToRestaurantRepository();
    private DishToRestaurantRepository dishToRestaurantRepository = new DishToRestaurantRepository();
    private List<Order> orders;

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void setDeliveryDriverRepository(DeliveryDriverRepository deliveryDriverRepository) {
        this.deliveryDriverRepository = deliveryDriverRepository;
    }

    public void addOrder(Order order){
        if(orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
        AuditService.getInstance().write("addOrder: Order " + order.getId() + " was added at ");
        System.out.println("Order added with success!");
    }

    public void showOrders(){
        if(orders.isEmpty())
            System.out.println("There are no orders!");
        else{
            int i = 0;
            for(Order order: orders){
                i++;
                System.out.print(Integer.toString(i));
                System.out.print(". ");
                System.out.println(order);
            }
        }
    }

    public List<Order> getOrders(){
        try{
            if(orders == null)
                throw new NoOrderFoundException("There are no orders!");
        }
        catch(NoOrderFoundException orderFoundException){
            System.out.println(orderFoundException.getMessage());
        }
        return orders;
    }

    public void removeOrder(Order order){
        if(orders.contains(order)){
            orders.remove(order);
            AuditService.getInstance().write("removeOrder: Order " + order.getId() + " was removed at ");
            System.out.println("Order " + order.getId() + " removed with success!");
        }
        else{
            System.out.println("Doesn't exist this order!");
        }
    }

    public void updateStatusForOrder(int indexOrder, OrderStatus orderStatus){
        if(indexOrder - 1 > orders.size()){
            System.out.println("Doesn't exist an order with this index!");
        }
        else{
            OrderStatus oldStatus = orders.get(indexOrder - 1).getOrderStatus();
            orders.get(indexOrder - 1).setOrderStatus(orderStatus);
            AuditService.getInstance().write("updateStatusForOrder: Order " + indexOrder + " was updated at ");
            System.out.println("Order status for order number " + indexOrder + " was modified from " + oldStatus + " to " + orderStatus);
        }
    }

    @Override
    public double priceOfOrder(Order order) {
        double price = 0;
        price += order.getOrderedDish().getPrice();
        price += order.getOrderedDrink().getPrice();
        return price;
    }

    public void addClient(Client client) throws SQLException {
        boolean valid_client = true;
        try{
            if(!validateEmail(client.getEmail()))
                throw new InvalidEmailException("Invalid format for email!");
        }
        catch(InvalidEmailException emailException){
            valid_client = false;
            System.out.println(emailException.getMessage());
        }
        try{
            if(!validatePhoneNumber(client.getPhoneNumber()))
                throw new InvalidPhoneNumberException("Invalid format for phone number!");
        }
        catch (InvalidPhoneNumberException phoneNumberException){
            valid_client = false;
            System.out.println(phoneNumberException.getMessage());
        }
        if(valid_client){
            if(clientRepository.getClients() == null)
                clientRepository = new ClientRepository(clientRepository.getDatabaseConfiguration());
            //clientRe.put(client.getEmail(), client);
            clientRepository.addClient(client);
            AuditService.getInstance().write("addClient: Client " + client.getId() + " was added at ");
            System.out.println("Client added with success!");
        }
    }

    public void showClients(){
        if(clientRepository.getClients().isEmpty())
            System.out.println("There are no clients!\n");
        else{
            int i = 0;
            for(Map.Entry<String, Client> client:clientRepository.getClients().entrySet()){
                i ++;
                System.out.print(Integer.toString(i) + ". ");
                System.out.println(client.getValue());
            }
        }
    }


    public Map<String, Client> getClients(){
        try {
            if (clientRepository.getClients() == null)
                throw new NoClientFoundException("There are no clients!");
        }
        catch(NoClientFoundException clientFoundException){
            System.out.println(clientFoundException.getMessage());
        }
        return clientRepository.getClients();
    }
    @Override
    public Map<String, Client> getAllClients() throws SQLException {
        try {
            if (clientRepository.getAllClients() == null)
                throw new NoClientFoundException("There are no clients!");
        }
        catch(NoClientFoundException clientFoundException){
            System.out.println(clientFoundException.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientRepository.getAllClients();
    }


    public void removeClient(Client client) throws SQLException {
        String emailClient = client.getEmail();
        if(clientRepository.getClients().containsKey(emailClient)){
            //clients.remove(emailClient);
            clientRepository.deleteClient(client);
            AuditService.getInstance().write("removeClient: Client " + client.getId() + " was removed at ");
            System.out.println("Client " + client.getId() + " removed with success!");
        }
        else{
            System.out.println("Doesn't exist this client!");
        }
    }

    public void updateClient(Client oldClient, Client newClient) throws SQLException {
        boolean valid_client = true;
        try{
            if(!validateEmail(newClient.getEmail()))
                throw new InvalidEmailException("Invalid format for email!");
        }
        catch(InvalidEmailException emailException){
            valid_client = false;
            System.out.println(emailException.getMessage());
        }
        try{
            if(!validatePhoneNumber(newClient.getPhoneNumber()))
                throw new InvalidPhoneNumberException("Invalid format for phone number!");
        }
        catch (InvalidPhoneNumberException phoneNumberException){
            valid_client = false;
            System.out.println(phoneNumberException.getMessage());
        }
        if(valid_client){
            if(clientRepository.getClients() == null)
                clientRepository = new ClientRepository(clientRepository.getDatabaseConfiguration());
            //clientRe.put(client.getEmail(), client);
            clientRepository.updateClient(oldClient, newClient);
            AuditService.getInstance().write("updateClient: ");
            System.out.println("Client updated with success!");
        }
    }


    public void addDeliveryDriver(DeliveryDriver deliveryDriver){
        boolean validDeliveryDriver = true;
        try{
            if(!validatePhoneNumber(deliveryDriver.getPhoneNumber()))
                throw new InvalidPhoneNumberException("Invalid format for phone number!");
        }
        catch (InvalidPhoneNumberException phoneNumberException){
            validDeliveryDriver = false;
            System.out.println(phoneNumberException.getMessage());
        }
        if(validDeliveryDriver){
            if(deliveryDriverRepository == null)
                deliveryDriverRepository = new DeliveryDriverRepository(deliveryDriverRepository.getDatabaseConfiguration());
            deliveryDriverRepository.addDeliveryDriver(deliveryDriver);
            sort(deliveryDriverRepository.getDeliveryDrivers());
            AuditService.getInstance().write("addDeliveryDriver: DeliveryDriver " + deliveryDriver.getId() + " was added at ");
            System.out.println("Delivery driver added with success!");
        }
    }

    public void showDeliveryDrivers(){
        if(deliveryDriverRepository.getDeliveryDrivers().isEmpty())
            System.out.println("There are no delivery drivers!\n");
        else{
            int i = 0;
            for(DeliveryDriver deliveryDriver: deliveryDriverRepository.getDeliveryDrivers()){
                i++;
                System.out.print(Integer.toString(i) + ". ");
                System.out.println(deliveryDriver);
            }
        }
    }

    public List<DeliveryDriver> getDeliveryDrivers(){
        try {
            if (deliveryDriverRepository.getDeliveryDrivers() == null)
                throw new NoClientFoundException("There are no delivery drivers!");
        }
        catch(NoDeliveryDriverFoundException deliveryDriverFoundException){
            System.out.println(deliveryDriverFoundException.getMessage());
        }
        return deliveryDriverRepository.getDeliveryDrivers();
    }

    public void fireDeliveryDriver(DeliveryDriver deliveryDriver){
        if(deliveryDriverRepository.getDeliveryDrivers().contains(deliveryDriver)){
            deliveryDriverRepository.removeDeliveryDriver(deliveryDriver);
            AuditService.getInstance().write("fireDeliveryDriver: DeliveryDriver " + deliveryDriver.getId() + " was removed at ");
            System.out.println("Delivery driver with index" + deliveryDriver.getId() + " was fired with success!");
        }
        else
            System.out.println("Doesn't exist this delivery driver!");
    }

    public void updateDeliveryDriver(DeliveryDriver oldDeliveryDriver, DeliveryDriver newDeliveryDriver){
        deliveryDriverRepository.updateDeliveryDriver(oldDeliveryDriver, newDeliveryDriver);
        AuditService.getInstance().write("updateDeliveryDriver: ");
        System.out.println("DeliveryDriver updated with success!");
    }

    public void addRestaurant(Restaurant restaurant){
        if(restaurantRepository.getRestaurants() == null)
            restaurantRepository = new RestaurantRepository();
        restaurantRepository.addRestaurant(restaurant);
        AuditService.getInstance().write("addRestaurant: Restaurant " + restaurant.getId() + ": " + restaurant.getName() + " was added at ");
        System.out.println("Restaurant added with success!");
    }

    public void showRestaurants(){
        if(restaurantRepository.getRestaurants().isEmpty())
            System.out.println("There are no restaurants!\n");
        else{
            int i = 0;
            for(Restaurant restaurant: restaurantRepository.getRestaurants()){
                i++;
                System.out.print(Integer.toString(i) + ". ");
                System.out.println(restaurant);
            }
        }
    }

    public List<Restaurant> getRestaurants(){
        try {
            if(restaurantRepository.getRestaurants() == null)
                throw new NoRestaurantFoundException("There are no restaurants!");
        }
        catch(NoRestaurantFoundException restaurantFoundException){
            System.out.println(restaurantFoundException.getMessage());
        }
        return restaurantRepository.getRestaurants();
    }

    public void removeRestaurant(Restaurant restaurant){
        if(restaurantRepository.getRestaurants().contains(restaurant)){
            restaurantRepository.removeRestaurant(restaurant);
            AuditService.getInstance().write("removeRestaurant: Restaurant " + restaurant.getId() + " was removed at ");
            System.out.println("Restaurant " + restaurant.getId() + " removed with success!");
        }
        else{
            System.out.println("Doesn't exist this restaurant!");
        }
    }

    public void addDrinkToRestaurant(Drink drink, Restaurant restaurant){
        if(restaurantRepository.getRestaurants().contains(restaurant) && drinkRepository.getDrinks().contains(drink)){
            DrinkFromRestaurant drinkToAdd = new DrinkFromRestaurant(restaurant.getId(), drink.getId());
            drinkToRestaurantRepository.addDrinkToRestaurant(drinkToAdd);
            AuditService.getInstance().write("addDrinkToRestaurant: Drink " + drink.getId() + " was added in the menu of restaurant " + restaurant.getId() + " at ");
            System.out.println("Drink added with success to restaurant menu!");
        }
        else{
            System.out.println("Can't find this restaurant or this drink!");
        }
    }

    public Drink getDrinkById(int id){
        boolean found = false;
        Drink dr = null;
        for(Drink d: drinkRepository.getDrinks()){
            if(d.getId() == id){
                found = true;
                dr = d;
            }
        }
        if(!found){
            System.out.println("This drink doesn't exist!");
        }
        return dr;
    }

    public void showDrinksFromRestaurant(Restaurant restaurant){
        List<DrinkFromRestaurant> drinksR = drinkToRestaurantRepository.getDrinksFromRestaurants();
        List<Drink> drinks = new ArrayList<>();
        for(DrinkFromRestaurant d: drinksR){
            if(d.getIdRestaurant() == restaurant.getId()){
                Drink dr = getDrinkById(d.getIdDrink());
                drinks.add(dr);
            }
        }
        if(drinks.isEmpty())
            System.out.println("Drink menu is empty!\n");
        else{
            for(Drink drink: drinks){
                System.out.println(drink);
            }
        }
    }

    public void addDishToRestaurant(Dish dish, Restaurant restaurant){
        if(restaurantRepository.getRestaurants().contains(restaurant) && dishRepository.getDishes().contains(dish)){
            DishFromRestaurant dishToAdd = new DishFromRestaurant(restaurant.getId(), dish.getId());
            dishToRestaurantRepository.addDishToRestaurant(dishToAdd);
            AuditService.getInstance().write("addDishToRestaurant: Dish " + dish.getId() + " was added in the menu of restaurant " + restaurant.getId() + " at ");
            System.out.println("Dish added with success to restaurant menu!");
        }
        else{
            System.out.println("Can't find this restaurant or this dish!");
        }
    }

    public Dish getDishById(int id){
        boolean found = false;
        Dish di = null;
        for(Dish d: dishRepository.getDishes()){
            if(d.getId() == id){
                found = true;
                di = d;
            }
        }
        if(!found){
            System.out.println("This dish doesn't exist!");
        }
        return di;
    }



    public void showDishesFromRestaurant(Restaurant restaurant){
        List<DishFromRestaurant> dishesR = dishToRestaurantRepository.getDishesFromRestaurants();
        List<Dish> dishes = new ArrayList<>();
        for(DishFromRestaurant d: dishesR){
            if(d.getIdRestaurant() == restaurant.getId()){
                Dish dr = getDishById(d.getIdDish());
                dishes.add(dr);
            }
        }
        if(dishes.isEmpty())
            System.out.println("Dish menu is empty!\n");
        else{
            for(Dish dish: dishes){
                System.out.println(dish);
            }
        }
    }

    /*public void addDrinkToOrder(Drink drink, Order order){
        Integer idRestaurant = order.getRestaurant().getId();
        for(DrinkFromRestaurant drinkFromRestaurant: drinkToRestaurantRepository.getDrinksFromRestaurants()){
            if(idRestaurant == drinkFromRestaurant.getIdRestaurant() && drink.getId() == drinkFromRestaurant.getIdDrink()){

            }
        }

        List <Drink> drinksFromRestaurant = order.getRestaurant().getDrinks();
        try {
            if(!drinksFromRestaurant.contains(drink))
                throw new DrinkIsNotInTheMenuException("This drink is not in the restaurant's menu.");
            else{
                order.getOrderedDrinks().add(drink);
                System.out.println("Drink added with success to your order!");
            }
        }
        catch(DrinkIsNotInTheMenuException drinkIsNotInTheMenuException){
            System.out.println(drinkIsNotInTheMenuException.getMessage());
        }
    }

    public void addDishToOrder(Dish dish, Order order){
        List <Dish> dishesFromRestaurant = order.getRestaurant().getFoods();
        try {
            if(!dishesFromRestaurant.contains(dish))
                throw new DishIsNotInTheMenuException("This dish is not in the restaurant's menu.");
            else{
                order.getOrderedFoods().add(dish);
                System.out.println("Dish added with success to your order!");
            }
        }
        catch(DishIsNotInTheMenuException dishIsNotInTheMenuException){
            System.out.println(dishIsNotInTheMenuException.getMessage());
        }
    }

    public void showDrinksFromOrder(Order order){
        if(order.getOrderedDrinks().isEmpty())
            System.out.println("There are no drinks in your order!\n");
        else{
            for(Drink drink: order.getOrderedDrinks()){
                System.out.println(drink);
            }
        }
    }

    public void showFoodsFromOrder(Order order){
        if(order.getOrderedFoods().isEmpty())
            System.out.println("There are no dishes in your order!\n");
        else{
            for(Dish dish : order.getOrderedFoods()){
                System.out.println(dish);
            }
        }
    }

    public void removeDrinkFromOrder(Order order, String drinkName){
        boolean found = false;
        for(Drink drink: order.getOrderedDrinks()){
            if(drink.getName() == drinkName){
                order.getOrderedDrinks().remove(drink);
                found = true;
                break;
            }
        }
        if(found)
            System.out.println("Drink removed from your order with success!");
        else
            System.out.println("This drink isn't in your order!");
    }

    public void removeDishFromOrder(Order order, String dishName){
        boolean found = false;
        for(Dish dish : order.getOrderedFoods()){
            if(dish.getName() == dishName){
                order.getOrderedFoods().remove(dish);
                found = true;
                break;
            }
        }
        if(found)
            System.out.println("Dish removed from your order with success!");
        else
            System.out.println("This dish isn't in your order!");
    }*/

    public Client findClient(String email){
        Client clientWanted = null;
        for(Map.Entry<String, Client> client: clientRepository.getClients().entrySet()){
            if(client.getKey() == email){
                clientWanted = client.getValue();
            }
        }
        return clientWanted;
    }

    public List<DeliveryDriver> getAvailableDeliveryDrivers(){
        List<DeliveryDriver> availableDeliveryDrivers = new ArrayList<>();
        for(DeliveryDriver deliveryDriver: this.deliveryDriverRepository.getDeliveryDrivers()){
            if(deliveryDriver.getDeliveryDriverStatus() == DeliveryDriverStatus.AVAILABLE)
                availableDeliveryDrivers.add(deliveryDriver);
        }
        return availableDeliveryDrivers;
    }



    public void addAddress(Address address){
        if(addressRepository == null){
            addressRepository = new AddressRepository(addressRepository.getDatabaseConfiguration());
        }
        addressRepository.addAddress(address);
        AuditService.getInstance().write("addAddress: Address " + address.getId() + " was added at ");
        System.out.println("Address added with success!");
    }

    public void addDish(Dish dish){
        if(dishRepository == null){
            dishRepository = new DishRepository(dishRepository.getDatabaseConfiguration());
        }
        dishRepository.addDish(dish);
        AuditService.getInstance().write("addDish: Dish " + dish.getId() + " was added at ");
        System.out.println("Dish added with success!");
    }


    public void addDrink(Drink drink){
        if(drinkRepository == null){
            drinkRepository = new DrinkRepository(dishRepository.getDatabaseConfiguration());
        }
        drinkRepository.addDrink(drink);
        AuditService.getInstance().write("addDrink: Drink " + drink.getId() + " was added at ");
        System.out.println("Drink added with success!");
    }


    public List<Address> getAddresses(){
        try{
            if(addressRepository == null)
                throw new Exception("There are no addresses!");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return addressRepository.getAddresses();
    }

    public List<Dish> getDishes(){
        try{
            if(dishRepository == null)
                throw new Exception("There are no dishes!");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return dishRepository.getDishes();
    }

    public List<Drink> getDrinks(){
        try{
            if(drinkRepository == null)
                throw new Exception("There are no drinks!");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return drinkRepository.getDrinks();
    }


    public void removeDish(Dish dish){
        if(dishRepository.getDishes().contains(dish)){
            dishRepository.removeDish(dish);
            AuditService.getInstance().write("removeDish: Dish " + dish.getId() + " was removed at ");
            System.out.println("Dish " + dish.getId() + " removed with success!");
        }
        else{
            System.out.println("Doesn't exist this dish!");
        }
    }

    public void removeDrink(Drink drink){
        if(drinkRepository.getDrinks().contains(drink)){
            drinkRepository.removeDrink(drink);
            AuditService.getInstance().write("removeDrink: Drink " + drink.getId() + " was removed at ");
            System.out.println("Drink " + drink.getId() + " removed with success!");
        }
        else{
            System.out.println("Doesn't exist this drink!");
        }
    }


    public void deleteAddress(Address address){
        System.out.println(address);
        System.out.println(getAddresses());
        if(addressRepository.getAddresses().contains(address)){
            addressRepository.deleteAddress(address);
            AuditService.getInstance().write("removeAddress: Address " + address.getId() + " was removed at ");
            System.out.println("Address " + address.getId() + " removed with success!");
        }
        else{
            System.out.println("Doesn't exist this address!");
        }
    }

    public void updateAddress(Address oldAddress, Address newAddress){
        addressRepository.updateAddress(oldAddress, newAddress);
        AuditService.getInstance().write("updateAddress: ");
        System.out.println("Address updated with success!");
    }

    public void updateDish(Dish oldDish, Dish newDish){
        dishRepository.updateDish(oldDish, newDish);
        AuditService.getInstance().write("updateDish: ");
        System.out.println("Dish updated with success!");
    }

    public void updateDrink(Drink oldDrink, Drink newDrink){
        drinkRepository.updateDrink(oldDrink, newDrink);
        AuditService.getInstance().write("updateDrink: ");
        System.out.println("Drink updated with success!");
    }

    @Override
    public void addClientsFromCSVFile(String path) throws IOException, SQLException {
        ClientReaderWriter clientReaderWriter = ClientReaderWriter.getInstance();
        List<Client> clientsFromCSVFile = new ArrayList<>(clientReaderWriter.read(path));
        for(Client client: clientsFromCSVFile){
            addClient(client);
        }
    }

    @Override
    public void addDeliveryDriversFromCSVFile(String path) throws IOException {
        DeliveryDriverReaderWriter deliveryDriverReaderWriter = DeliveryDriverReaderWriter.getInstance();
        List<DeliveryDriver> deliveryDriversFromCSVFile = new ArrayList<>(deliveryDriverReaderWriter.read(path));
        for(DeliveryDriver deliveryDriver: deliveryDriversFromCSVFile){
            addDeliveryDriver(deliveryDriver);
        }
    }

    @Override
    public void addAddressesFromCSVFile(String path) throws IOException {
        AddressReaderWriter addressReaderWriter = AddressReaderWriter.getInstance();
        List<Address> addressesFromCSVFile = new ArrayList<>(addressReaderWriter.read(path));
        for(Address address: addressesFromCSVFile){
            addAddress(address);
        }
    }

    @Override
    public void addDishesFromCSVFile(String path) throws IOException {
        DishReaderWriter dishReaderWriter = DishReaderWriter.getInstance();
        List<Dish> dishesFromCSVFile = new ArrayList<>(dishReaderWriter.read(path));
        for(Dish dish: dishesFromCSVFile){
            addDish(dish);
        }
    }

    @Override
    public void addDrinksFromCSVFile(String path) throws IOException {
        DrinkReaderWriter drinkReaderWriter = DrinkReaderWriter.getInstance();
        List<Drink> drinksFromCSVFile = new ArrayList<>(drinkReaderWriter.read(path));
        for(Drink drink: drinksFromCSVFile){
            addDrink(drink);
        }
    }
}
