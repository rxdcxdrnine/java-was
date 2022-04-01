package webserver;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Response {

    private static final Logger log = LoggerFactory.getLogger(Response.class);

    private final Status status;
    private final Map<String, String> header;
    private final byte[] body;

    public Response(Status status, Map<String, String> header, byte[] body) {
        this.status = status;
        this.header = header;
        this.body = body;
    }

    public Response(Status status, Map<String, String> header) {
        this(status, header, null);
    }

    public Status getStatus() {
        return status;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public byte[] getBody() {
        return body;
    }

    public static Builder sendRedirect(String url) {
        return new Builder(Status.FOUND)
            .addHeader("Location", url);
    }

    public static class Builder {

        private final Status status;
        private Map<String, String> header = new HashMap<>();
        private byte[] body;

        public Builder(Status status) {
            this.status = status;
        }

        public Builder addHeader(String key, String value) {
            header.put(key, value);
            return this;
        }

        public Builder addSession(String value, boolean isExpired) {
            String cookie = "sessionId=" + value + "; Path=/"
                + (isExpired ? "; expires=Thu, 01 Jan 1970 00:00:00 GMT" : "");
            header.put("Set-Cookie", cookie);
            return this;
        }

        public Builder addContentType(ContentType contentType) {
            header.put("Content-Type", contentType.getMime());
            return this;
        }

        public Builder body(byte[] body) {
            header.put("Content-Length", String.valueOf(body.length));
            this.body = body;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }

    private Response(Builder builder) {
        this.status = builder.status;
        this.header = builder.header;
        this.body = builder.body;
    }
}
