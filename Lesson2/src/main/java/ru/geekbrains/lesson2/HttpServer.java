package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.logger.ConsoleLogger;
import ru.geekbrains.lesson2.logger.Logger;
import ru.geekbrains.lesson2.service.request_handler.RequestHandlerFactory;
import ru.geekbrains.lesson2.service.socket_service.SocketServiceFactory;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final Logger logger = ConsoleLogger.getInstance();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            logger.info("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");
                new Thread(
                        RequestHandlerFactory.createRequestHandler(
                                SocketServiceFactory.createSocketService(socket))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
