package ru.geekbrains.lesson2.service;

import java.net.Socket;

public class SocketServiceFactory {

    public static SocketService createSocketService(Socket socket){
        return new SocketServiceImpl(socket);
    }

}
