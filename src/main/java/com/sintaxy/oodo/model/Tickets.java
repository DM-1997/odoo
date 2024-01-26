package com.sintaxy.oodo.model;

import java.time.LocalDateTime;

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
@Table(name= "tickests_bug")

public class Tickets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
	
	@Column(name = "nome")
    private String nome;
	
	@Column(name = "descricao")
    private String descricao;
	
	@Column(name = "screenshots")
    private String img;
	
	@Column(name = "descricao_solucao")
    private String descricao_solucao;
	
	@Column(name = "tempo_criado")
    private LocalDateTime tempo_criado;
	
	@Column(name = "tempo_agendado")
    private LocalDateTime tempo_agendado;
	
	@Column(name = "prioridade")
    private Prioridade prioridade;

}
