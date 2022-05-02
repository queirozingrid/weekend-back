package com.squirtle.weekend.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cascade;
import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "estabelecimento")
@Getter @Setter
public class Estabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Endereco endereco;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "estabelecimento_categoria")
	private List<Categoria> categorias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NonNull
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(@NonNull String cnpj) {
		this.cnpj = cnpj;
	}

	@NonNull
	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(@NonNull String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@NonNull
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(@NonNull String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@NonNull
	public String getEmail() {
		return email;
	}

	public void setEmail(@NonNull String email) {
		this.email = email;
	}

	@NonNull
	public String getSenha() {
		return senha;
	}

	public void setSenha(@NonNull String senha) {
		this.senha = senha;
	}

	@NonNull
	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(@NonNull String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
