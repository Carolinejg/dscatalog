package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.DTO.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service //vai registrar com um componente que vai participar da injeção de dependencia automatizado 
public class CategoryService {
	
	@Autowired //vai torna o repository uma dependecia válida
	private CategoryRepository repository;//depsencia com o categoryrepository
	
	@Transactional(readOnly=true)
	public List<CategoryDTO>findAll() {//garante que qualquer operação realizada neste método está dentro de uma transação
		List <Category> list = repository.findAll();
		//para cada elemento da lista de category, tranformo para DTO. 
		//o collect tranforma os elementos de strem para lista de volta
		List <CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		return listDTO;
		
	}
	
	@Transactional(readOnly=true)
	public CategoryDTO findByID(Long id) {
		
		//Optional nunca será um objeto nulo 
		Optional<Category>obj=repository.findById(id);//vai no banco e tras os dados do objeto
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada")); //permite definir uma chamada de exceção
		return new CategoryDTO(entity);  
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new  CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id,CategoryDTO dto) {//para não ir no banco sem necessidade
		try {
			Category entity = repository.getOne(id);// o getOne não toca no banco, intancia um obj provisório so quando manda salvar que ele acessa o bd
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new  CategoryDTO(entity);
		}
		catch(EntityNotFoundException e) {//se chamar o getOne para um id que não existe e tentar salvar acontece essa exceção 
			throw new ResourceNotFoundException("Id not found" + id);
		}
			
	}
	//nao tem a anotação transactional porque queremos capturar uma exceção do banco
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e ) {//se tentar deletar um id que não existe
			throw new ResourceNotFoundException("Id not found" + id);
		}
		catch(DataIntegrityViolationException e) {//se tentar deletar algo que não deve como category, uma vez que todo produto tem pelo menos uma categoria
			throw new DatabaseException("Integrity violation");
		}
		
	}
			
	

}
