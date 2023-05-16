package model;

public class DeliveryDriver extends Person implements Comparable<DeliveryDriver>{
    private static int noDeliveryDrivers = 0;
    private int id;
    private DeliveryMethod deliveryMethod;
    private DeliveryDriverStatus deliveryDriverStatus;

    public DeliveryDriver(String firstName, String lastName, String phoneNumber, DeliveryMethod deliveryMethod, DeliveryDriverStatus deliveryDriverStatus) {
        super(firstName, lastName, phoneNumber);
        this.id = noDeliveryDrivers;
        this.deliveryMethod = deliveryMethod;
        this.deliveryDriverStatus = deliveryDriverStatus;
        noDeliveryDrivers++;
    }

    public int getId() {
        return id;
    }

    public DeliveryDriverStatus getDeliveryDriverStatus() {
        return deliveryDriverStatus;
    }

    public void setDeliveryDriverStatus(DeliveryDriverStatus deliveryDriverStatus) {
        this.deliveryDriverStatus = deliveryDriverStatus;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @Override
    public String toString() {
        return "\n" + id +
                "\n" + super.toString() +
                "\nStatus: " + deliveryDriverStatus +
                "\nDelivery method: " + deliveryMethod + "\n";
    }

    @Override
    public int compareTo(DeliveryDriver deliveryDriver) {
        int comp = this.getLastName().compareTo(deliveryDriver.getLastName());
        return (comp != 0 ? comp : this.getFirstName().compareTo(deliveryDriver.getFirstName()));
        //return this.getLastName().compareTo(deliveryDriver.getLastName());
    }
}
