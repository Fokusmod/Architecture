package ru.geekbrains.lesson2.service.request_handler;

import ru.geekbrains.lesson2.domain.HttpRequest;
import ru.geekbrains.lesson2.domain.HttpResponse;
import ru.geekbrains.lesson2.domain.StatusCode;
import ru.geekbrains.lesson2.service.response_serializer.ResponseSerializer;
import ru.geekbrains.lesson2.service.socket_service.SocketService;

import java.util.Map;
@Handler(handlerOrder = 2)
public class PostMethodHandler extends MethodHandler{

    public PostMethodHandler() {
    }

    public PostMethodHandler(String method, MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer) {
        super(method, next, socketService, responseSerializer);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest httpRequest) {
        return HttpResponse.builder()
                .version("HTTP/1.1")
                .statusCode(StatusCode.OK)
                .headers(Map.of("Content-Type", "text/html; charset=utf-8"))
                .body("<h1>IM POST METHOD</h1")
                .build();
    }


}
