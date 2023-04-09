import model.*;
import service.impl.OrderServiceImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("Raluca", "Rogoza", "0763728291");
        Client c = new Client(p.getFirstName(), p.getLastName(), p.getPhoneNumber(), "raluca_rogoza@yahoo.com", "acasc343");
        Client c2 = new Client("Carina", "Nicola", "09435423", "carina_nicola@yahoo.com", "cari7548");
        DeliveryDriver dd = new DeliveryDriver("Popa", "Mihai", "0438473858", DeliveryMethod.CAR);
        System.out.println(p);
        System.out.println(c);
        System.out.println(dd);
        Address a = new Address("Bucharest", "Bd Carol", 33);
        Food f = new Food("Mici", false, 200, 15, 400, new ArrayList<>(){{add("carne"); add("condimente");}});
        List<Food> list_f = new ArrayList<>();
        list_f.add(f);
        Drink d = new Drink("Pepsi", true, 500, 6, 300, "lamaie", false);
        List<Drink> list_d = new ArrayList<>();
        list_d.add(d);
        Restaurant r = new Restaurant("MC", a, list_f, list_d);
        System.out.println(r);
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.set(Calendar.YEAR, 2000);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.MONTH, 4);
        Order order1 = new Order(c, a, r, dd, list_f, list_d);
        Order order2 = new Order(c2, a, r, dd, list_f, list_d);
        System.out.println(order1);
        System.out.println(order2);



        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.addClient(c);
        orderService.addClient(c2);
        orderService.showClients();
        orderService.showRestaurants();
        Restaurant restaurant2 = new Restaurant("MC", a, list_f, list_d);
        System.out.println(r.equals(restaurant2));
        orderService.addDrinkToRestaurant(d, r);
        orderService.addDrinkToRestaurant(d, r);
        orderService.showDrinksFromRestaurant(r);
        System.out.println(orderService.priceOfOrder(order2));

        orderService.addDishToRestaurant(new Food("pizza", false, 400, 40, 100, new ArrayList<>(){{add("carne"); add("condimente");}}), r);
        orderService.showFoodsFromRestaurant(r);

        System.out.println("-------------------------");
        System.out.println(order2.getOrderedDrinks());
        orderService.addDrinkToOrder(new Drink("Sprite", true, 500, 5, 400, "lamaie", false), order2);
        orderService.showDrinksFromOrder(order1);
        System.out.println("a doua");
        orderService.showDrinksFromOrder(order2);
        //System.out.println(order2.getOrderedDrinks());


        System.out.println(orderService.priceOfOrder(order2));


        orderService.removeDrinkFromOrder(order1, "Pepsi");
        orderService.showDrinksFromOrder(order1);
        System.out.println("a doua");
        orderService.showDrinksFromOrder(order2);

        //Scanner scanner = new Scanner(System.in);

    }
}

//cand adaug bautura/ dish la order sa pun nr order in antetul fct
// il aflu din pozitia pe care e in lista



// orders pointeaza catre aceeasi lista si de asta se modifica in ambele!!!