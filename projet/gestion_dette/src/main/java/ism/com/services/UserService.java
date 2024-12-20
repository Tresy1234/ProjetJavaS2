package ism.com.services;

import java.util.ArrayList;
import java.util.List;

import ism.com.entities.User;
import ism.com.entities.enums.CompteEtat;
import ism.com.entities.enums.Role;
import ism.com.repository.IRepository;

public class UserService {
    private final IRepository<User> userRepository;

    public UserService(IRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    // Ajouter un utilisateur
    public void createUser(User user) {
        userRepository.add(user);
    }

    // Mettre à jour un utilisateur
    public void updateUser(User user) {
        userRepository.update(user);
    }

    // Supprimer un utilisateur
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // Trouver un utilisateur par ID
    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    // Récupérer tous les utilisateurs
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Trouver des utilisateurs par rôle
    public List<User> findUsersByRole(String role) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().name().equalsIgnoreCase(role))
                .toList();
    }

    // Trouver des utilisateurs actifs ou inactifs
    public List<User> findActiveUsers(boolean isActive) {
        return userRepository.findAll().stream()
                .filter(user -> (isActive && user.getCompteEtat() == CompteEtat.ACTIF) ||
                        (!isActive && user.getCompteEtat() == CompteEtat.DESACTIVE))
                .toList();
    }

    // Créer un compte utilisateur pour un client
    public void createAccountForClient(User user) {
        for (User existingUser : userRepository.findAll()) {
            if (existingUser.getLogin().equals(user.getLogin())) {
                throw new IllegalArgumentException("Un compte existe déjà avec ce login.");
            }
        }
        userRepository.add(user);
        System.out.println("Compte utilisateur créé avec succès.");
    }

    // Activer ou désactiver un compte utilisateur
    public void toggleAccountStatus(int userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("Utilisateur non trouvé.");
        }
        if (user.getCompteEtat() == CompteEtat.ACTIF) {
            user.setCompteEtat(CompteEtat.DESACTIVE);
        } else {
            user.setCompteEtat(CompteEtat.ACTIF);
        }
        userRepository.update(user);
        System.out.println("Le statut du compte a été mis à jour.");
    }

    // Filtrer les utilisateurs par rôle et état
    public List<User> getUsersByFilter(Role role, CompteEtat etat) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if ((role == null || user.getRole() == role) &&
                (etat == null || user.getCompteEtat() == etat)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }
}
