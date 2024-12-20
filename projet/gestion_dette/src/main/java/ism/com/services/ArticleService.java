package ism.com.services;

import java.util.List;

import ism.com.entities.Article;
import ism.com.repository.IRepository;

public class ArticleService {
    private final IRepository<Article> articleRepository;

    public ArticleService(IRepository<Article> articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(Article article) {
        articleRepository.add(article);
    }

    public void updateArticle(Article article) {
        articleRepository.update(article);
    }

    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    public Article findArticleById(int id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public List<Article> findAvailableArticles() {
        return articleRepository.findAll().stream()
                .filter(article -> article.getQteStock() > 0)
                .toList();
    }
}
