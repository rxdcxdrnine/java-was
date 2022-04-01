package webserver.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ArticleService;
import webserver.Request;
import webserver.Response;
import webserver.Status;

public class ArticleListHandler implements PathHandler {

    private static final Logger log = LoggerFactory.getLogger(ArticleListHandler.class);
    private static final String FILE_PATH = "./webapp/template/qna/list.html";

    private final ArticleService articleService = ArticleService.getInstance();

    @Override
    public Response handle(Request request) {
        List<Article> articles = articleService.findArticles();

        StringBuilder sb = new StringBuilder();
        for (int ind = 0; ind < articles.size(); ind++) {
            sb.append(toHtmlTag(ind, articles.get(ind)));
        }

        Path path = Paths.get(FILE_PATH);

        try {
            String htmlString = Files.readString(path);
            String content = String.format(htmlString, sb);

            byte[] body = content.getBytes(StandardCharsets.UTF_8);
            return new Response.Builder(Status.OK)
                .addContentType(request.getContentType())
                .body(body)
                .build();

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    private String toHtmlTag(int index, Article article) {
        return String.format(
            "<tr>" +
                "<th scope=\"row\">%d</th>" +
                "<td>%s</td>" +
                "<td>%s</td>" +
                "<td>%s</td>" +
                "</tr>",
            index + 1,
            article.getCreatedDate().toLocalDate(),
            article.getWriter(),
            article.getContent());
    }
}
