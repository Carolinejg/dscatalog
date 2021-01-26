package com.devsuperior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;

@Service //vai registrar com um componente que vai participar da injeção de dependencia automatizado 
public class CategoryService {
	
	@Autowired //vai torna o repository uma dependecia válida
	private CategoryRepository repository;//depsencia com o categoryrepository
	
	
	public List<Category>findAll(){
		return repository.findAll();
	}

}
