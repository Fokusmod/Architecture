package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.domain.HttpRequest;
import ru.geekbrains.lesson2.domain.HttpResponse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileService {

    public HttpResponse getResponse(String rootUrl, String contentUrl) {
        if (!existFile(rootUrl, contentUrl)) {
            return HttpResponse.builder()
                    .version("HTTP/1.1")
                    .statusCode("404 NOT_FOUND")
                    .headers(Map.of("Content-Type", "text/html; charset=utf-8"))
                    .body("<h1>Файл не найден!</h1>")
                    .build();
        }
        return HttpResponse.builder()
                .version("HTTP/1.1")
                .statusCode("200 OK")
                .headers(Map.of("Content-Type", "text/html; charset=utf-8"))
                .build();
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
