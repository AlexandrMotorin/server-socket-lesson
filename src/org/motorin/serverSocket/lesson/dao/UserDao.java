package org.motorin.serverSocket.lesson.dao;

import lombok.SneakyThrows;
import org.motorin.serverSocket.lesson.entity.User;
import org.motorin.serverSocket.lesson.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class UserDao implements Dao<Integer, User>{

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, password, role, gender) 
            VALUES (?,?,?,?,?,?)
            """;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    @SneakyThrows
    public User save(User user) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1,user.getName());
            preparedStatement.setObject(2,user.getBirthday());
            preparedStatement.setObject(3,user.getEmail());
            preparedStatement.setObject(4,user.getPassword());
            preparedStatement.setObject(5,user.getRole().name());
            preparedStatement.setObject(6,user.getGender().name());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getObject("id",Integer.class));
        }
        return user;
    }

    public static UserDao getInstance(){
        return INSTANCE;
    }
}
