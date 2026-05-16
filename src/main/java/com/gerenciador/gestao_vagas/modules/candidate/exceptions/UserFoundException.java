package com.gerenciador.gestao_vagas.modules.candidate.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("Usuario já existe");
    }
}
