package br.com.jsa.aluguellegal.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "contrato")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Contrato implements Serializable {

    private static final long serialversionuid = 1621971725973432195l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    @OneToOne
    @JoinColumn(name = "despesa_id")
    private Despesa despesa;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    @Column(name = "data_inicio")
    private Date datainicio;
    @Column(name = "data_fim")
    private Date datafim;
    @Column(name = "data_contrato")
    private Date datacontrato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public Date getDatacontrato() {
        return datacontrato;
    }

    public void setDatacontrato(Date datacontrato) {
        this.datacontrato = datacontrato;
    }
}
