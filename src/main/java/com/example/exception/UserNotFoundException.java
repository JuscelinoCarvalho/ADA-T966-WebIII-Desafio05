package com.example.exception;

import com.example.dto.UserNotFoundExceptionDTO;
import lombok.Data;

public class UserNotFoundException extends Exception{

    private Integer id;
    private String message;

    public UserNotFoundException(UserNotFoundExceptionDTO exceptionDTO) {
        super(exceptionDTO.getMessage());
        this.id = exceptionDTO.getId();
        this.message = exceptionDTO.getMessage();

    }

    public Integer getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return "{ 'id': " + this.id + ",  'message': '[ERRO USER NOT FOUND]: " + this.message + "' }";
    }
}
