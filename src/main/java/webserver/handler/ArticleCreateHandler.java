package webserver.handler;

import model.Article;
import model.User;
import service.ArticleService;
import webserver.Request;
import webserver.Response;

public class ArticleCreateHandler extends AbstractPathHandler {

    private final ArticleService articleService = ArticleService.getInstance();

    @Override
    public Response doPost(Request request) {
        // request 객체의 sessionUser 조회
        User user = request.getSessionUser();

        Article article = new Article(user.getName(), request.getBodyValue("content"));
        articleService.write(article);

        return Response.sendRedirect("/")
            .build();
    }
}
