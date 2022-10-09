package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.domain.HttpRequest;
import ru.geekbrains.lesson2.domain.HttpResponse;
import ru.geekbrains.lesson2.logger.ConsoleLogger;
import ru.geekbrains.lesson2.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHandler implements Runnable {

    private static final String WWW = "/Users/aleks/dev/geek-architecture-123/www";
    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {
        FileService fileService = new FileService();
        List<String> request = socketService.readRequest();
        HttpRequest httpRequest = fileService.getRequest(request);

        HttpResponse httpResponse = fileService.getResponse(WWW, httpRequest.getPath());

        ResponseSerializer responseSerializer = new ResponseSerializerImpl();
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
