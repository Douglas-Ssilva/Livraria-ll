package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Autor autor = new Autor();
	private Integer autorId;
	
	@Inject
	private AutorDAO dao; //new AutorDAO();
	
	@Inject
	FacesContext context;
	
	public List<Autor> getAutores(){
		System.out.println("Retornando autores!");
		return this.dao.listaTodos();
	}

	@Transacional 	//Coloco essa annotation nos métodos que sei que usaram transações
	public void gravar() {
		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
			displayMsg("User saved successfully!");
		}else {
			this.dao.atualiza(this.autor);
			displayMsg("User edited successfully!");
		}
		autor= new Autor();
	}
	
	@Transacional
	public void delete(Autor autor) {
		this.dao.remove(autor);
		displayMsg("User deleted successfully!");
	}
	
	public void edit(Autor autor) {
		this.autor= autor;
		System.out.println(this.autor);
	}
	
	public void displayMsg(String msg) {
		context.addMessage(null ,new FacesMessage(FacesMessage.SEVERITY_INFO ,msg,""));
	}
	
	public void loadAutor() {
		this.dao.buscaPorId(autorId);
	}
	
	public Autor getAutor() {
		return autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
