package db;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import model.Article;

public class ArticleDataBase {

    private static final Map<Integer, Article> articles = Maps.newHashMap();

    public static void addArticle(Article article) {
        articles.put(articles.size(), article);
    }

    public static Collection<Article> findAll() {
        return articles.values().stream()
            .sorted(Comparator.comparing(Article::getCreatedDate).reversed())
            .limit(5)
            .collect(Collectors.toList());
    }
}
