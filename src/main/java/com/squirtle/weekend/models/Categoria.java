package com.squirtle.weekend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "categoria")
@Getter @Setter
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NonNull
	private String nome;
}
