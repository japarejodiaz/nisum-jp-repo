package com.springboot.project.nisumprojectrestapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserBadRequestException extends RuntimeException {

    public UserBadRequestException(String message) {
        // TODO Auto-generated constructor stub
        super(message);
    }

}