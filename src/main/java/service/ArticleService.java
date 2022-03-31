package service;

import db.ArticleDataBase;
import model.Article;

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
}
