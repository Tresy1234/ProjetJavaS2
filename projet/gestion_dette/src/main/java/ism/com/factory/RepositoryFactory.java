package ism.com.factory;

import ism.com.entities.Article;
import ism.com.entities.Client;
import ism.com.entities.Dette;
import ism.com.entities.Paiement;
import ism.com.entities.User;
import ism.com.repository.IRepository;
import ism.com.repository.bd.ArticleRepositoryBD;
import ism.com.repository.bd.ClientRepositoryBD;
import ism.com.repository.bd.DetteRepositoryBD;
import ism.com.repository.bd.PaiementRepositoryBD;
import ism.com.repository.bd.UserRepositoryBD;
import ism.com.repository.list.ArticleRepositoryList;
import ism.com.repository.list.ClientRepositoryList;
import ism.com.repository.list.DetteRepositoryList;
import ism.com.repository.list.PaiementRepositoryList;
import ism.com.repository.list.UserRepositoryList;

public class RepositoryFactory {
     public static IRepository<?> createRepository(String type, Class<?> entityClass) {
        if (type.equalsIgnoreCase("bd")) {
            // Création des repositories pour la base de données
            if (entityClass.equals(Client.class)) {
                return new ClientRepositoryBD();
            } else if (entityClass.equals(Dette.class)) {
                return new DetteRepositoryBD();
            } else if (entityClass.equals(Article.class)) {
                return new ArticleRepositoryBD();
            } else if (entityClass.equals(Paiement.class)) {
                return new PaiementRepositoryBD();
            } else if (entityClass.equals(User.class)) {
                return new UserRepositoryBD();
            }
        } else if (type.equalsIgnoreCase("list")) {
            // Création des repositories en mémoire
            if (entityClass.equals(Client.class)) {
                return new ClientRepositoryList();
            } else if (entityClass.equals(Dette.class)) {
                return new DetteRepositoryList();
            } else if (entityClass.equals(Article.class)) {
                return new ArticleRepositoryList();
            } else if (entityClass.equals(Paiement.class)) {
                return new PaiementRepositoryList();
            } else if (entityClass.equals(User.class)) {
                return new UserRepositoryList();
            }
        }
        throw new IllegalArgumentException("Type de repository ou entité non pris en charge");
    }
}
