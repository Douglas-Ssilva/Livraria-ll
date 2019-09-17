package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Log;

public class AutorDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em; //Injetando o entity pois todos os métodos precisaram dele
	private DAO<Autor> dao;
	
	@PostConstruct //Falando p CDI iniciar esse método logo que fizer o inject no em
	private void init() {
		this.dao= new DAO<>(em, Autor.class);
	}
	
	@Log
	public void adiciona(Autor autor) {
		this.dao.adiciona(autor);
	}

	@Log
	public List<Autor> listaTodos() {
		System.out.println("Listando autores no AutorDAO");
		return this.dao.listaTodos();
	}

	@Log
	public void atualiza(Autor autor) {
		this.dao.atualiza(autor);
	}

	@Log
	public void remove(Autor autor) {
		this.dao.remove(autor);
	}

	@Log
	public Autor buscaPorId(Integer autorId) {
		return this.dao.buscaPorId(autorId);
	}

}
