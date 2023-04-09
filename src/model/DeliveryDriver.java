package model;

public class DeliveryDriver extends Person{
    private DeliveryMethod deliveryMethod;

    public DeliveryDriver(String firstName, String lastName, String phoneNumber, DeliveryMethod deliveryMethod) {
        super(firstName, lastName, phoneNumber);
        this.deliveryMethod = deliveryMethod;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @Override
    public String toString() {
        return "DeliveryDriver{" +
                super.toString() +
                ", deliveryMethod=" + deliveryMethod +
                '}';
    }
}
