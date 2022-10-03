package ru.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void serverStart (){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new Thread(()-> new HandlerRequest(socket).read()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
