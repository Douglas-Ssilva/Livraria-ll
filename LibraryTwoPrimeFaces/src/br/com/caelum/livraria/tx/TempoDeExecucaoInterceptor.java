package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class TempoDeExecucaoInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@AroundInvoke
	public Object measureTime(InvocationContext context) throws Exception {
		long start= System.currentTimeMillis();
		System.out.println("Log inicio...");
		
		Object object = context.proceed();
		
		long end= System.currentTimeMillis();
		System.out.println("Time the method "+ context.getMethod().getName() +": " + (end - start));
		
		return object;
	}

}
