package com.wenner.pontointeligente.api.entities;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.wenner.pontointeligente.api.enums.PerfilEnum;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -4878449827130034758L;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String nome;
	
	@Column (nullable = false)
	private String email;
	
	@Column (nullable = false)
	private String senha;
	
	@Column (nullable = false)
	private String cpf;
	
	@Column (name = "valor_hora",nullable = true)
	private BigDecimal valorHora;
	
	@Column (name = "qtd_horas_trabalho_dia", nullable = true)
	private Float qtdHorasTrabalhoDia;
	
	@Column (name = "qtd_horas_almoco", nullable = true)
	private Float qtdHorasAlmoco;
	
	@Enumerated (EnumType.STRING)
	@Column (nullable = false)
	private PerfilEnum perfil;
	
	@Column (name = "data_criacao", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;

	public Funcionario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getValorHora() {
		return valorHora;
	}
	
	@Transient
	public Optional<BigDecimal> getValorHoraOpt() {
		return Optional.ofNullable(valorHora);
	}

	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	public Float getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}
	
	@Transient
	public Optional<Float> getQtdHorasTrabalhoDiaOpt() {
		return Optional.ofNullable(qtdHorasTrabalhoDia);
	}

	public void setQtdHorasTrabalhoDia(Float qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}

	public Float getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}
	
	@Transient
	public Optional<Float> getQtdHorasAlmocoOpt() {
		return Optional.ofNullable(qtdHorasAlmoco);
	}

	public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + ", qtdHorasAlmoco="
				+ qtdHorasAlmoco + ", perfil=" + perfil + ", dataCriacao="
				+ dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", empresa=" + empresa + "]";
	}

}
