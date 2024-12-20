package ism.com.repository.list;

import ism.com.entities.Dette;

public class DetteRepositoryList extends  BaseRepositoryList<Dette>{
    @Override
    public Dette findById(int id) {
        return storage.stream()
                .filter(dette -> dette.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
