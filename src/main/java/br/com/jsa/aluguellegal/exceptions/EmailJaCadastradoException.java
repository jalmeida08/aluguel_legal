package br.com.jsa.aluguellegal.exceptions;

public class EmailJaCadastradoException extends Exception {

    public EmailJaCadastradoException() { super("Esse e-mail já está cadastrado no sistema"); }

    public EmailJaCadastradoException(String msg, Throwable cause){
        super(msg, cause);
    }
}
