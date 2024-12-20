package ism.com.repository.list;

import java.util.ArrayList;
import java.util.List;

import ism.com.repository.IRepository;

public abstract class   BaseRepositoryList<T> implements IRepository<T> {
     protected List<T> storage = new ArrayList<>(); 

    @Override
    public void add(T entity) {
        storage.add(entity);
    }

    @Override
    public void update(T entity) {
        int index = storage.indexOf(entity);
        if (index != -1) {
            storage.set(index, entity);
        }
    }

    @Override
    public void delete(T entity) {
        storage.remove(entity);
    }

    @Override
    public T findById(int id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return storage;
    }

    
}
