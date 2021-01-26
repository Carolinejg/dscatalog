package com.devsuperior.dscatalog.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//serializable tranforma a classe em sequencia de bites. Não obrigatório
//anotação do JPA para dizer que a classe é uma entidade do banco
@Entity
@Table(name= "tb_category")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id //representa a chave primária da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) //para gerar um chave auto-inrementada
	private long id;
	private String name;
	
	Category()	{
	
	}

	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//hashCode método para comparação entre elementos. Por concidência dois elementos podem ter o mesmo número gerado.  
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override //gerado pela ferramenta faz a comparação de duas categorias pelo id. Comparação totalmente eficaz 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
}
