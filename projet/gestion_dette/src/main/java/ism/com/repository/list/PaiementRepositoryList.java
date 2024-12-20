package ism.com.repository.list;

import ism.com.entities.Paiement;

public class PaiementRepositoryList extends  BaseRepositoryList<Paiement>{
    @Override
    public Paiement findById(int id) {
        return storage.stream()
                .filter(paiement -> paiement.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
