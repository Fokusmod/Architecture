package ru.geekbrains.lesson2.service.request_handler;

import ru.geekbrains.lesson2.service.request_parser.RequestParser;
import ru.geekbrains.lesson2.service.socket_service.SocketService;

public class RequestHandlerFactory {

    public static RequestHandlerImpl createRequestHandler(SocketService socketService, RequestParser requestParser) {
        return new RequestHandlerImpl(socketService, requestParser);
    }
}
