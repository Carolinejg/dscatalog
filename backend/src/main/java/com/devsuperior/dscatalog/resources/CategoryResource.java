package com.devsuperior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Category;

//resources: Pacote que implementa o controlador REST
//para dizer que está classe será um controlador REST colocamos a seguinte anotação: 

@RestController
@RequestMapping(value="/categories")//colocando a rota do recurso REST
public class CategoryResource {

	//o tipo RespondeEntity, tipo do frame que emcapsula uma resposta HTTP.
	//para configurar que o método vai ser um end point do recurso Category, tem que colocar a anotação a seguir: 
	@GetMapping
	public ResponseEntity<List <Category>>findAll()
	{
		List<Category>list = new ArrayList<>();
		list.add(new Category(1, "Books"));
		list.add(new Category( 2, "Movies"));
		return ResponseEntity.ok(list);
	}
	
	
}
