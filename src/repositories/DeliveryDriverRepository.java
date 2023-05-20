package repositories;

import model.DeliveryDriver;

import java.util.ArrayList;
import java.util.List;

public class DeliveryDriverRepository {
    private List<DeliveryDriver> deliveryDrivers;
    public DeliveryDriverRepository(){
        this.deliveryDrivers = new ArrayList<>();
    }

    public List<DeliveryDriver> getDeliveryDrivers() {
        return deliveryDrivers;
    }

    public void addDeliveryDriver(DeliveryDriver deliveryDriver){
        deliveryDrivers.add(deliveryDriver);
    }

    public void removeDeliveryDriver(DeliveryDriver deliveryDriver){
        deliveryDrivers.remove(deliveryDriver);
    }
}
