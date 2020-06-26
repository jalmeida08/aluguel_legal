package br.com.jsa.aluguellegal.exceptions;

public class ChaveAtivacaoUsuarioNaoLocalizadoException extends Exception {

    public ChaveAtivacaoUsuarioNaoLocalizadoException() {
        super("Erro ao recuperar os dados");
    }

    public ChaveAtivacaoUsuarioNaoLocalizadoException(String msg, Throwable cause){
        super(msg, cause);
    }
}

