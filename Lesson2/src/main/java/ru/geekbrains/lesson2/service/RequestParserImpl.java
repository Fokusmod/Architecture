package ru.geekbrains.lesson2.service;

import ru.geekbrains.lesson2.domain.HttpRequest;
import ru.geekbrains.lesson2.service.RequestParser;

import java.util.List;

class RequestParserImpl implements RequestParser {
    @Override
    public HttpRequest parse(List<String> rawRequest) {
        String[] parts = rawRequest.get(0).split(" ");
        return HttpRequest.builder()
                .method(parts[0])
                .path(parts[1])
                .version(parts[2])
                .build();
    }
}
