package br.com.caelum.livraria.listener;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.caelum.livraria.dao.JPAUtil;

public class ContextListener implements ServletContextListener{
	//Ao encerrar o tomcat encerre a sessão
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.close();
	}
	
	//Ao iniciar o tomcat, inicie a sessão, pede uma sessão p fabrica
	@Override
	public void contextInitialized(ServletContextEvent e) {
		EntityManager em = new JPAUtil().getEntityManager();
	}

}
