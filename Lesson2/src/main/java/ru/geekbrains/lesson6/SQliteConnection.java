package ru.geekbrains.lesson6;

public enum SQliteConnection {

    DRIVER("org.sqlite.JDBC"),
    CONNECTION("jdbc:sqlite:Lesson2/data.db");

    final String content;

    SQliteConnection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
