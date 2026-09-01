package br.com.gym_api.exception;

public class AlunoNotFoundException extends RuntimeException {

    public AlunoNotFoundException(String message) {
        super(message);
    }
}
