package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.VendaDAO;
import br.com.caelum.livraria.modelo.Vendas;

@Named
@ViewScoped
public class VendaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private VendaDAO vendaDAO;
	

	public List<Vendas> getVendas(){
		List<Vendas> vendas= vendaDAO.listaTodos();
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

		
		List<Vendas> vendasList= getVendas();
		
		for (Vendas vendas : vendasList) {
			v.set(vendas.getLivro().getTitulo(), vendas.getQuantidade());
		}
		ChartSeries v2 = new ChartSeries();
		v2.setLabel("Vendas 2020");
		
		model.addSeries(v);
		return model;
	}
}
