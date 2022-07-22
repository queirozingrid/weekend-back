package com.squirtle.weekend.models;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "estabelecimento")
@Getter
public class Estabelecimento implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NonNull
	@CNPJ
	private String cnpj;

	@NotBlank
	@NonNull
	private String nomeFantasia;

	@NotBlank
	@NonNull
	private String razaoSocial;

	@Email
	private String email;

	private String senha;

	private String telefone1;

	private String telefone2;

	private String logo;

	private Boolean estacionamento;

	private Boolean espacoKids;

	private String linkCardapio;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Endereco endereco;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "estabelecimento_categoria")
	private List<Categoria> categorias;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();


	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);

	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCnpj(@NonNull String cnpj) {
		this.cnpj = cnpj;
	}

	public void setNomeFantasia(@NonNull String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setRazaoSocial(@NonNull String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setEstacionamento(Boolean estacionamento) {
		this.estacionamento = estacionamento;
	}

	public void setEspacoKids(Boolean espacoKids) {
		this.espacoKids = espacoKids;
	}

	public void setLinkCardapio(String linkCardapio) {
		this.linkCardapio = linkCardapio;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Estabelecimento{" +
				"id=" + id +
				", cnpj='" + cnpj + '\'' +
				", nomeFantasia='" + nomeFantasia + '\'' +
				", razaoSocial='" + razaoSocial + '\'' +
				", email='" + email + '\'' +
				", senha='" + senha + '\'' +
				", telefone1='" + telefone1 + '\'' +
				", telefone2='" + telefone2 + '\'' +
				", logo='" + logo + '\'' +
				", endereco=" + endereco +
				", categorias=" + categorias +
				'}';
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
