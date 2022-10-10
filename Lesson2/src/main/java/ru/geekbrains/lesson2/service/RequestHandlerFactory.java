package ru.geekbrains.lesson2.service;

public class RequestHandlerFactory {

    public static RequestHandlerImpl createRequestHandler(SocketService socketService){
        return new RequestHandlerImpl(socketService);
    }
}
