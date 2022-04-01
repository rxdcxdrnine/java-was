package webserver.handler;

import model.User;
import service.UserService;
import webserver.Request;
import webserver.Response;
import webserver.mapper.Session;

public class UserLoginHandler extends AbstractPathHandler {

    private final Session session = Session.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    public Response doPost(Request request) {
        try {
            User user = userService.login(
                request.getBodyValue("userId"),
                request.getBodyValue("password")
            );

            String sessionId = session.setUser(user);

            return Response.sendRedirect("/")
                .addSession(sessionId, false)
                .build();

        } catch (IllegalArgumentException e) {
            return Response.sendRedirect("/user/login_failed.html")
                .build();
        }
    }
}
