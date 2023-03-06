package com.example.exception;

import com.example.dto.UserNotFoundExceptionDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class ExceptionHandlerClass {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(MethodArgumentNotValidException exc){
        log.error("[ERROR] Argumentos - : " + exc.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        exc.getBindingResult().getFieldErrors().forEach(err -> {
            errorMap.put(err.getField(), err.getDefaultMessage());
        });
        log.info(errorMap.values().toString());
        return ResponseEntity.badRequest().body(errorMap);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){

        log.error(ex.getMessage());
        UserNotFoundExceptionDTO userError = new UserNotFoundExceptionDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity(userError.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, String>> handleWebBindException(WebExchangeBindException e){
        WebExchangeBindException exWeb = (WebExchangeBindException) e;
        return returnErrors(exWeb);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>>  handleOtherErrors(Exception e){
        ServerWebInputException exWeb = (ServerWebInputException) e;
        return returnErrors(exWeb);
    }

    public ResponseEntity<Map<String, String>> returnErrors(Exception e){
        log.error("[ERRO] ENTROU NO ERRO GENERICO");
        log.error("[ERRO] DE EXCEPTION " + e.getClass().getCanonicalName());
        //log.error("[ERRO] STACK TRACE...: " + e.getStackTrace());
        //e.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        MethodArgumentNotValidException exMethod = null;
        try{
            exMethod = (MethodArgumentNotValidException) e;
        }catch (Exception ex2){
            log.error("Exception 2--->>>" + e.getMessage());
            //e.getFieldErrors().stream().toList().forEach(d -> return d.field + d.codes);
            //e.printStackTrace();
        }finally {
            if(exMethod != null){
                exMethod.getBindingResult().getFieldErrors().forEach(err -> {
                    errorMap.put(err.getField(), err.getDefaultMessage());
                });
                log.info(errorMap.values().toString());
                return ResponseEntity.badRequest().body(errorMap);
            }else {
                var xstr = (WebExchangeBindException) e;
                errorMap.put("Source","CAMPOS INVÁLIDOS");
                xstr.getFieldErrors().forEach(c -> errorMap.put(c.getField(), c.getCode()));
                log.info("Campo(s) inválido(s)!");

                return ResponseEntity.badRequest().body(errorMap);
            }
        }


    }

}

