package ru.geekbrains.lesson6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection connection;
    private final PreparedStatement preparedStatement;
    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection connection) {
        this.connection = connection;
        try {
            this.preparedStatement = connection.prepareStatement("select id,login,password from users where id = ?;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(Long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }

            try {
                preparedStatement.setLong(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    user = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                    identityMap.put(id,user);
                    return Optional.of(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return Optional.empty();
    }
}
