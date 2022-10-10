package ru.geekbrains.lesson2.logger;

public class ConsoleLogger implements Logger {

    private static ConsoleLogger instance;

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    public static ConsoleLogger getInstance() {
        if (instance == null) {
            instance = new ConsoleLogger();
        }
        return instance;
    }
}
