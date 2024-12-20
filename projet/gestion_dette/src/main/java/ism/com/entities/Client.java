package ism.com.entities;

import java.util.List;

import ism.com.repository.IRepository;
import lombok.Data;

@Data
public class Client {
    private int id;
    private String surname;
    private String phone;
    private String address;
    private User user;
    private List<User> users;
    public IRepository<Dette> getDettes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDettes'");
    }

    
}
