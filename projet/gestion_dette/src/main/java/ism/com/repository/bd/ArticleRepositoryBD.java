package ism.com.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ism.com.entities.Article;

public class ArticleRepositoryBD extends BaseRepositoryBD<Article>{
     @Override
    public void add(Article article) {
        try {
            String sql = "INSERT INTO articles (name, price, qteStock) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, article.getName());
            stmt.setDouble(2, article.getPrice());
            stmt.setInt(3, article.getQteStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Article article) {
        try {
            String sql = "UPDATE articles SET name = ?, price = ?, qteStock = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, article.getName());
            stmt.setDouble(2, article.getPrice());
            stmt.setInt(3, article.getQteStock());
            stmt.setLong(4, article.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Article article) {
        try {
            String sql = "DELETE FROM articles WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, article.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Article findById(int id) {
        try {
            String sql = "SELECT * FROM articles WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Article article = new Article();
                article.setId((long) rs.getInt("id"));
                article.setName(rs.getString("name"));
                article.setPrice(rs.getDouble("price"));
                article.setQteStock(rs.getInt("qteStock"));
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM articles";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setName(rs.getString("name"));
                article.setPrice(rs.getDouble("price"));
                article.setQteStock(rs.getInt("qteStock"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
 
   
}
