package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private final Class<T> classe;
	private EntityManager em;

	//Como o EntityManager é dependência de todos os métodos, dizemos que ele é uma dependência da classe. 
	//E o que fazemos com essa dependência? Como a classe precisa dela, vamos adicioná-la ao seu construtor;
	public DAO(EntityManager em ,Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}

	public void adiciona(T t) {
		em.persist(t);
	}

	public void remove(T t) {
		em.remove(em.merge(t));
	}

	public void atualiza(T t) {
		em.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		return lista;
	}

	public T buscaPorId(Integer id) {
		T instancia = em.find(classe, id);
		return instancia;
	}

	public int contaTodos() {
		long result = (Long) em.createQuery("select count(n) from livro n")
				.getSingleResult();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		return lista;
	}
	
	public int quantidadeDeElementos() {
        long result = (Long) em.createQuery("select count(n) from " + classe.getSimpleName() + " n")
                .getSingleResult();

        return (int) result;
    }

}
