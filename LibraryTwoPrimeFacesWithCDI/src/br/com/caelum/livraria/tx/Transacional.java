package br.com.caelum.livraria.tx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@Target({ElementType.METHOD, ElementType.TYPE}) //Terá como alvo métodos e class
@Retention(RetentionPolicy.RUNTIME) //Será executada em tempo de execução
@InterceptorBinding //Info relacionada a um interceptador
public @interface Transacional {

}
