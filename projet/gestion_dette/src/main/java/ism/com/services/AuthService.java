package ism.com.services;


import ism.com.entities.User;
import ism.com.repository.IRepository;

public class AuthService {
    private IRepository<User> userRepository;
    private User currentUser;

    public AuthService(IRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String login, String password) {
    for (User user : userRepository.findAll()) {
        if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
    }
    return false;
}


    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }




}
