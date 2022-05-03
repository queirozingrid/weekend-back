package com.squirtle.weekend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "endereco")
@Getter @Setter
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NonNull
	private String cep;
	
	@NotBlank
	@NonNull
	private String rua;
	
	@NotBlank
	@NonNull
	private String bairro;
	
	@NotBlank
	@NonNull
	private String numero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NonNull
	public String getCep() {
		return cep;
	}

	public void setCep(@NonNull String cep) {
		this.cep = cep;
	}

	@NonNull
	public String getRua() {
		return rua;
	}

	public void setRua(@NonNull String rua) {
		this.rua = rua;
	}

	@NonNull
	public String getBairro() {
		return bairro;
	}

	public void setBairro(@NonNull String bairro) {
		this.bairro = bairro;
	}

	@NonNull
	public String getNumero() {
		return numero;
	}

	public void setNumero(@NonNull String numero) {
		this.numero = numero;
	}
}
