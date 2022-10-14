package ru.geekbrains.lesson2.service.request_handler;

import ru.geekbrains.lesson2.service.response_serializer.ResponseSerializerFactory;
import ru.geekbrains.lesson2.service.socket_service.SocketService;


public class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService) {
        PostMethodHandler postMethodHandler = new PostMethodHandler("POST",null,socketService,ResponseSerializerFactory.createResponseSerializer());
        return new GetMethodHandler("GET",postMethodHandler,socketService,ResponseSerializerFactory.createResponseSerializer());
    }
}
