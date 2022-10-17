package ru.geekbrains.lesson6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitOfWork {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final List<User> newUsers = new ArrayList<>();
    private final Map<Long, User> updateUsers = new HashMap<>();
    private final List<User> deleteUsers = new ArrayList<>();

    public UnitOfWork(Connection connection) {
        this.connection = connection;
    }

    public void registerNew(User user) {
        newUsers.add(user);
    }

    public void registerUpdate(User user, User changedUser) {
        updateUsers.put(user.getId(), changedUser);
    }

    public void registerDelete(User user) {
        deleteUsers.add(user);
    }

    public void commit() {
        insert(newUsers);
        update(updateUsers);
        delete(deleteUsers);
        close(connection);
    }

    private void delete(List<User> list) {
        for (User user : list) {
            try {
                preparedStatement = connection.prepareStatement("delete from users where id = ? and login = ? and password = ?;");
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void update(Map<Long, User> updateUsers) {
        updateUsers.forEach((aLong, user) -> {
            try {
                preparedStatement = connection.prepareStatement("update users set id = ?, login = ?, password = ? where id = ?");
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setLong(4,aLong);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void insert(List<User> newUsers) {
        for (User user : newUsers) {
            try {
                preparedStatement = connection.prepareStatement("insert into users values (?,?,?)");
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void close(Connection connection){
        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}