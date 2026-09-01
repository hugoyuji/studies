package br.com.gym_api.exception;

public class AlunoEmailAlreadyExistsException extends RuntimeException {

    public AlunoEmailAlreadyExistsException(String mensagem){
        super(mensagem);
    }
}
