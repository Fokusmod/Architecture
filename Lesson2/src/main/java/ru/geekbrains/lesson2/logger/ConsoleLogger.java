package ru.geekbrains.lesson2.logger;

public class ConsoleLogger implements Logger {

    private static Logger instance;

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new ConsoleLogger();
        }
        return TimeConsoleLogger.createTimeConsoleLogger(instance);
    }
}
