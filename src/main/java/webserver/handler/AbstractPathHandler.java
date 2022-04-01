package webserver.handler;

import webserver.Request;
import webserver.Response;
import webserver.Status;

public abstract class AbstractPathHandler implements PathHandler {

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";

    @Override
    public Response handle(Request request) {
        if (request.checkMethod(GET_METHOD)) {
            return doGet(request);
        }
        if (request.checkMethod(POST_METHOD)) {
            return doPost(request);
        }
        return null;
    }

    protected Response doGet(Request request) {
        return new Response.Builder(Status.METHOD_NOT_ALLOWED)
            .build();
    }

    protected Response doPost(Request request) {
        return new Response.Builder(Status.METHOD_NOT_ALLOWED)
            .build();
    }

}
