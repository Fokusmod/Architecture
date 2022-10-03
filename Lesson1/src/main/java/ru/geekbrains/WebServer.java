package ru.geekbrains;


public class WebServer {

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.serverStart();

    }

}
