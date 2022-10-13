package ru.geekbrains.lesson2.service.request_handler;

import ru.geekbrains.lesson2.service.socket_service.SocketService;

public class RequestHandlerFactory {

    public static RequestHandlerImpl createRequestHandler(SocketService socketService){
        return new RequestHandlerImpl(socketService);
    }
}
