package org.motorin.serverSocket.lesson.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private Integer id;
    private String name;
    private String image;
    private LocalDate birthday;
    private String email;
    private String password;
    private Role role;
    private Gender gender;
}
