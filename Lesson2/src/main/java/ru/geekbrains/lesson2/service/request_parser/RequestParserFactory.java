package ru.geekbrains.lesson2.service.request_parser;

public class RequestParserFactory {

    public static RequestParser createRequestParser(){
        return new RequestParserImpl();
    }


}
