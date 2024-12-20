package ism.com.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ism.com.entities.enums.Role;
import ism.com.entities.enums.CompteEtat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;                  
    private String email;              
    private String login;              
    private String password;           
    private Role role;                 
    private CompteEtat compteEtat;  
}
