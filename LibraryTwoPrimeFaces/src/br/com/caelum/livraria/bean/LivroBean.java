package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.dao.LivroDAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private List<Livro> list;
	private List<Autor> listAutor;
	private Integer idAutor;
	private Integer id;
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação", "Didático");
	@Inject
	private LivroDAO dao;
	@Inject
	private AutorDAO daoAutor;
	@Inject
	private FacesContext context;
	
	@Transacional
	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			displayMessage("Livro deve ter pelo menos um Autor!");
			return;
		}
		
		if (this.livro.getId() == null) {
			dao.adiciona(this.livro);
			displayMessage("Saved successfully!");
		}else {
			dao.atualiza(this.livro); 
			displayMessage("Updated successfully!");
		}
		this.list= dao.listaTodos();
		livro = new Livro();
	}
	
	public void addAutor() {
		Autor autor = daoAutor.buscaPorId(idAutor);
		this.livro.adicionaAutor(autor);
		this.idAutor= null;
	}
	
	@Transacional
	public void delete(Livro livro) {
		if (livro != null) {
			dao.remove(livro);
			this.list= dao.listaTodos();
			displayMessage("Removed successfully!");
		}
	}
	
	public void edit(Livro livro) {
		this.livro= dao.buscaPorId(livro.getId());
	}
	
	public void removeAutor(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	private void displayMessage(String msg) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,msg,""));
	}
	
	public String redirect() {
		return "autor?faces-redirect=true";
	}
	
	public void loadById() {
		this.livro= dao.buscaPorId(id);
	}
	
	public void messageValidator(FacesContext context, UIComponent component, Object value)throws ValidatorException {
		String s = value.toString();
		if (!s.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"ISBN should start with 1!",""));
		}
	}
	
	public List<Autor> getAutores() {
		if (listAutor == null) {
			listAutor= daoAutor.listaTodos();
		}
		return listAutor;
	}
	
	public List<Autor> getAutoresLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		if (this.list == null) {
			this.list= dao.listaTodos();
		}
		return this.list;
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Livro> getList() {
		return list;
	}

	public void setList(List<Livro> list) {
		this.list = list;
	}

	public List<String> getGeneros() {
		return generos;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
}
