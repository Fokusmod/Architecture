package ru.geekbrains.lesson2.service.request_handler;

import ru.geekbrains.lesson2.domain.HttpRequest;
import ru.geekbrains.lesson2.logger.ConsoleLogger;
import ru.geekbrains.lesson2.logger.Logger;
import ru.geekbrains.lesson2.service.request_parser.RequestParser;
import ru.geekbrains.lesson2.service.socket_service.SocketService;



class RequestHandlerImpl implements RequestHandler {

    private static final String WWW = "/Users/Fokusmod/Desktop/Architecture/Lesson2/123.txt";
    private static final Logger logger = ConsoleLogger.getInstance();
    private final SocketService socketService;
    private final RequestParser requestParser;
    private final MethodHandler methodHandler;


    public RequestHandlerImpl(SocketService socketService, RequestParser requestParser) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.methodHandler = AnnotationHandlerFactory.create(socketService);

    }

    @Override
    public void run() {
        HttpRequest httpRequest = requestParser.parse(socketService.readRequest());
        methodHandler.handle(httpRequest);
        logger.info("Client disconnected!");
    }
}
