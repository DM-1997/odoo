package com.sintaxy.oodo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name= "Utilizadores")

public class Utilizador {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(name = "nome")
    private String nome;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "funcao")
    private String funcao;
	
	@Column(name = "senha")
    private String senha;
	
	@Column(name = "contacto")
    private String contacto;
	
	@Column(name = "pref_comunicacao")
    private String pref_comunicacao;
	
	@Column(name = "historico_tickts")
    private String historico_tickts;
	
	
	

}
