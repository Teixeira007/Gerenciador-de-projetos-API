package com.home.gerenciadordeprojetos.exception;

public class NegocioException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NegocioException(String sms){
        super(sms);
    }
}
