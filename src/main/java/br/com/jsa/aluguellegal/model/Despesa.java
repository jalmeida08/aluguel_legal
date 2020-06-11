package br.com.jsa.aluguellegal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "despesa")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Despesa implements Serializable {

	private static final long serialVersionUID = -8774227577853371546L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	private String nome;
	private Double valor;
	@OneToOne(mappedBy = "despesa")
	private Contrato contrato;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "imovel_despesa")
	@JoinColumn(name="imovel_id")
	private List<Imovel> imovel = new ArrayList<Imovel>();
	@Column(name="ano_mes_referencia")
	private Integer anoMesReferencia;
	@Column(name="data_vencimento")
	private Date dataVencimento; 
	@Column(name="data_pagamento")
	private Date dataPagamento;
	private String descricao;
	@Version
	@Column(name="versao")
	private Integer versao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public List<Imovel> getImovel() {
		return imovel;
	}
	public void setImovel(List<Imovel> imovel) {
		this.imovel = imovel;
	}
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getVersao() {
		return versao;
	}
	public void setVersao(Integer versao) {
		this.versao = versao;
	}
	
	
	
}
