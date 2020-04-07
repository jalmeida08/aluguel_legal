package br.com.jsa.aluguellegal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity
public class Acesso  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_acesso")
	private Integer idAcesso;
	
	private String papel;
	
	@ManyToMany(mappedBy="usuario")
	private List<Usuario> usuario = new ArrayList<Usuario>();

	@Version
	private Integer versao;

	public Integer getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(Integer idAcesso) {
		this.idAcesso = idAcesso;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}
	
}
