package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor //Tipo de classe que faz algo antes e depois(begin e commit)
public class GerenciadorTrasacao implements Serializable { //Centralizando o gerenciamento de transação

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	@AroundInvoke //É executado algo antes e depois da chamada do método. O mundo do CDI chama o antes e depois de Around (ao redor)
	public Object executeTx(InvocationContext context) throws Exception { //CDI passa esse param com  a info de quem está executando
		em.getTransaction().begin();
		
		Object object = context.proceed(); //sabe qual método de add ou update está chamando e chama seu respectivo no DAO

		em.getTransaction().commit();
		
		return object; 		//Caso o bean retorne algo, uma page por exemplo
	}

}
