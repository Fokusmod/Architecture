package ru.geekbrains.lesson2.service.request_handler;

import org.reflections.Reflections;
import ru.geekbrains.lesson2.service.response_serializer.ResponseSerializer;
import ru.geekbrains.lesson2.service.response_serializer.ResponseSerializerFactory;
import ru.geekbrains.lesson2.service.socket_service.SocketService;

import java.lang.reflect.Constructor;
import java.util.*;



public class AnnotationHandlerFactory {

    private static final String HANDLER_PACKAGE = "ru.geekbrains.lesson2.service.request_handler";

    public static MethodHandler create(SocketService socketService) {
        Reflections reflection = new Reflections(HANDLER_PACKAGE);
        List<Class<?>> classes = new ArrayList<>(reflection.getTypesAnnotatedWith(Handler.class));

        MethodHandler prev = null;
        classes.sort(Comparator.comparingInt(AnnotationHandlerFactory::getOrder).reversed());
        try {
            for (Class<?> clazz : classes) {
                String methodName = clazz.getSimpleName().split("(?=[A-Z])")[0].toUpperCase();
                Constructor<?> constructor = clazz.getConstructor(String.class, MethodHandler.class, SocketService.class, ResponseSerializer.class);
                prev = (MethodHandler) constructor.newInstance(methodName, prev, socketService, ResponseSerializerFactory.createResponseSerializer());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prev;
    }

    private static int getOrder(Class<?> clazz) {
        return clazz.getAnnotation(Handler.class).handlerOrder();
    }
}


