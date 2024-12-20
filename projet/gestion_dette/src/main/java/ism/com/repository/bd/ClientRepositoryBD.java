package ism.com.repository.bd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ism.com.entities.Client;

public class ClientRepositoryBD extends BaseRepositoryBD<Client>{

  @Override
    public void add(Client client) {
        try {
            String sql = "INSERT INTO clients (surname, phone, address) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, client.getSurname());
            stmt.setString(2, client.getPhone());
            stmt.setString(3, client.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        try {
            String sql = "UPDATE clients SET surname = ?, phone = ?, address = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, client.getSurname());
            stmt.setString(2, client.getPhone());
            stmt.setString(3, client.getAddress());
            stmt.setLong(4, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client client) {
        try {
            String sql = "DELETE FROM clients WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client findById(int id) {
        try {
            String sql = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setSurname(rs.getString("surname"));
                client.setPhone(rs.getString("phone"));
                client.setAddress(rs.getString("address"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clients";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setSurname(rs.getString("surname"));
                client.setPhone(rs.getString("phone"));
                client.setAddress(rs.getString("address"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
    @Override
public Client findByPhone(String phone) {
    try {
        String sql = "SELECT * FROM clients WHERE phone = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, phone);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Client client = new Client();
            client.setId(rs.getInt("id"));
            client.setSurname(rs.getString("surname"));
            client.setPhone(rs.getString("phone"));
            client.setAddress(rs.getString("address"));
            return client;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    
}
