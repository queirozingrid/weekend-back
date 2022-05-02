package com.squirtle.weekend.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private Integer faixaPrecos;
	private String poster;
	private String atracoes;
	
	@NotNull
	private Boolean recorrente;
	@NotNull
	private Boolean cobraCouvert;
	@NotNull
	private Double valorEntrada;
	
	@ManyToMany
	@JoinColumn(name = "tag_evento")
	private List<Tag> tags;
	
}
