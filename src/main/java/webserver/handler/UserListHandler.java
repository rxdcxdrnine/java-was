package webserver.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import webserver.Request;
import webserver.Response;
import webserver.Status;

public class UserListHandler implements PathHandler {

    private static final Logger log = LoggerFactory.getLogger(UserListHandler.class);
    private static final String FILE_PATH = "./webapp/template/user/list.html";

    private final UserService userService = UserService.getInstance();

    @Override
    public Response handle(Request request) {
        List<User> users = userService.findUsers();

        // TODO: template engine 에 맞게 바꾸기
        // model.addAttribute("user", user);
        // return "view.html";
        StringBuilder sb = new StringBuilder();
        for (int ind = 0; ind < users.size(); ind++) {
            sb.append(toHtmlTag(ind, users.get(ind)));
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

    private String toHtmlTag(int index, User user) {
        return String.format(
            "<tr>" +
                "<th scope=\"row\">%d</th>" +
                "<td>%s</td>" +
                "<td>%s</td>" +
                "<td>%s</td>" +
                "<td><a href=\"#\" class=\"btn btn-success\" role=\"button\">수정</a></td>" +
                "</tr>",
            index + 1,
            user.getUserId(),
            user.getName(),
            user.getEmail());
    }
}
