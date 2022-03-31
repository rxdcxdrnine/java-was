package webserver.handler;

import model.Article;
import model.User;
import service.ArticleService;
import webserver.Request;
import webserver.Response;
import webserver.Status;

public class ArticleCreateHandler implements PathHandler {

    private ArticleService articleService = ArticleService.getInstance();

    @Override
    public Response handle(Request request) {
        // request 객체의 sessionUser 조회
        User user = request.getSessionUser();

        Article article = new Article(user.getName(), request.getBodyValue("content"));
        articleService.write(article);

        return new Response.Builder(Status.FOUND)
                .addHeader("Location", "http://localhost:8080/")
                .build();
    }
}
