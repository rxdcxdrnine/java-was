package webserver.handler;

import model.User;
import service.UserService;
import webserver.Request;
import webserver.Response;
import webserver.mapper.Session;

public class UserLoginHandler implements PathHandler {

    private final Session session = Session.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    public Response handle(Request request) {
        try {
            User user = userService.login(
                request.getBodyValue("userId"),
                request.getBodyValue("password")
            );

            String sessionId = session.setUser(user);

            return Response.sendRedirect("http://localhost:8080/")
                .addSession(sessionId, false)
                .build();

        } catch (IllegalArgumentException e) {
            return Response.sendRedirect("http://localhost:8080/user/login_failed.html")
                .build();
        }
    }
}
