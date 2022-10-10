package ru.geekbrains.lesson2.service;

public class RequestParserFactory {

    public static RequestParser createRequestParser(){
        return new RequestParserImpl();
    }


}
