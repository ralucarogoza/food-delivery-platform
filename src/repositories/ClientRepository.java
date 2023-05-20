package repositories;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ClientRepository {
    private Map<String, Client> clients;

    public ClientRepository() {
        this.clients = new TreeMap<>();
    }

    public Map<String, Client> getClients() {
        return this.clients;
    }

    public void addClient(Client client){
        clients.put(client.getEmail(), client);
    }
    public void removeClient(Client client){
        clients.remove(client.getEmail());
    }
}
