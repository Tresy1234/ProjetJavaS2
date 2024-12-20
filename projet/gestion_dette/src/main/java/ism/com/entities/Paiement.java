package ism.com.entities;

import lombok.Data;
import ism.com.entities.enums.PaiementEtat;
import java.util.Date;

@Data
public class Paiement {
    private Long id;                   
    private Date date;                 
    private double montant;       
    private PaiementEtat etat;  
    private int detteId;      
}


