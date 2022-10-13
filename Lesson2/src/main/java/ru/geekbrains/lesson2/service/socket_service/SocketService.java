package ru.geekbrains.lesson2.service.socket_service;

import java.io.Closeable;
import java.io.Reader;
import java.util.List;

public interface SocketService extends Closeable {

    List<String> readRequest();

    void writeResponse(String headers, Reader reader);

}
