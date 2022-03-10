package org.motorin.serverSocket.lesson.mapper;

import org.motorin.serverSocket.lesson.dto.CreateUserDto;
import org.motorin.serverSocket.lesson.entity.Gender;
import org.motorin.serverSocket.lesson.entity.Role;
import org.motorin.serverSocket.lesson.entity.User;
import org.motorin.serverSocket.lesson.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final String IMAGE_FOLDER = "users/";

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance(){
        return INSTANCE;
    }
}
