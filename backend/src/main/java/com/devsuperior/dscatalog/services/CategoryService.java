package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.DTO.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

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
		Optional<Category>obj=repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada")); //permite definir uma chamada de exceção
		return new CategoryDTO(entity);  
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new  CategoryDTO(entity);
	}

}
