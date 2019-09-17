package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private final Class<T> classe;
	private EntityManager manager;

	public DAO(EntityManager em,Class<T> classe) {
		this.manager = em;
		this.classe = classe;
	}

	public void adiciona(T t) {
		manager.persist(t);
	}

	public void remove(T t) {
		manager.remove(manager.merge(t));
	}

	public void atualiza(T t) {
		manager.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).getResultList();
		return lista;
	}

	public T buscaPorId(Integer id) {
		T instancia = manager.find(classe, id);
		return instancia;
	}

	public int contaTodos() {
		long result = (Long) manager.createQuery("select count(n) from livro n")
				.getSingleResult();
		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
		return lista;
	}
	
	public int quantidadeDeElementos() {
        long result = (Long) manager.createQuery("select count(n) from " + classe.getSimpleName() + " n")
                .getSingleResult();
        return (int) result;
    }

}
