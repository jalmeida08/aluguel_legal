package br.com.jsa.aluguellegal.exceptions;

public class CpfJaCadastradoException extends  Exception {

    public CpfJaCadastradoException(){
        super("O número de CPF informado já está cadastrado no sistema");
    }

    public CpfJaCadastradoException(String msg, Throwable cause){
        super(msg, cause);
    }
}
