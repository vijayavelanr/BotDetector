package com.codebuzz.botdetector;

import com.codebuzz.botdetector.exception.NotAHumanException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BotExceptionAdvice {


        @ResponseBody
        @ExceptionHandler(NotAHumanException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String employeeNotFoundHandler(NotAHumanException ex) {
            return ex.getMessage();
        }
}
