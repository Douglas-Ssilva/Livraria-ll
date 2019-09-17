package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
	private Integer idAutor;
	private Integer id;
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação", "Didático");
	
	@Inject
	private LivroDAO dao;
	@Inject
	private AutorDAO autorDAO;
	@Inject
	private FacesContext context;

	@Transacional
	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			displayMsg("Livro deve ter pelo menos um Autor!");
			return;
		}
		
		if (this.livro.getId() == null) {
			dao.adiciona(this.livro);
			this.list= dao.listaTodos(); //Atualizando a lista após gravar o livro
			displayMsg("Saved successfully!");
			
		}else {
			dao.atualiza(this.livro); 
			displayMsg("Updated successfully!");
		}
		
		livro = new Livro();
	}
	
	public void addAutor() {
		Autor autor = this.autorDAO.buscaPorId(idAutor);
		this.livro.adicionaAutor(autor);
		this.idAutor= null;
	}
	
	private void displayMsg(String msg) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,msg,""));
	}
	
	
	@Transacional
	public void delete(Livro livro) {
		if (livro != null) {
			dao.remove(livro);
			this.list= dao.listaTodos();
			displayMsg("Removed successfully!");
		}
	}
	
	public void edit(Livro livro) {
		this.livro= this.dao.buscaPorId(livro.getId()); //Foi tirado o eager do carregamento, dessa forma carrego o autor somente quando precisar
	}
	
	public void removeAutor(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public String redirect() {
		System.out.println("Redirecionando...");
		return "autor?faces-redirect=true";
	}
	
	public void loadById() {
		this.livro= this.dao.buscaPorId(id);
	}
	
	public void messageValidator(FacesContext context, UIComponent component, Object value)throws ValidatorException {
		String s = value.toString();
		if (!s.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"ISBN should start with 1!",""));
		}
	}
	
	public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) {
        //tirando espaços do filtro
        String textoDigitado = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();

        System.out.println("Filtrando pelo " + textoDigitado + ", Valor do elemento: " + valorColuna);

        // o filtro é nulo ou vazio?
        if (textoDigitado == null || textoDigitado.equals("")) {
            return true;
        }

        // elemento da tabela é nulo?
        if (valorColuna == null) {
            return false;
        }

        try {
            // fazendo o parsing do filtro para converter para Double
            Double precoDigitado = Double.valueOf(textoDigitado);
            Double precoColuna = (Double) valorColuna;

            // comparando os valores, compareTo devolve um valor negativo se o value é menor do que o filtro
            return precoColuna.compareTo(precoDigitado) < 0;

        } catch (NumberFormatException e) {
            // usuario nao digitou um numero
            return false;
        }
}
	
	public List<Autor> getAutores() {
		return this.autorDAO.listaTodos();
	}
	
	public List<Autor> getAutoresLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros(){
		//Carregue somente se os livros não foram inicializados, carregando a lista apenas uma vez
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
