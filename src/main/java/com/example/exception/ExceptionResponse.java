package com.example.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date timeStamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timeStamp, String message, String details){
        super();
        this.timeStamp = timeStamp;
        this.details = details;
        this.message =  message;

    }

}
