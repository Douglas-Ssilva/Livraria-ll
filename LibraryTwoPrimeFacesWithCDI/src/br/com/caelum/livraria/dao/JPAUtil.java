package br.com.caelum.livraria.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("LibraryTwoPrimeFacesWithCDI");

	@Produces //informo que será uma fábrica de entityManager
	@RequestScoped //Tenho que passar as vezes que tenho que produzir essa em
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	//Com essa annotation, falo p CDI fechar o em assim que a requisição acabar 
	public void close(@Disposes EntityManager em) {
		em.close();
	}
}
