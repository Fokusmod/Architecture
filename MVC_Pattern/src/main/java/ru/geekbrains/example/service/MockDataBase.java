package ru.geekbrains.example.service;

import ru.geekbrains.example.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MockDataBase {

   private final List<Student> database = new ArrayList<>();

    public List<Student> getDatabase() {
        return database;
    }
}
