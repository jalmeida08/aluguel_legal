package br.com.jsa.aluguellegal.exceptions;

public class UsuarioJaCadastradoException extends Exception {

    public UsuarioJaCadastradoException() { super("Esse usuário já está cadastrado no sistema"); }

    public UsuarioJaCadastradoException(String msg, Throwable cause){
        super(msg, cause);
    }
}
