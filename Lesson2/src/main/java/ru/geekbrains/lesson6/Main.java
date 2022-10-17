package ru.geekbrains.lesson6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        User user1 = new User(1L,"user1","pass1");
        User user2 = new User(2L,"user2","pass2");
        User changedUser = new User(3L,"user3","pass3");


        UserRepository userRepository = new UserRepository(getConnection());
        userRepository.beginTransaction();

        userRepository.insert(user1);
        userRepository.insert(user2);
        userRepository.update(user1, changedUser);
        userRepository.delete(user2);
        User user4 = userRepository.findById(3L).get();
        System.out.println(user4);

        userRepository.commitTransaction();
    }

    public static Connection getConnection() {
        try {
            Class.forName(SQliteConnection.DRIVER.getContent());
            Connection connection = DriverManager.getConnection(SQliteConnection.CONNECTION.getContent());
            createTable(connection);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTable(Connection connection){
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists users (id bigserial primary key , login varchar not null , password varchar not null );");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
