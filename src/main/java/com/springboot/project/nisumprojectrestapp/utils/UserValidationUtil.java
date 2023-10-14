package com.springboot.project.nisumprojectrestapp.utils;

import com.springboot.project.nisumprojectrestapp.dto.request.UserPhoneRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestDto;
import com.springboot.project.nisumprojectrestapp.entity.UserEntity;
import com.springboot.project.nisumprojectrestapp.exceptions.UserBadRequestException;
import com.springboot.project.nisumprojectrestapp.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
@Slf4j
public class UserValidationUtil {

    /*@Value("${config.rule.password}")
    private static String PASSWORD_REGEX;*/

    @Autowired
    private IUserRepository userRepository;

    // Contraseña de 8-16 caracteres con al menos un dígito, al menos una
    // letra minúscula, al menos una letra mayúscula, al menos una
    // caracter especial sin espacios en blanco
    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(PASSWORD_REGEX);

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public void validateRequestDto(UserRequestDto userRequestDto) {

        if (userRequestDto.getName() == null ||
                userRequestDto.getName().isEmpty() ||
                userRequestDto.getName().isBlank()) {
            throw new UserBadRequestException("El nombre es requerido ");
        }

        if (userRequestDto.getEmail() == null ||
                userRequestDto.getEmail().isEmpty() ||
                userRequestDto.getEmail().isBlank()) {
            throw new UserBadRequestException("La cuenta de Correo es requerido ");
        } else {
            this.validateEmail(userRequestDto.getEmail());
        }

        if (userRequestDto.getPassword() == null ||
                userRequestDto.getPassword().isEmpty() ||
                userRequestDto.getPassword().isBlank()) {
            throw new UserBadRequestException("La contrasena de Correo es requerido ");
        } else {
            this.validarPasswordFormat(userRequestDto.getPassword());
        }

        if (userRequestDto.getUserPhones() == null) {
            throw new UserBadRequestException("Deben enviar la informacion de telefono del usuario ");
        }

        for (UserPhoneRequestDto listPhone : userRequestDto.getUserPhones()) {

            if (listPhone.getCityCode() == null ||
                    listPhone.getCityCode().isEmpty() ||
                    listPhone.getCityCode().isBlank()) {
                throw new UserBadRequestException("Se debe ingresar el codigo de ciudad o area del telefono del usuario ");
            }

            if (listPhone.getNumberPhone() == null ||
                    listPhone.getNumberPhone().isEmpty() ||
                    listPhone.getNumberPhone().isBlank()) {
                throw new UserBadRequestException("Se debe ingresar el numero telefono del usuario ");
            }

            if (listPhone.getCountryCode() == null ||
                    listPhone.getCountryCode().isEmpty() ||
                    listPhone.getCountryCode().isBlank()) {
                throw new UserBadRequestException("Se debe ingresar el codigo de pais del telefono del usuario ");
            }

        }

    }

    public void validateUserEntity(UserRequestDto userRequestDto) {

        Optional<UserEntity> userEntity = userRepository.getUserEntityByEmail(userRequestDto.getEmail());

        if (userEntity.isPresent()) {
            throw new UserBadRequestException("Cuenta de Correo ya esta registrada " + userRequestDto.getEmail());
        }

    }

    public void validarPasswordFormat(String password) {
        if (PASSWORD_PATTERN.matcher(password).matches()) {
            log.info("The Password is valid");
        } else {
            throw new UserBadRequestException("El formato de contrasena es incorrecto, verifique ");
        }
    }

    public Boolean emailValidator(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public void validateEmail(String email) {
        if (emailValidator(email)) {
            System.out.println("The email address " + email + " is valid");
        } else {
            throw new UserBadRequestException("El formato de cuenta de correo es incorrecto, verifique ");
        }
    }
}






