package ism.com.repository.bd;

import ism.com.entities.Paiement;
import ism.com.entities.enums.PaiementEtat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementRepositoryBD extends BaseRepositoryBD<Paiement> {

    @Override
    public void add(Paiement paiement) {
        try {
            String sql = "INSERT INTO paiements (date, montant, etat) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(paiement.getDate().getTime()));
            stmt.setDouble(2, paiement.getMontant());
            stmt.setString(3, paiement.getEtat().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Paiement paiement) {
        try {
            String sql = "UPDATE paiements SET date = ?, montant = ?, etat = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(paiement.getDate().getTime()));
            stmt.setDouble(2, paiement.getMontant());
            stmt.setString(3, paiement.getEtat().name());
            stmt.setLong(4, paiement.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Paiement paiement) {
        try {
            String sql = "DELETE FROM paiements WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, paiement.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paiement findById(int id) { // Correction de la signature pour correspondre à BaseRepositoryBD
        try {
            String sql = "SELECT * FROM paiements WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(rs.getLong("id"));
                paiement.setDate(rs.getDate("date"));
                paiement.setMontant(rs.getDouble("montant"));
                paiement.setEtat(PaiementEtat.valueOf(rs.getString("etat")));
                return paiement;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Paiement> findAll() {
        List<Paiement> paiements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM paiements";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(rs.getLong("id"));
                paiement.setDate(rs.getDate("date"));
                paiement.setMontant(rs.getDouble("montant"));
                paiement.setEtat(PaiementEtat.valueOf(rs.getString("etat")));
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }

    
}
