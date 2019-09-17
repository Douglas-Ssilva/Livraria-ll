package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Vendas;

@Named
@ViewScoped
public class VendaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Vendas venda;
	

	public List<Vendas> getVendas(long seed){
		List<Vendas> vendas= new ArrayList<>();
//		List<Livro> dao= new DAO<Livro>(Livro.class).listaTodos();
//		
//		Random random= new Random(seed); //Gere sempre os mesmos valores
//		for (Livro livro : dao) {
//			Integer quantidade= random.nextInt(500); //Passo o value máximo
//			vendas.add(new Vendas(livro, quantidade));
//		}
		return vendas;
	}
	
	public BarChartModel getModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		model.setTitle("Vendas"); // setando o título do gráfico
		model.setLegendPosition("ne"); // nordeste
		
		//Dados específicos
		ChartSeries v = new ChartSeries();
		v.setLabel("Vendas 2019");

		
		List<Vendas> vendasList= getVendas(1234);
		
		for (Vendas vendas : vendasList) {
			v.set(vendas.getLivro().getTitulo(), vendas.getQuantidade());
		}
		ChartSeries v2 = new ChartSeries();
		v2.setLabel("Vendas 2020");
		
		vendasList= getVendas(4567);
		
		for (Vendas vendas : vendasList) {
			v2.set(vendas.getLivro().getTitulo(), vendas.getQuantidade());
		}

		model.addSeries(v);
		model.addSeries(v2);
		return model;
	}

	public Vendas getVenda() {
		return venda;
	}

	public void setVenda(Vendas venda) {
		this.venda = venda;
	}

}
