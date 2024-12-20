package ism.com.repository.list;

import ism.com.entities.Article;

public class ArticleRepositoryList extends  BaseRepositoryList<Article>{
    @Override
    public Article findById(int id) {
        return storage.stream()
                .filter(article -> article.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
