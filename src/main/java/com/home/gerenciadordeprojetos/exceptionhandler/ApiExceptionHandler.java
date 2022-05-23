package com.home.gerenciadordeprojetos.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
    
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        List<Problem.Campo> campos = new ArrayList<>();
        for(ObjectError error: ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) error).getField();
            String menssage = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Problem.Campo(name, menssage));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
        problem.setCampos(campos);
        
        return handleExceptionInternal(ex, problem, headers, status, request);
    }
}
