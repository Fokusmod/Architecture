package ru.geekbrains.example;

import ru.geekbrains.example.controller.StudentController;
import ru.geekbrains.example.model.Student;
import ru.geekbrains.example.service.MockDataBase;
import ru.geekbrains.example.view.StudentView;

public class Main {
    public static void main(String[] args) {

        MockDataBase mockDataBase = new MockDataBase();
        StudentView studentView = new StudentView();

        StudentController studentController = new StudentController(mockDataBase,studentView);

        Student student = new Student("Alex","Alexov",18);
        Student student1 = new Student("Alex1","Alexov1",181);
        Student changedStudent = new Student("Ivan", "Ivanov", 19);

        studentController.addStudent(student);
        studentController.addStudent(student1);

        studentController.getStudent(student);

        studentController.changeStudent(student,changedStudent);

        studentController.infoAboutAllStudents();

        studentController.deleteStudent(student1);

        studentController.infoAboutAllStudents();

    }
}
