package ru.geekbrains.lesson2.domain;

public enum StatusCode {

    OK(200, "OK"),
    NOT_FOUND(404,"NOT_FOUND");

    final int code;

    final String title;

    StatusCode(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
