package service;

import db.ArticleDataBase;
import model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {

    private static ArticleService articleService;

    private ArticleService() {
    }

    public static ArticleService getInstance() {
        if (articleService == null) {
            articleService = new ArticleService();
        }
        return articleService;
    }

    public void write(Article article) {
        ArticleDataBase.addArticle(article);
    }

    public List<Article> findArticles() {
        return new ArrayList<>(ArticleDataBase.findAll());
    }
}
