package webserver;

public enum Status {
    OK("200 OK"),
    CREATED("201 CREATED"),
    FOUND("302 FOUND"),
    METHOD_NOT_ALLOWED("405 METHOD NOT ALLOWED");

    private String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
