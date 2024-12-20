package ism.com.repository.bd;

import java.sql.Connection;
import java.util.List;

import ism.com.database.DatabaseConnection;
import ism.com.repository.IRepository;

public abstract class BaseRepositoryBD<T> implements IRepository<T> {
    protected Connection connection;

    public BaseRepositoryBD() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public abstract void add(T entity); // À implémenter dans les sous-classes

    @Override
    public abstract void update(T entity);

    @Override
    public abstract void delete(T entity);

    @Override
    public abstract T findById(int id); // Utilisation de Long pour plus de flexibilité

    @Override
    public abstract List<T> findAll();

    

    
}
