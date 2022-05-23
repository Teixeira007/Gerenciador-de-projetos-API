package com.home.gerenciadordeprojetos.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class Problem {
    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private List<Campo> campos; 

    @Getter
    @AllArgsConstructor
    public static class Campo{
        
        private String name;
        private String mensage;
    }
}
