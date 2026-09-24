package br.com.stock_api.exception;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String mensagem) {
        super(mensagem);
    }
}