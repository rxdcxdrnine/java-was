package model;

import java.time.LocalDateTime;

public class Article {

    private final String writer;
    private final String content;
    private final LocalDateTime createdDate;

    public Article(String writer, String content) {
        this.writer = writer;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
