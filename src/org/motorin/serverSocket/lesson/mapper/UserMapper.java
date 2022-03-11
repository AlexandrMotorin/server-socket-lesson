package org.motorin.serverSocket.lesson.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.motorin.serverSocket.lesson.dto.UserDto;
import org.motorin.serverSocket.lesson.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto>{

    private static final UserMapper INSTANCE = new UserMapper();

    public static UserMapper getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .image(object.getImage())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .role(object.getRole())
                .build();
    }
}
