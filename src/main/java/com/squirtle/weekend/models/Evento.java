package com.squirtle.weekend.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "evento")
@Getter @Setter
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NonNull
	private String titulo;
	
	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "estabelecimentoId", referencedColumnName = "id", nullable = false)
	private Estabelecimento estabelecimento;

	@NonNull
	private LocalDateTime data;
	private String descricao;
	
	@NotNull
	@Min(1)
	@Max(5)
	private int faixaPrecos;

	private Boolean precisaIngresso;
	private String poster;
	private String atracoes;
	
	@NotNull
	private Boolean recorrente;
	@NotNull
	private Boolean cobraCouvert;

	private Boolean visibilidade;

	@Lob
	private String descricaoCouvert;

	private ArrayList<String> fotos;

	@NotNull
	private Double valorEntrada;
	
	@ManyToMany
	@JoinColumn(name = "tag_evento")
	private List<Tag> tags;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NonNull
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(@NonNull String titulo) {
		this.titulo = titulo;
	}

	@NonNull
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(@NonNull Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	@NonNull
	public LocalDateTime getData() {
		return data;
	}

	public void setData(@NonNull LocalDateTime data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getFaixaPrecos() {
		return faixaPrecos;
	}

	public void setFaixaPrecos(Integer faixaPrecos) {
		this.faixaPrecos = faixaPrecos;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getAtracoes() {
		return atracoes;
	}

	public void setAtracoes(String atracoes) {
		this.atracoes = atracoes;
	}

	public Boolean getRecorrente() {
		return recorrente;
	}

	public void setRecorrente(Boolean recorrente) {
		this.recorrente = recorrente;
	}

	public Boolean getCobraCouvert() {
		return cobraCouvert;
	}

	public void setCobraCouvert(Boolean cobraCouvert) {
		this.cobraCouvert = cobraCouvert;
	}

	public Double getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getDescricaoCouvert() {
		return descricaoCouvert;
	}

	public void setDescricaoCouvert(String descricaoCouvert) {
		this.descricaoCouvert = descricaoCouvert;
	}

	public Boolean getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(Boolean visibilidade) {
		this.visibilidade = visibilidade;
	}

	public ArrayList<String> getFotos() {
		return fotos;
	}

	public void setFotos(ArrayList<String> fotos) {
		this.fotos = fotos;
	}

	public void setFaixaPrecos(int faixaPrecos) {
		this.faixaPrecos = faixaPrecos;
	}

	public Boolean getPrecisaIngresso() {
		return precisaIngresso;
	}

	public void setPrecisaIngresso(Boolean precisaIngresso) {
		this.precisaIngresso = precisaIngresso;
	}
}
