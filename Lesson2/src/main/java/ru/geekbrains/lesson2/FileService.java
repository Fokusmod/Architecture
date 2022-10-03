package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.domain.HttpRequest;
import ru.geekbrains.lesson2.domain.HttpResponse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileService {

    public HttpResponse getResponse(String rootUrl, String contentUrl) {
        if (!existFile(rootUrl, contentUrl)) {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setVersion("HTTP/1.1");
            httpResponse.setStatusCode("404 NOT_FOUND");
            httpResponse.setHeaders(new HashMap<>(Map.of("Content-Type", "text/html; charset=utf-8")));
            httpResponse.setBody("<h1>Файл не найден!</h1>");
            return httpResponse;
        }
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setVersion("HTTP/1.1");
        httpResponse.setStatusCode("200 OK");
        httpResponse.setHeaders(new HashMap<>(Map.of("Content-Type", "text/html; charset=utf-8")));
        return httpResponse;
    }


    public HttpRequest getRequest(List<String> request) {
        RequestParser requestParser = new RequestParserImpl();
        return requestParser.parse(request);
    }

    public boolean existFile(String rootUrl, String contentUrl) {
        Path path = Paths.get(rootUrl, contentUrl);
        return Files.exists(path);
    }

}
