package ru.geekbrains.lesson2.service.response_serializer;

import ru.geekbrains.lesson2.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
