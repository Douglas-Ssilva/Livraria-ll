package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class GerenciadorLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@AroundInvoke
	public Object executeLog(InvocationContext context) throws Exception {
		long start = System.currentTimeMillis();
		Object proceed = context.proceed();
		long end= System.currentTimeMillis();
		System.out.println("Time: "+ (end- start));
		return proceed;
	}

}
