package com.devsuperior.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;

@Repository //para dizer que é um mecanismo injetavel de dependencia 
public interface CategoryRepository  extends JpaRepository <Category,Long>{//tipo da entidade e o tipo do id
	
}
