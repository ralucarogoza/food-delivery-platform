package repositories;

import model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressRepository {
    private List<Address> addresses;
    public AddressRepository(){
        this.addresses = new ArrayList<>();
    }
    public List<Address> getAddresses(){
        return this.addresses;
    }

    public void addAddress(Address address){
        addresses.add(address);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
    }
}
