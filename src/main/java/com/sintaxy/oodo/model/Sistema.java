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
@Table(name= "sistema")

public class Sistema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(name = "descricao")
    private String descricao;
	
	@Column(name = "detalhes_tecnicos")
    private String detalhes_tecnicos;
	
	@Column(name = "funcionalidade")
    private String funcionalidade;
	
	
}
