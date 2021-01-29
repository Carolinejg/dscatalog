package com.devsuperior.dscatalog.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.DTO.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

//resources: Pacote que implementa o controlador REST
//para dizer que está classe será um controlador REST colocamos a seguinte anotação: 

@RestController
@RequestMapping(value="/categories")//colocando a rota do recurso REST
public class CategoryResource {
	
	@Autowired //injetar automaticamente a dependencia 
	private CategoryService service;
	
	//o tipo RespondeEntity, tipo do frame que emcapsula uma resposta HTTP.
	//para configurar que o método vai ser um end point do recurso Category, tem que colocar a anotação a seguir: 
	@GetMapping
	public ResponseEntity<List <CategoryDTO>>findAll()
	{
		List<CategoryDTO> list= service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity <CategoryDTO>findByID(@PathVariable Long id)//diz que o arfgumento da rota tem que casar com a variavel id
	{
		CategoryDTO categoryDTO= service.findByID(id);
		return ResponseEntity.ok(categoryDTO);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
		dto = service.insert(dto);
		//quando retorna um código 201 que significa recurso criado, convem adcionar no capeçalho da respota um header chamado location contenod o endereço
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);//inserir no cabeçalho da resposta http://localhost:8080/categories11
	}
	
	
}
