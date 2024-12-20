package ism.com.services;

import java.util.List;

import ism.com.entities.Paiement;
import ism.com.repository.IRepository;

public class PaiementService {
    private final IRepository<Paiement> paiementRepository;

    public PaiementService(IRepository<Paiement> paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public void createPaiement(Paiement paiement) {
        paiementRepository.add(paiement);
    }

    public void updatePaiement(Paiement paiement) {
        paiementRepository.update(paiement);
    }

    public void deletePaiement(Paiement paiement) {
        paiementRepository.delete(paiement);
    }

    public Paiement findPaiementById(int id) {
        return paiementRepository.findById(id);
    }

    public List<Paiement> findAllPaiements() {
        return paiementRepository.findAll();
    }
}
