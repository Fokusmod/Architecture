package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
