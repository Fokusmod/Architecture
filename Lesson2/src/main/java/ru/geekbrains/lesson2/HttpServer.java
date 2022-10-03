package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.logger.ConsoleLogger;
import ru.geekbrains.lesson2.logger.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final Logger logger = new ConsoleLogger();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");

                new Thread(new RequestHandler(new SocketService(socket))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
