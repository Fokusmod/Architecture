package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandlerRequest {

    private Socket socket;

    private String url = "/Users/macbook/IdeaProjects/first-geek-web-server/www";

    public HandlerRequest(Socket socket) {
        this.socket = socket;
    }

    public void read() {

        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

             PrintWriter output = new PrintWriter(socket.getOutputStream())) {

            while (!input.ready());

            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");
            System.out.println(firstLine);
            while (input.ready()) {
                System.out.println(input.readLine());
            }
            FileService fileService = new FileService(url,parts);
            fileService.readFile(output);

            socket.close();
            System.out.println("Client disconnected!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
