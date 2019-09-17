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
	private Integer autorId;
	private Autor autor = new Autor();
	private List<Autor> list;
	@Inject
	private AutorDAO dao;
	@Inject
	private FacesContext context;

	public List<Autor> getAutores(){
		if (this.list == null) {
			list= this.dao.listaTodos();
		}
		return this.list;
	}

	@Transacional
	public void gravar() {
		String msg;
		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
			msg= "saved";
		}else {
			this.dao.atualiza(this.autor);
			msg= "edited";
		}
		displayMessage(msg);
		this.list= dao.listaTodos();
		autor= new Autor();
	}
	
	@Transacional
	public void delete(Autor autor) {
		this.dao.remove(autor);
		this.list= dao.listaTodos();
		displayMessage("deleted");
	}
	
	public void edit(Autor autor) {
		this.autor= autor;
	}
	
	private void displayMessage(String msg) {
		context.addMessage(null ,new FacesMessage(FacesMessage.SEVERITY_INFO ,"User "+ msg +" successfully!",""));
	}
	
	public void loadAutor() {
		this.autor= this.dao.buscaPorId(autorId);
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
