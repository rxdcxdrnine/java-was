package db;

import com.google.common.collect.Maps;
import model.Article;

import java.util.Collection;
import java.util.Map;

public class ArticleDataBase {

    private static final Map<Integer, Article> articles = Maps.newHashMap();

    public static void addArticle(Article article) {
        articles.put(articles.size(), article);
    }

    public static Collection<Article> findAll() {
        return articles.values();
    }
}
