package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    private String WWW;

    private String[] parts;

    public FileService(String WWW, String[] parts) {
        this.WWW = WWW;
        this.parts = parts;
    }


    public void readFile(PrintWriter output) {
        Path path = Paths.get(WWW, parts[1]);

        if (!Files.exists(path)) {
            output.println("HTTP/1.1 404 NOT_FOUND");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<h1>Файл не найден!</h1>");
            output.flush();
            return;
        }
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();

        try {
            Files.newBufferedReader(path).transferTo(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Client disconnected!");
    }

}
