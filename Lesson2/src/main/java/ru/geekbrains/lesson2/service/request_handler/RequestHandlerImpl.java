package ru.geekbrains.lesson2.service.request_handler;

import ru.geekbrains.lesson2.domain.HttpRequest;
import ru.geekbrains.lesson2.domain.HttpResponse;
import ru.geekbrains.lesson2.logger.ConsoleLogger;
import ru.geekbrains.lesson2.logger.Logger;
import ru.geekbrains.lesson2.service.response_serializer.ResponseSerializer;
import ru.geekbrains.lesson2.service.response_serializer.ResponseSerializerFactory;
import ru.geekbrains.lesson2.service.socket_service.SocketService;
import ru.geekbrains.lesson2.service.file_service.FileService;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class RequestHandlerImpl implements RequestHandler {

    private static final String WWW = "/Users/aleks/dev/geek-architecture-123/www";
    private static final Logger logger = ConsoleLogger.getInstance();

    private final SocketService socketService;

    public RequestHandlerImpl(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {
        FileService fileService = FileService.createFileService();
        List<String> request = socketService.readRequest();

        HttpRequest httpRequest = fileService.getRequest(request);
        HttpResponse httpResponse = fileService.getResponse(WWW, httpRequest.getPath());

        ResponseSerializer responseSerializer = ResponseSerializerFactory.createResponseSerializer();
        String header = responseSerializer.serialize(httpResponse);

        if (httpResponse.getBody()==null){
            try {
                socketService.writeResponse(header,Files.newBufferedReader(Path.of(WWW).resolve(httpRequest.getPath())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        socketService.writeResponse(header, new StringReader(httpResponse.getBody()));
        logger.info("Client disconnected!");
    }
}
