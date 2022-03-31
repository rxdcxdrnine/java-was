package webserver.mapper;

import model.User;
import webserver.Request;
import webserver.Response;
import webserver.Status;

public class Filter {

    private static Session session = Session.getInstance();

    public Response handle(Request request) {

        // TODO: 로그인이 필요한 path 의 지정 방법
        if (request.getPath().endsWith(".css")
                || request.getPath().endsWith(".js")
                || request.getPath().endsWith(".woff")
                || request.getPath().endsWith(".ico")

                || request.getPath().equals("/user/login.html")
                || request.getPath().equals("/user/login_failed.html")
                || request.getPath().equals("/user/form.html")
                || request.getPath().equals("/index.html")

                || request.getPath().equals("/user/create")
        ) {
            return null;
        }

        String sessionId = request.getCookieValue("sessionId");
        if (sessionId == null) {
            return getLoginFormRedirect();
        }

        User user = session.getUser(sessionId);
        if (user == null) {
            return getLoginFormRedirect();
        }

        request.setSessionUser(user);
        return null;
    }

    public Response getLoginFormRedirect() {
        return new Response.Builder(Status.FOUND)
                .addHeader("Location", "http://localhost:8080/user/login.html")
                .build();
    }
}
