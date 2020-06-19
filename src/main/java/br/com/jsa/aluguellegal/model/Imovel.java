package br.com.jsa.aluguellegal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "imovel")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Imovel implements Serializable {

	private static final long serialVersionUID = 845961843393627309L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@ManyToMany
	@JoinTable(name = "pessoa_imovel")
	@JoinColumn(name="pessoa_id")
	private List<Pessoa> pessoa = new ArrayList<Pessoa>();
	@OneToOne(mappedBy = "imovel")
	private Contrato contrato;
	private Integer cep;
	private String endereco;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	@ManyToMany(mappedBy="imovel")
	private List<Despesa> despesa = new ArrayList<Despesa>();
	@Column(name="status_imovel")
	private String statusImovel;
	@Version
	@Column(name="versao")
	private Integer versao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Pessoa> getPessoa() {
		return pessoa;
	}
	public void setPessoa(List<Pessoa> pessoa) {
		this.pessoa = pessoa;
	}
	public Integer getCep() {
		return cep;
	}
	public void setCep(Integer cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public List<Despesa> getDespesa() {
		return despesa;
	}
	public void setDespesa(List<Despesa> despesa) {
		this.despesa = despesa;
	}
	public String getStatusImovel() {
		return statusImovel;
	}
	public void setStatusImovel(String statusImovel) {
		this.statusImovel = statusImovel;
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
