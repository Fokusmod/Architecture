package ru.geekbrains.lesson2.service;

import ru.geekbrains.lesson2.domain.HttpRequest;

import java.util.List;

public interface RequestParser {

    HttpRequest parse(List<String> rawRequest);
}
