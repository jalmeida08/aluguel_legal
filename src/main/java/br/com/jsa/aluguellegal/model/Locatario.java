package br.com.jsa.aluguellegal.model;

import javax.persistence.Entity;

@Entity
public class Locatario extends Pessoa {

	private static final long serialVersionUID = -1247354878506923006L;
	
	private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

