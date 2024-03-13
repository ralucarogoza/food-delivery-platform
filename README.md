# Food Delivery Platform

I built a Java backend system for a food delivery platform, incorporating CRUD operations and database management functionalities. 

## Classes <br/>
* **Person** - abstract class <br/>
* **Client** - inherits Person <br/>
* **DeliveryDriver** - inherits Person<br/>
* **Product** - abstract class </br>
* **Drink** - inherits Product <br/>
* **Dish** - inherits Product <br/>
* **DishFromRestaurant** <br/>
* **DrinkFromRestaurant** <br/>
* **Address** <br/>
* **Restaurant** <br/>
* **Order** <br/>
* **FoodDeliveryService**
<br/>

## Actions <br/>

#### Client
* **void addClient(Client client)**
* **Map<String, Client> getClients()**
* **Optional<Client> findClientByEmail(String email)**
* **void deleteClient(Client client)**
* **void updateClient(Client oldClient, Client newClient)**

* **void addClientsFromCSVFile(String path)**


#### Delivery Driver
* **void addDeliveryDriver(DeliveryDriver deliveryDriver)**
* **List<DeliveryDriver> getDeliveryDrivers()**
* **List<DeliveryDriver> getAvailableDeliveryDrivers()**
* **void deleteDeliveryDriver(DeliveryDriver deliveryDriver)**
* **void updateDeliveryDriver(DeliveryDriver oldDeliveryDriver, DeliveryDriver newDeliveryDriver)**
* **void addDeliveryDriversFromCSVFile(String path)**


#### Address
* **void addAddress(Address address)**
* **List<Address> getAddresses()**
* **void deleteAddress(Address address)**
* **void updateAddress(Address oldAddress, Address newAddress)**
* **void addAddressesFromCSVFile(String path)**

#### Dish
* **void addDish(Dish dish)**
* **List<Dish> getDishes()**
* **void deleteDish(Dish dish)**
* **void updateDish(Dish oldDish, Dish newDish)**
* **void addDishesFromCSVFile(String path)**

#### Drink
* **List<Drink> getDrinks()**
* **void addDrink(Drink drink)**
* **void deleteDrink(Drink drink)**
* **void updateDrink(Drink oldDrink, Drink newDrink)**
* **void addDrinksFromCSVFile(String path)**

#### Drink From Restaurant
* **void addDrinkToRestaurant(DrinkFromRestaurant drinkFromRestaurant)**
* **List<DrinkFromRestaurant> getDrinksFromRestaurant()**

#### Dish From Restaurant
* **void addDishToRestaurant(DishFromRestaurant dishFromRestaurant)**
* **List<DishFromRestaurant> getDishesFromRestaurant()**

#### Restaurant
* **void addRestaurant(Restaurant restaurant)**
* **List<Restaurant> getRestaurants()**
* **void deleteRestaurant(Restaurant restaurant)**
* **void updateRestaurant(Restaurant oldRestaurant, Restaurant newRestaurant)**

#### Order
* **void addOrder**
* **List<Order> getOrders**
* **void deleteOrder(Order order)**
* **void updateOrder(Order oldOrder, Order newOrder)**
* **double priceOfOrder(Order order)**



















