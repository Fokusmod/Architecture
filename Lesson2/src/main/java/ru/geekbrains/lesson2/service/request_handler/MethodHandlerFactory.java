package ru.geekbrains.lesson2.service.request_handler;

import org.reflections.Reflections;
import ru.geekbrains.lesson2.service.response_serializer.ResponseSerializerFactory;
import ru.geekbrains.lesson2.service.socket_service.SocketService;

import java.util.*;



public class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService) {
//        abracadabra(socketService);
        PostMethodHandler postMethodHandler = new PostMethodHandler("POST", null, socketService, ResponseSerializerFactory.createResponseSerializer());
        return new GetMethodHandler("GET", postMethodHandler, socketService, ResponseSerializerFactory.createResponseSerializer());
    }


    public static void abracadabra(SocketService socketService) {
        Reflections reflection = new Reflections("ru.geekbrains.lesson2.service.request_handler");
        reflection.getTypesAnnotatedWith(Handler.class);
        Set<Class<?>> sortedSet = new TreeSet<>(Comparator.comparingInt(o -> o.getDeclaredAnnotation(Handler.class).handlerOrder()));
        sortedSet.addAll(reflection.getTypesAnnotatedWith(Handler.class));
        }
    }

