package com.squirtle.weekend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "estabelecimento")
@Getter @Setter
public class Estabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NonNull
	private String cnpj;
	
	@NotBlank
	@NonNull
	private String nomeFantasia;
	
	@NotBlank
	@NonNull
	private String razaoSocial;
	
	@Email
	@NonNull
	@NotBlank
	private String email;
	
	@NotBlank
	@NonNull
	private String senha;
	
	@NotBlank
	@NonNull
	private String telefone1;
	
	private String telefone2;
	
	private String logo;
	
	@OneToOne
	private Endereco endereco;
	
	@ManyToMany
	@JoinColumn(name = "estabelecimento_categoria")
	private List<Categoria> categorias;
	
}
