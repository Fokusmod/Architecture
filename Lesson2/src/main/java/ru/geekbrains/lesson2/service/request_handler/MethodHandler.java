package ru.geekbrains.lesson2.service.request_handler;

import ru.geekbrains.lesson2.domain.HttpRequest;
import ru.geekbrains.lesson2.domain.HttpResponse;
import ru.geekbrains.lesson2.domain.StatusCode;
import ru.geekbrains.lesson2.service.response_serializer.ResponseSerializer;
import ru.geekbrains.lesson2.service.socket_service.SocketService;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public abstract class MethodHandler {
    private final String method;
    private final MethodHandler next;
    private final SocketService socketService;
    private final ResponseSerializer responseSerializer;
    private static final String WWW = "/Users/Fokusmod/Desktop/Architecture/Lesson2/123.txt";

    public MethodHandler(String method, MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
    }

    public void handle(HttpRequest httpRequest) {
        HttpResponse httpResponse = checkFile(WWW, httpRequest.getPath());
        if (httpResponse!= null){
            writeResponse(httpResponse, responseSerializer);
            return;
        }
        if (method.equals(httpRequest.getMethod())) {
            httpResponse = handleInternal(httpRequest);
        } else if (next != null) {
            next.handle(httpRequest);
            return;
        } else {
            System.out.println("method not allowed");
        }
        writeResponse(httpResponse, responseSerializer);
    }

    private void writeResponse(HttpResponse httpResponse, ResponseSerializer responseSerializer) {
        String responseBody = responseSerializer.serialize(httpResponse);
        if (httpResponse.getBody() == null) {
            try {
                socketService.writeResponse(responseBody, Files.newBufferedReader(Path.of(WWW)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        socketService.writeResponse(responseBody, new StringReader(httpResponse.getBody()));
    }

    protected abstract HttpResponse handleInternal(HttpRequest httpRequest);

    public HttpResponse checkFile(String rootUrl, String contentUrl) {
        Path path = Paths.get(rootUrl, contentUrl);
        if(!Files.exists(path)){
            return HttpResponse.builder()
                .version("HTTP/1.1")
                .statusCode(StatusCode.NOT_FOUND)
                .headers(Map.of("Content-Type", "text/html; charset=utf-8"))
                .body("<h1>Файл не найден!</h1>")
                .build();
        }
        return null;
    }


}
