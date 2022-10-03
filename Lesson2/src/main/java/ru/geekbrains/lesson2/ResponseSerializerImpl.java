package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.domain.HttpResponse;

import java.util.Iterator;
import java.util.Map;

public class ResponseSerializerImpl implements ResponseSerializer {

    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpResponse.getVersion()).append(" ").append(httpResponse.getStatusCode()).append(" \n");

        Map<String,String> headers = httpResponse.getHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }


}
