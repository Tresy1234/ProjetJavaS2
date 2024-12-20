package ism.com.entities;

import lombok.Data;
import ism.com.entities.enums.DetteEtat;
import java.util.Date;
import java.util.List;

@Data
public class Dette {
    private Long id;                   // Identifiant unique de la dette
    private Date date;                 // Date de création de la dette
    private double montant;            // Montant total de la dette
    private double montantVerser;      // Montant déjà payé
    private double montantRestant;     // Montant restant à payer
    private List<Article> articles;    // Articles inclus dans cette dette
    private List<Paiement> paiements;  // Paiements effectués pour cette dette
    private DetteEtat etat; 
    private Client client;         // État de la dette (En Cours, Soldée, Annulée)
}
