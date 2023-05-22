package service.impl;

import exceptions.*;
import model.*;
import repositories.*;
import service.AuditService;
import service.FoodDeliveryService;
import utils.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static java.util.Collections.sort;
import static validation.ClientValidation.validateEmail;
import static validation.PersonValidation.validatePhoneNumber;

public class FoodDeliveryServiceImpl implements FoodDeliveryService {
    private ClientRepository clientRepository;
    private DeliveryDriverRepository deliveryDriverRepository;
    private RestaurantRepository restaurantRepository;
    private AddressRepository addressRepository;
    private DrinkRepository drinkRepository;
    private DishRepository dishRepository;

    private DrinkFromRestaurantRepository drinkFromRestaurantRepository;
    private DishFromRestaurantRepository dishFromRestaurantRepository;
    private OrderRepository orderRepository;

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void setDeliveryDriverRepository(DeliveryDriverRepository deliveryDriverRepository) {
        this.deliveryDriverRepository = deliveryDriverRepository;
    }

    public void setDrinkRepository(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public void setDishRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void setDrinkFromRestaurantRepository(DrinkFromRestaurantRepository drinkFromRestaurantRepository) {
        this.drinkFromRestaurantRepository = drinkFromRestaurantRepository;
    }

    public void setDishFromRestaurantRepository(DishFromRestaurantRepository dishFromRestaurantRepository) {
        this.dishFromRestaurantRepository = dishFromRestaurantRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(Order order){
        if(orderRepository == null){
            orderRepository = new OrderRepository(orderRepository.getDatabaseConfiguration());
        }
        orderRepository.addOrder(order);
        AuditService.getInstance().write("addOrder: Order " + order.getId() + " was added at ");
        System.out.println("Order added with success!");
    }



    @Override
    public List<Order> getOrders(){
        try{
            if(orderRepository.getOrders() == null)
                throw new NoOrderFoundException("There are no orders!");
        }
        catch(NoOrderFoundException orderFoundException){
            System.out.println(orderFoundException.getMessage());
        }
        return orderRepository.getOrders();
    }

    @Override
    public void deleteOrder(Order order){
        boolean found = false;
        try{
            for(Order o: getOrders()){
                if(o.getId() == order.getId()){
                    found = true;
                }
            }
            if(!found){
                throw new OrderNotFoundException("Order with id " + order.getId() + " doesn't exist!");
            }
            orderRepository.deleteOrder(order);
            AuditService.getInstance().write("deleteOrder: Order " + order.getId() + " was removed at ");
            System.out.println("Order " + order.getId() + " removed with success!");
        }
        catch (OrderNotFoundException orderNotFoundException){
            System.out.println(orderNotFoundException.getMessage());
        }
    }

    @Override
    public void updateOrder(Order oldOrder, Order newOrder){
        boolean found = false;
        try{
            for(Order o: getOrders()){
                if(o.getId() == oldOrder.getId()){
                    found = true;
                }
            }
            if(!found){
                throw new OrderNotFoundException("Order with id " + oldOrder.getId() + " doesn't exist!");
            }
            orderRepository.updateOrder(oldOrder, newOrder);
            AuditService.getInstance().write("updateOrder: at ");
            System.out.println("Order " + oldOrder.getId() + " updated with success!");
        }
        catch (OrderNotFoundException orderNotFoundException){
            System.out.println(orderNotFoundException.getMessage());
        }
    }

    @Override
    public double priceOfOrder(Order order) {
        double price = 0;

        for(Dish dish: getDishes()){
            if(dish.getId() == order.getOrderedDish().getIdDish())
                price += dish.getPrice();
        }
        for(Drink drink: getDrinks()){
            if(drink.getId() == order.getOrderedDrink().getIdDrink())
                price += drink.getPrice();
        }
        return price;
    }


    // ADDRESS

    @Override
    public void addAddress(Address address){
        if(addressRepository == null){
            addressRepository = new AddressRepository(addressRepository.getDatabaseConfiguration());
        }
        addressRepository.addAddress(address);
        AuditService.getInstance().write("addAddress: Address " + address.getId() + " was added at ");
        System.out.println("Address added with success!");
    }

    @Override
    public List<Address> getAddresses(){
        try{
            if(addressRepository.getAddresses() == null)
                throw new Exception("There are no addresses!");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return addressRepository.getAddresses();
    }

    @Override
    public void deleteAddress(Address address){
        boolean found = false;
        try{
            for(Address a: getAddresses()){
                if(a.getId() == address.getId()){
                    found = true;
                }
            }
            if(!found){
                throw new AddressNotFoundException("Address with id " + address.getId() + " doesn't exist!");
            }
            addressRepository.deleteAddress(address);
            AuditService.getInstance().write("deleteAddress: Address " + address.getId() + " was removed at ");
            System.out.println("Address " + address.getId() + " removed with success!");
        }
        catch (AddressNotFoundException addressNotFoundException){
            System.out.println(addressNotFoundException.getMessage());
        }
    }

    @Override
    public void updateAddress(Address oldAddress, Address newAddress){
        boolean found = false;
        try{
            for(Address a: getAddresses()) {
                if (a.getId() == oldAddress.getId()) {
                    found = true;
                }
            }
            if(!found){
                throw new AddressNotFoundException("Address with id " + oldAddress.getId() + " doesn't exist!");
            }
            addressRepository.updateAddress(oldAddress, newAddress);
            AuditService.getInstance().write("updateAddress: ");
            System.out.println("Address updated with success!");
        }
        catch (AddressNotFoundException addressNotFoundException){
            System.out.println(addressNotFoundException.getMessage());
        }
    }



    // CLIENT

    @Override
    public void addClient(Client client) {
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
            clientRepository.addClient(client);
            AuditService.getInstance().write("addClient: Client " + client.getId() + " was added at ");
            System.out.println("Client added with success!");
        }
    }

    public Optional<Client> findClientByEmail(String email){
        return clientRepository.findClientByEmail(email);
    }

    @Override
    public Map<String, Client> getClients() {
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
    public void removeClient(Client client){
        boolean found = false;
        try{
            for(Client c: getClients().values()) {
                if (c.getId() == client.getId()) {
                    found = true;
                }
            }
            if(!found){
                throw new ClientNotFoundException("Client with id " + client.getId() + " doesn't exist!");
            }
            clientRepository.deleteClient(client);
            AuditService.getInstance().write("removeClient: Client " + client.getId() + " was removed at ");
            System.out.println("Client " + client.getId() + " removed with success!");
        }
        catch(ClientNotFoundException clientNotFoundException){
            System.out.println(clientNotFoundException.getMessage());
        }
    }

    @Override
    public void updateClient(Client oldClient, Client newClient) throws SQLException {
        boolean valid_client = true;
        boolean found = false;
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
            for(Client c: getClients().values()) {
                if (c.getId() == oldClient.getId()) {
                    found = true;
                }
            }
            if(!found){
                throw new ClientNotFoundException("Client with id " + oldClient.getId() + " doesn't exist!");
            }
        }
        catch (InvalidPhoneNumberException phoneNumberException){
            valid_client = false;
            System.out.println(phoneNumberException.getMessage());
        }
        catch(ClientNotFoundException clientNotFoundException) {
            System.out.println(clientNotFoundException.getMessage());
        }
        if(found && valid_client) {
            clientRepository.updateClient(oldClient, newClient);
            AuditService.getInstance().write("updateClient: ");
            System.out.println("Client updated with success!");
        }
    }

    // DELIVERY DRIVER

    @Override
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

    @Override
    public List<DeliveryDriver> getDeliveryDrivers(){
        try {
            if (deliveryDriverRepository.getDeliveryDrivers() == null)
                throw new NoDeliveryDriverFoundException("There are no delivery drivers!");
        }
        catch(NoDeliveryDriverFoundException deliveryDriverFoundException){
            System.out.println(deliveryDriverFoundException.getMessage());
        }
        return deliveryDriverRepository.getDeliveryDrivers();
    }

    @Override
    public List<DeliveryDriver> getAvailableDeliveryDrivers(){
        try {
            if (deliveryDriverRepository.getAvailableDeliveryDrivers() == null)
                throw new NoAvailableDeliveryDriversException("There are no available delivery drivers!");
        }
        catch(NoAvailableDeliveryDriversException noAvailableDeliveryDriversException){
            System.out.println(noAvailableDeliveryDriversException.getMessage());
        }
        return deliveryDriverRepository.getAvailableDeliveryDrivers();
    }

    @Override
    public void deleteDeliveryDriver(DeliveryDriver deliveryDriver){
        boolean found = false;
        try{
            for(DeliveryDriver d: getDeliveryDrivers()){
                if(d.getId() == deliveryDriver.getId()){
                    found = true;
                }
                if(!found){
                    throw new DeliveryDriverNotFoundException("DeliveryDriver with id " + deliveryDriver.getId() + " doesn't exist!");
                }
                deliveryDriverRepository.deleteDeliveryDriver(deliveryDriver);
                AuditService.getInstance().write("fireDeliveryDriver: DeliveryDriver " + deliveryDriver.getId() + " was removed at ");
                System.out.println("Delivery driver with id " + deliveryDriver.getId() + " was fired with success!");
            }
        }
        catch (DeliveryDriverNotFoundException deliveryDriverNotFoundException){
            System.out.println(deliveryDriverNotFoundException.getMessage());
        }
    }

    @Override
    public void updateDeliveryDriver(DeliveryDriver oldDeliveryDriver, DeliveryDriver newDeliveryDriver){
        boolean validDeliveryDriver = true;
        boolean found = false;
        try{
            if(!validatePhoneNumber(newDeliveryDriver.getPhoneNumber()))
                throw new InvalidPhoneNumberException("Invalid format for phone number!");
            for(DeliveryDriver d: getDeliveryDrivers()) {
                if (d.getId() == oldDeliveryDriver.getId()) {
                    found = true;
                }
            }
            if(!found){
                throw new DeliveryDriverNotFoundException("DeliveryDriver with id " + oldDeliveryDriver.getId() + " doesn't exist!");
            }
        }
        catch (InvalidPhoneNumberException phoneNumberException){
            validDeliveryDriver = false;
            System.out.println(phoneNumberException.getMessage());
        }
        catch(DeliveryDriverNotFoundException deliveryDriverNotFoundException) {
            System.out.println(deliveryDriverNotFoundException.getMessage());
        }
        if(found && validDeliveryDriver) {
            deliveryDriverRepository.updateDeliveryDriver(oldDeliveryDriver, newDeliveryDriver);
            AuditService.getInstance().write("updateDeliveryDriver: ");
            System.out.println("DeliveryDriver updated with success!");        }
    }



    // DRINK


    public Optional<Drink> getDrinkById(int id){
        return drinkRepository.getDrinkById(id);
    }

    @Override
    public List<Drink> getDrinks(){
        try{
            if(drinkRepository == null)
                throw new NoDrinkFoundException("There are no drinks!");
        }
        catch(NoDrinkFoundException noDrinkFoundException){
            System.out.println(noDrinkFoundException.getMessage());
        }
        return drinkRepository.getDrinks();
    }

    @Override
    public void addDrink(Drink drink){
        if(drinkRepository == null){
            drinkRepository = new DrinkRepository(dishRepository.getDatabaseConfiguration());
        }
        drinkRepository.addDrink(drink);
        AuditService.getInstance().write("addDrink: Drink " + drink.getId() + " was added at ");
        System.out.println("Drink added with success!");
    }


    @Override
    public void deleteDrink(Drink drink){
        boolean found = false;
        try{
            for(Drink d: getDrinks()){
                if(d.getId() == drink.getId()){
                    found = true;
                }
            }
            if(!found){
                throw new DrinkNotFoundException("Drink with id " + drink.getId() + " doesn't exist!");
            }
            drinkRepository.deleteDrink(drink);
            AuditService.getInstance().write("removeDrink: Drink " + drink.getId() + " was removed at ");
            System.out.println("Drink " + drink.getId() + " removed with success!");
        }
        catch (DrinkNotFoundException drinkNotFoundException){
            System.out.println(drinkNotFoundException.getMessage());
        }
    }


    @Override
    public void updateDrink(Drink oldDrink, Drink newDrink){
        boolean found = false;
        try{
            for(Drink d: getDrinks()) {
                if (d.getId() == oldDrink.getId()) {
                    found = true;
                }
            }
            if(!found){
                throw new DrinkNotFoundException("Drink with id " + oldDrink.getId() + " doesn't exist!");
            }
            drinkRepository.updateDrink(oldDrink, newDrink);
            AuditService.getInstance().write("updateDrink: ");
            System.out.println("Drink updated with success!");
        }
        catch (DrinkNotFoundException drinkNotFoundException){
            System.out.println(drinkNotFoundException.getMessage());
        }
    }








    // DISH
    @Override
    public void addDish(Dish dish){
        if(dishRepository == null){
            dishRepository = new DishRepository(dishRepository.getDatabaseConfiguration());
        }
        dishRepository.addDish(dish);
        AuditService.getInstance().write("addDish: Dish " + dish.getId() + " was added at ");
        System.out.println("Dish added with success!");
    }


    @Override
    public List<Dish> getDishes(){
        try{
            if(dishRepository == null)
                throw new NoDishFoundException("There are no dishes!");
        }
        catch(NoDishFoundException noDishFoundException){
            System.out.println(noDishFoundException.getMessage());
        }
        return dishRepository.getDishes();
    }


    public Optional<Dish> getDishById(int id){
        return dishRepository.getDishById(id);
    }

    @Override
    public void deleteDish(Dish dish){
        boolean found = false;
        try{
            for(Dish d: getDishes()){
                if(d.getId() == dish.getId()){
                    found = true;
                }
            }
            if(!found){
                throw new DishNotFoundException("Dish with id " + dish.getId() + " doesn't exist!");
            }
            dishRepository.removeDish(dish);
            AuditService.getInstance().write("removeDish: Dish " + dish.getId() + " was removed at ");
            System.out.println("Dish " + dish.getId() + " removed with success!");
        }
        catch (DishNotFoundException dishNotFoundException){
            System.out.println(dishNotFoundException.getMessage());
        }
    }


    @Override
    public void updateDish(Dish oldDish, Dish newDish){
        boolean found = false;
        try{
            for(Dish d: getDishes()) {
                if (d.getId() == oldDish.getId()) {
                    found = true;
                }
            }
            if(!found){
                throw new DishNotFoundException("Dish with id " + oldDish.getId() + " doesn't exist!");
            }
            dishRepository.updateDish(oldDish, newDish);
            AuditService.getInstance().write("updateDish: ");
            System.out.println("Dish updated with success!");
        }
        catch (DishNotFoundException dishNotFoundException){
            System.out.println(dishNotFoundException.getMessage());
        }
    }




    // RESTAURANT



    @Override
    public void addRestaurant(Restaurant restaurant){
        if(restaurantRepository.getRestaurants() == null)
            restaurantRepository = new RestaurantRepository(restaurantRepository.getDatabaseConfiguration());
        restaurantRepository.addRestaurant(restaurant);
        AuditService.getInstance().write("addRestaurant: Restaurant " + restaurant.getId() + ": " + restaurant.getName() + " was added at ");
        System.out.println("Restaurant added with success!");
    }

    @Override
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

    @Override
    public void deleteRestaurant(Restaurant restaurant){
        boolean found = false;
        try{
            for(Restaurant r: getRestaurants()){
                if(r.getId() == restaurant.getId()){
                    found = true;
                }
            }
            if(!found){
                throw new RestaurantNotFoundException("Restaurant with id " + restaurant.getId() + " doesn't exist!");
            }
            restaurantRepository.deleteRestaurant(restaurant);
            AuditService.getInstance().write("removeRestaurant: Restaurant " + restaurant.getId() + " was removed at ");
            System.out.println("Restaurant " + restaurant.getId() + " removed with success!");
        }
        catch (RestaurantNotFoundException restaurantNotFoundException){
            System.out.println(restaurantNotFoundException.getMessage());
        }
    }

    @Override
    public void updateRestaurant(Restaurant oldRestaurant, Restaurant newRestaurant){
        boolean found = false;
        try{
            for(Restaurant r: getRestaurants()) {
                if (r.getId() == oldRestaurant.getId()) {
                    found = true;
                }
            }
            if(!found){
                throw new RestaurantNotFoundException("Restaurant with id " + oldRestaurant.getId() + " doesn't exist!");
            }
            restaurantRepository.updateRestaurant(oldRestaurant, newRestaurant);
            AuditService.getInstance().write("updateRestaurant: ");
            System.out.println("Restaurant updated with success!");
        }
        catch (RestaurantNotFoundException restaurantNotFoundException){
            System.out.println(restaurantNotFoundException.getMessage());
        }
    }




    @Override
    public void addDrinkToRestaurant(DrinkFromRestaurant drinkFromRestaurant){
        if(drinkFromRestaurantRepository == null)
            drinkFromRestaurantRepository = new DrinkFromRestaurantRepository(drinkFromRestaurantRepository.getDatabaseConfiguration());
        drinkFromRestaurantRepository.addDrinkToRestaurant(drinkFromRestaurant);
        AuditService.getInstance().write("addDrinkToRestaurant: at ");
        System.out.println("Drink added with success to restaurant menu!");
    }





    public List<DrinkFromRestaurant> getDrinksFromRestaurant(){
        try {
            if(drinkFromRestaurantRepository.getDrinksFromRestaurants() == null)
                throw new NoDrinkFromRestaurantFoundException("There are no menus for drinks!");
        }
        catch(NoDrinkFromRestaurantFoundException noDrinkFromRestaurantFoundException){
            System.out.println(noDrinkFromRestaurantFoundException.getMessage());
        }
        return drinkFromRestaurantRepository.getDrinksFromRestaurants();
    }


    public void addDishToRestaurant(DishFromRestaurant dishFromRestaurant){
        if(dishFromRestaurantRepository == null){
            dishFromRestaurantRepository = new DishFromRestaurantRepository(dishFromRestaurantRepository.getDatabaseConfiguration());
        }
        dishFromRestaurantRepository.addDishToRestaurant(dishFromRestaurant);
        AuditService.getInstance().write("addDishToRestaurant: at ");
        System.out.println("Dish added with success to restaurant menu!");
    }


    public List<DishFromRestaurant> getDishesFromRestaurant(){
        try {
            if(dishFromRestaurantRepository.getDishesFromRestaurants() == null)
                throw new NoDishFromRestaurantFoundException("There are no menus for dishes!");
        }
        catch(NoDishFromRestaurantFoundException noDishFromRestaurantFoundException){
            System.out.println(noDishFromRestaurantFoundException.getMessage());
        }
        return dishFromRestaurantRepository.getDishesFromRestaurants();
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














    // CSV


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
