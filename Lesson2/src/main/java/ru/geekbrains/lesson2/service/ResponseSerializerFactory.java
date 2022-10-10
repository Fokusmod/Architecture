package ru.geekbrains.lesson2.service;

public class ResponseSerializerFactory {

    public static ResponseSerializer createResponseSerializer(){
        return new ResponseSerializerImpl();
    }
}
