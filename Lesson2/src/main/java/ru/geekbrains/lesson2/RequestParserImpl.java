package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.domain.HttpRequest;

import java.util.List;

public class RequestParserImpl implements RequestParser{


    @Override
    public HttpRequest parse(List<String> rawRequest) {

        String[] parts = rawRequest.get(0).split(" ");

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setMethod(parts[0]);
        httpRequest.setPath(parts[1]);
        httpRequest.setVersion(parts[2]);

        return httpRequest;
    }


}
