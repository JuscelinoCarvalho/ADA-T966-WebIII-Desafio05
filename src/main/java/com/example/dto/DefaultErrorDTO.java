package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultErrorDTO {

    private int id;
    private String message;
    @Override
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
