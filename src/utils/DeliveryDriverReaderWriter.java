package utils;

import model.DeliveryDriver;
import model.DeliveryDriverStatus;
import model.DeliveryMethod;
import service.FileReaderWriterService;

public class DeliveryDriverReaderWriter implements FileReaderWriterService<DeliveryDriver> {
    private static DeliveryDriverReaderWriter instance = null;
    private DeliveryDriverReaderWriter(){

    }
    public static DeliveryDriverReaderWriter getInstance(){
        if(instance == null){
            instance = new DeliveryDriverReaderWriter();
        }
        return instance;
    }
    @Override
    public DeliveryDriver objectFromCSVLine(String line) {
        String[] deliveryDriverData = line.split(",");
        return new DeliveryDriver(deliveryDriverData[0], deliveryDriverData[1], deliveryDriverData[2],
                DeliveryMethod.valueOf(deliveryDriverData[3]), DeliveryDriverStatus.valueOf(deliveryDriverData[4]));
    }

    @Override
    public String CSVLineFromObject(DeliveryDriver object) {
        return object.getId() + ',' + object.getFirstName() + ',' + object.getLastName() + ',' + object.getPhoneNumber() + ',' +
                object.getDeliveryDriverStatus() + ',' + object.getDeliveryMethod();
    }
}
