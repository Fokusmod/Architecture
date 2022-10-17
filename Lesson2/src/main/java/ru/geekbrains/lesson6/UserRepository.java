package ru.geekbrains.lesson6;

import java.sql.Connection;
import java.util.Optional;

public class UserRepository {

    private final Connection connection;

    private final UserMapper userMapper;

    private UnitOfWork unitOfWork;

    public UserRepository(Connection connection) {
        this.connection = connection;
        this.userMapper = new UserMapper(connection);
        this.unitOfWork = new UnitOfWork(connection);
    }

    public void beginTransaction () {
        this.unitOfWork = new UnitOfWork(connection);
    }

    public void insert(User user){
        this.unitOfWork.registerNew(user);
    }

    public void update(User user, User changedUser){
        this.unitOfWork.registerUpdate(user, changedUser);
    }

    public void delete(User user){
        this.unitOfWork.registerDelete(user);
    }

    public Optional<User> findById(Long id){
       return this.userMapper.findById(id);
    }

    public void commitTransaction(){
        this.unitOfWork.commit();
    }
}
