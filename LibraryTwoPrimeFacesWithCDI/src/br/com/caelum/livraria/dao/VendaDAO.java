package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Vendas;

public class VendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private DAO<Vendas> dao;

	@PostConstruct
	private void init() {
		this.dao= new DAO<>(manager, Vendas.class);
	}

	public void adiciona(Vendas t) {
		dao.adiciona(t);
	}

	public void remove(Vendas t) {
		dao.remove(t);
	}

	public void atualiza(Vendas t) {
		dao.atualiza(t);
	}

	public List<Vendas> listaTodos() {
		return dao.listaTodos();
	}

	public Vendas buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

}
