package ism.com.repository;

import java.util.List;

public interface IRepository<T> {
    void add(T entity);            // Ajouter une entité
    void update(T entity);         // Mettre à jour une entité
    void delete(T entity);         // Supprimer une entité
    T findById(int id);           // Trouver une entité par son ID
    List<T> findAll();   
    default T findByPhone(String phone) {
        throw new UnsupportedOperationException("Méthode non implémentée");
    }
    
}

