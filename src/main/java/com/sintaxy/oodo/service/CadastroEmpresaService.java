package com.sintaxy.oodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sintaxy.oodo.exception.EntidadeEmUsoException;
import com.sintaxy.oodo.exception.EntidadeNaoEncontradaException;
import com.sintaxy.oodo.model.Empresa;
import com.sintaxy.oodo.repository.EmpresaRepository;

@Service
public class CadastroEmpresaService {
	
	@Autowired
    private EmpresaRepository empresaRepository;
	

    public Empresa salvar(Empresa empresa) {
         
          return empresaRepository.save(empresa);
      }
    
    
public void excluir(Long empresaId) {
		
		try {
			
			empresaRepository.deleteById(empresaId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de estado com código %d", empresaId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Estado de código %d não pode ser removida, pois está em uso", empresaId));
		}
	}

}
