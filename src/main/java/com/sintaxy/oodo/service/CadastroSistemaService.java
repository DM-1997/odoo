package com.sintaxy.oodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sintaxy.oodo.exception.EntidadeEmUsoException;
import com.sintaxy.oodo.exception.EntidadeNaoEncontradaException;
import com.sintaxy.oodo.model.Empresa;
import com.sintaxy.oodo.model.Sistema;
import com.sintaxy.oodo.repository.EmpresaRepository;
import com.sintaxy.oodo.repository.SistemaRepository;

@Service
public class CadastroSistemaService {
	
	@Autowired
    private SistemaRepository sistemaRepository;
	

    public Sistema salvar(Sistema sistema) {
         
          return sistemaRepository.save(sistema);
      }
    
    
    
public void excluir(Long sistemaId) {
		
		try {
			
			sistemaRepository.deleteById(sistemaId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de estado com código %d", sistemaId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Estado de código %d não pode ser removida, pois está em uso", sistemaId));
		}
	}

}







































