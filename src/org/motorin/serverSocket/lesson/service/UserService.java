package org.motorin.serverSocket.lesson.service;

import org.motorin.serverSocket.lesson.dao.UserDao;
import org.motorin.serverSocket.lesson.dto.CreateUserDto;
import org.motorin.serverSocket.lesson.entity.User;
import org.motorin.serverSocket.lesson.exception.ValidationException;
import org.motorin.serverSocket.lesson.mapper.CreateUserMapper;
import org.motorin.serverSocket.lesson.validator.CreateUserValidator;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator validator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper mapper = CreateUserMapper.getInstance();

    public Integer create(CreateUserDto userDto){
        var validationResult = validator.isValid(userDto);
        if(!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var user = mapper.mapFrom(userDto);
        var userEntity = userDao.save(user);

        return userEntity.getId();
    }

    public static UserService getInstance(){
        return INSTANCE;
    }

}
