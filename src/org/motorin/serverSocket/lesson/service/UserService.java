package org.motorin.serverSocket.lesson.service;

import lombok.SneakyThrows;
import org.motorin.serverSocket.lesson.dao.UserDao;
import org.motorin.serverSocket.lesson.dto.CreateUserDto;
import org.motorin.serverSocket.lesson.dto.UserDto;
import org.motorin.serverSocket.lesson.entity.User;
import org.motorin.serverSocket.lesson.exception.ValidationException;
import org.motorin.serverSocket.lesson.mapper.CreateUserMapper;
import org.motorin.serverSocket.lesson.mapper.UserMapper;
import org.motorin.serverSocket.lesson.validator.CreateUserValidator;

import java.util.Optional;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private static final String IMAGE_FOLDER = "users/";

    private final CreateUserValidator validator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper mapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getINSTANCE();

    public Optional<UserDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email,password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto){
        var validationResult = validator.isValid(userDto);
        if(!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var userEntity = mapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);

        return userEntity.getId();
    }

    public static UserService getInstance(){
        return INSTANCE;
    }

}
