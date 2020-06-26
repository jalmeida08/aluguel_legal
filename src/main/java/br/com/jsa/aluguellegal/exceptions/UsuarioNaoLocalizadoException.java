package br.com.jsa.aluguellegal.exceptions;

public class UsuarioNaoLocalizadoException extends Exception {

    public UsuarioNaoLocalizadoException() {
        super("Usuário Não Localizado. Entre em contato com o administrador do sistema");
    }

    public UsuarioNaoLocalizadoException(String msg, Throwable cause){
        super(msg, cause);
    }
}
