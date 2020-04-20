package br.com.jsa.aluguellegal.model;

public enum TipoTelefone {

	
	TELEFONE_FIXO("Telefone Fixo"),
	CELULAR("Celular");
	
	private String descricao;
	
	private TipoTelefone(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
