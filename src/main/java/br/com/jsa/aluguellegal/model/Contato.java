package br.com.jsa.aluguellegal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "contato")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Contato implements Serializable{

	private static final long serialVersionUID = -2278837998778912001L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@ManyToOne
	private Pessoa pessoa;
	@Column(name = "num_telefone")
	private Long numTelefone;
	@Column(name="tipo_telefone")
	private String tipoTelefone;
	@Column(name="whats_app")
	private boolean whatsApp;
	@Version
	@Column(name="versao")
	private Integer versao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Long getNumTelefone() {
		return numTelefone;
	}
	public void setNumTelefone(Long numTelefone) {
		this.numTelefone = numTelefone;
	}
	public String getTipoTelefone() {
		return tipoTelefone;
	}
	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	public boolean isWhatsApp() {
		return whatsApp;
	}
	public void setWhatsApp(boolean whatsApp) {
		this.whatsApp = whatsApp;
	}
	public Integer getVersao() {
		return versao;
	}
	public void setVersao(Integer versao) {
		this.versao = versao;
	}
	
	
}
