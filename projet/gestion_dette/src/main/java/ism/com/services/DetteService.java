package ism.com.services;

import java.util.List;

import ism.com.entities.Client;
import ism.com.entities.Dette;
import ism.com.repository.IRepository;

public class DetteService {
    private final IRepository<Dette> detteRepository;

    public DetteService(IRepository<Dette> detteRepository) {
        this.detteRepository = detteRepository;
    }

    public void createDette(Client client, Dette dette) {
        client.getDettes().add(dette);
        detteRepository.add(dette);
    }

    public void updateDette(Dette dette) {
        detteRepository.update(dette);
    }

    public void deleteDette(Dette dette) {
        detteRepository.delete(dette);
    }

    public Dette findDetteById(int id) {
        return detteRepository.findById(id);
    }

    public List<Dette> findAllDettes() {
        return detteRepository.findAll();
    }

    public List<Dette> findUnpaidDettes() {
        return detteRepository.findAll().stream()
                .filter(dette -> dette.getMontantRestant() > 0)
                .toList();
    }
    
}
