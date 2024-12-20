package ism.com.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ism.com.entities.Dette;
import ism.com.entities.enums.DetteEtat;

public class DetteRepositoryBD  extends BaseRepositoryBD<Dette>   {
    
    @Override
    public void add(Dette dette) {
        try {
            String sql = "INSERT INTO dettes (date, montant, montantVerser, montantRestant, etat) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(dette.getDate().getTime()));
            stmt.setDouble(2, dette.getMontant());
            stmt.setDouble(3, dette.getMontantVerser());
            stmt.setDouble(4, dette.getMontantRestant());
            stmt.setString(5, dette.getEtat().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Dette dette) {
        try {
            String sql = "UPDATE dettes SET date = ?, montant = ?, montantVerser = ?, montantRestant = ?, etat = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(dette.getDate().getTime()));
            stmt.setDouble(2, dette.getMontant());
            stmt.setDouble(3, dette.getMontantVerser());
            stmt.setDouble(4, dette.getMontantRestant());
            stmt.setString(5, dette.getEtat().name());
            stmt.setLong(6, dette.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Dette dette) {
        try {
            String sql = "DELETE FROM dettes WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, dette.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dette findById(int id) {
        try {
            String sql = "SELECT * FROM dettes WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Dette dette = new Dette();
                dette.setId(rs.getLong("id"));
                dette.setDate(rs.getDate("date"));
                dette.setMontant(rs.getDouble("montant"));
                dette.setMontantVerser(rs.getDouble("montantVerser"));
                dette.setMontantRestant(rs.getDouble("montantRestant"));
                dette.setEtat(DetteEtat.valueOf(rs.getString("etat")));
                return dette;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dette> findAll() {
        List<Dette> dettes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dettes";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dette dette = new Dette();
                dette.setId(rs.getLong("id"));
                dette.setDate(rs.getDate("date"));
                dette.setMontant(rs.getDouble("montant"));
                dette.setMontantVerser(rs.getDouble("montantVerser"));
                dette.setMontantRestant(rs.getDouble("montantRestant"));
                dette.setEtat(DetteEtat.valueOf(rs.getString("etat")));
                dettes.add(dette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dettes;
    }
 
    
}
