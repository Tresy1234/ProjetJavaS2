package ism.com.services;

import java.util.List;

import ism.com.entities.Client;
import ism.com.repository.IRepository;

public class ClientService {
    private final IRepository<Client> clientRepository;

    public ClientService(IRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Client client) {
        clientRepository.add(client);
    }

    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public Client findClientById(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> findClientsWithAccounts(boolean hasAccount) {
        return clientRepository.findAll().stream()
                .filter(client -> (hasAccount && client.getUser() != null) || (!hasAccount && client.getUser() == null))
                .toList();
    }
    public Client findClientByPhone(String phone) {
        return clientRepository.findByPhone(phone); // Appelle la méthode du repository
    }
    
}
