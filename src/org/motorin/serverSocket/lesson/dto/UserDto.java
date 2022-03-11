package org.motorin.serverSocket.lesson.dto;

import lombok.Builder;
import lombok.Value;
import org.motorin.serverSocket.lesson.entity.Role;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    Role role;
}
