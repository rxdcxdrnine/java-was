package webserver.handler;

import model.User;
import service.UserService;
import webserver.Request;
import webserver.Response;
import webserver.mapper.Session;

public class UserCreateHandler extends AbstractPathHandler {

    private static final Session session = Session.getInstance();
    private static final UserService userService = UserService.getInstance();

    @Override
    public Response doPost(Request request) {

        try {
            User user = new User(
                request.getBodyValue("userId"),
                request.getBodyValue("password"),
                request.getBodyValue("name"),
                request.getBodyValue("email")
            );
            userService.register(user);

            String sessionId = session.setUser(user);

            return Response.sendRedirect("http://localhost:8080/")
                .addSession(sessionId, false)
                .build();

        } catch (IllegalArgumentException e) {
            return Response.sendRedirect("http://localhost:8080/user/form.html")
                .build();
        }
    }

}
