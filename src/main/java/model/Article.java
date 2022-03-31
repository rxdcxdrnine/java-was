package model;

import java.time.LocalDate;

public class Article {

    private String writer;
    private String content;
    private LocalDate createdDate;

    public Article(String writer, String content) {
        this.writer = writer;
        this.content = content;
        this.createdDate = LocalDate.now();
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }
}
