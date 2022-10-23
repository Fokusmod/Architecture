package ru.geekbrains.example.controller;

import ru.geekbrains.example.model.Student;
import ru.geekbrains.example.service.MockDataBase;
import ru.geekbrains.example.view.StudentView;

import java.util.List;

public class StudentController {

    private List<Student> dataBase;
    private StudentView studentView;

    public StudentController(MockDataBase dataBase, StudentView studentView) {
        this.dataBase = dataBase.getDatabase();
        this.studentView = studentView;
    }

    public void addStudent (Student student) {
        dataBase.add(student);
        System.out.println("Student added");
    }

    public Student getStudent(Student student) {
        for (Student student1 : dataBase) {
            if (student1.equals(student)){
                return student1;
            }
        }
        throw new RuntimeException("this student not found");
    }

    public void deleteStudent (Student student) {
        dataBase.remove(student);
        System.out.println("Student removed");
    }

    public void changeStudent (Student student, Student newStudent) {
        dataBase.remove(student);
        dataBase.add(newStudent);
    }

    public void studentInfo (Student student){
        studentView.studentInfo(student.getFirstName(),student.getLastName(),student.getAge());
    }

    public void infoAboutAllStudents (){
        for (Student student : dataBase) {
            studentView.studentInfo(student.getFirstName(),student.getLastName(),student.getAge());
            System.out.println();
        }
    }


}
