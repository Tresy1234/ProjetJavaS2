package ism.com.repository.list;

import ism.com.entities.User;
import ism.com.entities.enums.CompteEtat;
import ism.com.entities.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryList extends BaseRepositoryList<User> {

    private final List<User> users = new ArrayList<>();
    private long nextId = 1; // Simule l'auto-incrémentation des IDs

    public UserRepositoryList() {
        // Initialisation des données de test
        users.add(new User(nextId++, "admin@example.com", "admin", "passer123", Role.ADMIN, CompteEtat.ACTIF));
        users.add(new User(nextId++, "client1@example.com", "client1", "passer123", Role.CLIENT, CompteEtat.ACTIF));
        users.add(new User(nextId++, "boutiquier1@example.com", "boutiquier1", "passer123", Role.BOUTIQUIER, CompteEtat.ACTIF));
    }

    @Override
    public void add(User user) {
        user.setId(nextId++);
        users.add(user);
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                return;
            }
        }
        throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + user.getId());
    }

    @Override
    public void delete(User user) {
        users.removeIf(u -> u.getId() == user.getId());
    }

    @Override
    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }
}
