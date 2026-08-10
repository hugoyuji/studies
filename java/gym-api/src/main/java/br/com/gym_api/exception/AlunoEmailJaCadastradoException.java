package br.com.gym_api.exception;

public class AlunoEmailJaCadastradoException extends RuntimeException {

    public AlunoEmailJaCadastradoException (String mensagem){
        super(mensagem);
    }
}
