package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object executeTrasacao(InvocationContext context) throws Exception {
		manager.getTransaction().begin();
		Object proceed = context.proceed();
		manager.getTransaction().commit();
		return proceed;
	}
}
