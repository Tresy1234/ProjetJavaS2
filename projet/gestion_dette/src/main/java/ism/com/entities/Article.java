package ism.com.entities;
import lombok.Data;

@Data
public class Article {
    private Long id;                   // Identifiant unique de l'article
    private String name;               // Nom de l'article
    private double price;              // Prix unitaire de l'article
    private int qteStock;  
    
}
