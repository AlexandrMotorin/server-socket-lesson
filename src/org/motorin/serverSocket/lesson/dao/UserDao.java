package org.motorin.serverSocket.lesson.dao;

import lombok.SneakyThrows;
import org.motorin.serverSocket.lesson.entity.Gender;
import org.motorin.serverSocket.lesson.entity.Role;
import org.motorin.serverSocket.lesson.entity.User;
import org.motorin.serverSocket.lesson.util.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User>{

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, password, role, gender, image) 
            VALUES (?,?,?,?,?,?,?)
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
            "SELECT * FROM users WHERE email = ? AND password = ?";

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

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password){
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {

            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            var resultSet = preparedStatement.executeQuery();
            User user = null;

            if(resultSet.next()){
                user = buildEntity(resultSet);
            }
            return Optional.ofNullable(user);
        }
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
            preparedStatement.setObject(7, user.getImage());

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

    private User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id",Integer.class))
                .name(resultSet.getObject("name",String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate() )
                .image(resultSet.getObject("image", String.class))
                .email(resultSet.getObject("email", String.class))
                .role(Role.find(resultSet.getObject("role",String.class)).orElse(null))
                .gender(Gender.valueOf(resultSet.getObject("gender",String.class)))
                .password(resultSet.getObject("password",String.class))
                .build();
    }
}
