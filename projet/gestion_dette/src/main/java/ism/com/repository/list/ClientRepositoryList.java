package ism.com.repository.list;

import ism.com.entities.Client;

public class ClientRepositoryList extends BaseRepositoryList<Client> {
  @Override
    public Client findById(int id) {
        return storage.stream()
                .filter(client -> client.equals(id))
                .findFirst()
                .orElse(null);
    }  
}
