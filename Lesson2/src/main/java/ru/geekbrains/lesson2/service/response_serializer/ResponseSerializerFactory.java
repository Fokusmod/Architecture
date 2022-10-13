package ru.geekbrains.lesson2.service.response_serializer;

public class ResponseSerializerFactory {

    public static ResponseSerializer createResponseSerializer(){
        return new ResponseSerializerImpl();
    }
}
