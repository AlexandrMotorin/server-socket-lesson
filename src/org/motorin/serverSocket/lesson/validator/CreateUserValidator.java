package org.motorin.serverSocket.lesson.validator;

import org.motorin.serverSocket.lesson.dto.CreateUserDto;
import org.motorin.serverSocket.lesson.entity.Gender;
import org.motorin.serverSocket.lesson.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto>{

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto userDto) {
        var validationResult = new ValidationResult();
        if(!LocalDateFormatter.isValid(userDto.getBirthday())){
            validationResult.add(Error.of("invalid.birthday","Birthday is invalid"));
        }
        if(Gender.valueOf(userDto.getGender()) == null) {
            validationResult.add(Error.of("invalid.gender","GEnder is invalid"));
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance(){
        return INSTANCE;
    };
}
