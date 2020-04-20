package br.com.jsa.aluguellegal.model;

public enum StatusPagamento {
	
	PAGO(1, "Pago"),
	EM_ABERTO(2, "Em aberto"),
	VENCIDO(3, "Vencido");

	private Integer id;
	private String descricao;
	
	private StatusPagamento(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Integer getId() {
		return id;
	}
}
