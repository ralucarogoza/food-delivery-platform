package utils;

import model.Address;
import service.FileReaderWriterService;

public class AddressReaderWriter implements FileReaderWriterService<Address>{
    private static AddressReaderWriter instance = null;
    private AddressReaderWriter(){

    }
    public static AddressReaderWriter getInstance(){
        if(instance == null){
            instance = new AddressReaderWriter();
        }
        return instance;
    }
    @Override
    public Address objectFromCSVLine(String line) {
        String[] addressData = line.split(",");
        return new Address(addressData[0], addressData[1], Integer.parseInt(addressData[2]));
    }

    @Override
    public String CSVLineFromObject(Address object) {
        return object.getId() + ',' + object.getCity() + ',' + object.getStreet() + ',' + object.getNumber();
    }
}
