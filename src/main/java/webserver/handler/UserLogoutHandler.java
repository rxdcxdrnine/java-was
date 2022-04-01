package webserver.handler;

import webserver.Request;
import webserver.Response;

public class UserLogoutHandler extends AbstractPathHandler {

    @Override
    public Response doGet(Request request) {
        String sessionId = request.getCookieValue("sessionId");

        return Response.sendRedirect("http://localhost:8080/")
            .addSession(sessionId, true)
            .build();
    }
}
