package br.com.ticket_api.exception;

public class ChamadoNotFoundException extends RuntimeException {
    public ChamadoNotFoundException(Long id) {
        super("Chamado não encontrado com o ID: " + id);
    }
}
