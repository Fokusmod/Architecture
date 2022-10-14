package ru.geekbrains.lesson2.service.socket_service;

import ru.geekbrains.lesson2.domain.HttpResponse;
import ru.geekbrains.lesson2.logger.ConsoleLogger;
import ru.geekbrains.lesson2.logger.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class SocketServiceImpl implements SocketService {

    private final Logger logger;
    private final Socket socket;

    SocketServiceImpl(Socket socket) {
        this.socket = socket;
        this.logger = ConsoleLogger.getInstance();
    }
    @Override
    public List<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            while (!input.ready()) ;
            List<String> request = new ArrayList<>();
            while (input.ready()) {
                String line = input.readLine();
                logger.info(line);
                request.add(line);
            }
            return request;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    @Override
    public void writeResponse(String headers, Reader reader) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(headers);
            if (reader != null) {
                reader.transferTo(output);
            }
            output.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}
