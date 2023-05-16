package utils;

import model.Client;
import service.FileReaderWriterService;

public class ClientReaderWriter implements FileReaderWriterService<Client> {
    private static ClientReaderWriter instance = null;
    private ClientReaderWriter(){

    }
    public static ClientReaderWriter getInstance(){
        if(instance == null){
            instance = new ClientReaderWriter();
        }
        return instance;
    }
    @Override
    public Client objectFromCSVLine(String line) {
        String[] clientData = line.split(",");
        return new Client(clientData[0], clientData[1], clientData[2], clientData[3]);
    }

    @Override
    public String CSVLineFromObject(Client client) {
        return client.getId() + ',' + client.getFirstName() + ',' + client.getLastName() + ',' + client.getPhoneNumber() + ',' + client.getEmail();
    }
}
