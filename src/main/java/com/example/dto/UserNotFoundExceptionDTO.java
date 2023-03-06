package com.example.dto;

import lombok.Data;

@Data
public class UserNotFoundExceptionDTO {
    private Integer id;
    private String message;

    public UserNotFoundExceptionDTO(Integer id, String message){
        this.id = id;
        this.message = message;
    }

    public String toString(){
        String sId = "0";

        if(getId() != 0) {
            sId = String.valueOf(this.id);
        }
        if(getMessage() == null){
            this.message = "null";
        }
        return "{ 'id': " + sId + ", 'message': " + this.message + "}";
    }
}
