package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro>{

	private static final long serialVersionUID = 1L;
//	private DAO<Livro> dao= new DAO<>(Livro.class);
	
//	public LivroDataModel() {
//		super.setRowCount(dao.quantidadeDeElementos()); //Precisamos passar quantidade máxima de registros
//	}
//	
//	//Busca certa quantidade de livros no banco
//	@Override
//	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, 
//			Map<String, Object> filtros) {
//		 String titulo = (String) filtros.get("titulo");        
//		 return dao.listaTodosPaginada(inicio, quantidade);
//	}

}
