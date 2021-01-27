package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.DTO.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

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

}
