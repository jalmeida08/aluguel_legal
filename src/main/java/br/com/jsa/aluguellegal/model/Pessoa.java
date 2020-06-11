package br.com.jsa.aluguellegal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "pessoa")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = -7267937286295026785L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Long numCpf;
	@OneToOne(mappedBy = "pessoa")
	private Contrato contrato;
	@OneToOne(mappedBy="pessoa", fetch = FetchType.LAZY)
	@JsonIgnore
	private Usuario usuario;
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<Contato> contato = new ArrayList<Contato>();
	@ManyToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Imovel> imovel = new ArrayList<Imovel>();
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
	public Long getNumCpf() {
		return numCpf;
	}
	public void setNumCpf(Long numCpf) {
		this.numCpf = numCpf;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Contato> getContato() {
		return contato;
	}
	public void setContato(List<Contato> contato) {
		this.contato = contato;
	}
	public List<Imovel> getImovel() {
		return imovel;
	}
	public void setImovel(List<Imovel> imovel) {
		this.imovel = imovel;
	}
	public Integer getVersao() {
		return versao;
	}
	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
}