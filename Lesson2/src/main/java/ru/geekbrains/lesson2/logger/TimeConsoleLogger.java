package ru.geekbrains.lesson2.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConsoleLogger implements Logger{

    private final SimpleDateFormat date;
    private final Logger logger;

    private TimeConsoleLogger(Logger logger) {
        this.logger = logger;
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static Logger createTimeConsoleLogger(Logger logger){
        return new TimeConsoleLogger(logger);
    }

    @Override
    public void info(String msg) {
        String format = date.format((new Date(System.currentTimeMillis())));
        logger.info("[" + format + "]: " + msg);
    }
}
