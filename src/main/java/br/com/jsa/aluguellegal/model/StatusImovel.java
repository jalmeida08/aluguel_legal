package br.com.jsa.aluguellegal.model;

public enum StatusImovel {

	DISPONIVEL(1, "Disponível"),
	ALUGADO (2, "Alugado"),
	MANUTENCAO(3, "Em Manutenção");
	
	private Integer id;
	private String descricao;
	
	private StatusImovel(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
